/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.resources;

import co.edu.uniandes.csw.monitoria.dtos.MonitoriaDTO;
import co.edu.uniandes.csw.monitoria.dtos.MonitoriaDetailDTO;
import co.edu.uniandes.csw.monitoria.ejb.EstudianteLogic;
import co.edu.uniandes.csw.monitoria.ejb.MonitoriaEstudianteLogic;
import co.edu.uniandes.csw.monitoria.ejb.MonitoriaLogic;
import co.edu.uniandes.csw.monitoria.entities.EstudianteEntity;
import co.edu.uniandes.csw.monitoria.entities.MonitoriaEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author l.mejia
 */
@Path("monitorias")
@Consumes("application/json")
@Produces("application/json")
@Stateless
public class MonitoriaResource {
    
    private MonitoriaLogic logic;
   
    private MonitoriaEstudianteLogic logicRelacion;
    
    private EstudianteLogic logicEstudiante;
    
    
    @Inject
    public MonitoriaResource(MonitoriaLogic logic, MonitoriaEstudianteLogic logicRelacion, EstudianteLogic logicEstudiante)
    {
        this.logic=logic;
        this.logicRelacion=logicRelacion;
        this.logicEstudiante=logicEstudiante;
              
    }
    public MonitoriaResource()
    {
        this.logic=null;
        this.logicRelacion=null;
        this.logicEstudiante=null;
              
    }
    
    @POST
    public MonitoriaDetailDTO createMonitoria(MonitoriaDetailDTO monitoria) throws BusinessLogicException{
    MonitoriaEntity entity =monitoria.toEntity();
    MonitoriaEntity nuevaMonitoria = logic.createMonitoria(entity);
    return new MonitoriaDetailDTO(nuevaMonitoria);
    }
    @GET
    public List<MonitoriaDetailDTO> getMonitoria() throws BusinessLogicException{
        return listEntity2DetailDTO(logic.getMonitorias());
    }
    
    @GET
    @Path("{id:\\d+}")
    public MonitoriaDetailDTO getMonitoria(@PathParam("id") Long id) throws BusinessLogicException
    {
        MonitoriaEntity entidad=logic.findById(id);
        return new MonitoriaDetailDTO(entidad);
    }
    
    @PUT
    @Path("{id:\\d+}")
    public MonitoriaDTO updateMonitoria(@PathParam("id") Long id, MonitoriaDTO actualizar) throws BusinessLogicException
    {
        MonitoriaEntity nueva=logic.findById(id);
        if(actualizar.getEstado()!=null)
            nueva.setEstado(actualizar.getEstado());
        return new MonitoriaDTO(logic.update(nueva));
    }
    @PUT
    @Path("estudiante/{id:\\d+}/{idEstudiante:\\d+}")
    public MonitoriaDetailDTO agregarEstudiantes(@PathParam("id") Long id, @PathParam("idEstudiante") Long idEstudiante) throws BusinessLogicException
    {
        EstudianteEntity estudiante=logicEstudiante.findById(idEstudiante);
        MonitoriaEntity monitoria=logic.findById(id);
        if(!monitoria.getTipo().equals("larga") && monitoria.getEstudiantes().size()==1)
            throw new WebApplicationException("el tipo de monitoria no permite mas de 1 estudiante", 405);
        else if(estudiante.getPenalizacion())
            throw new WebApplicationException("el estudiante esta penalizado", 405);
        else{
        logicRelacion.agregarRelacion(estudiante, monitoria);
        }
        return new MonitoriaDetailDTO(logic.findById(id));
    }
    
    @Path("{idMonitoria: \\d+}/actividades")
    public Class<ActividadResource> getActividadResource(@PathParam("idMonitoria") Long monitoriaId) throws BusinessLogicException {
        MonitoriaEntity entity = logic.findById(monitoriaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /actividades/" + monitoriaId + "/actividades no existe.", 404);
        }
        return ActividadResource.class;
    }
    
    private List<MonitoriaDTO> listEntity2DTO(List<MonitoriaEntity> entityList){
        List<MonitoriaDTO> list = new ArrayList<>();
        for(MonitoriaEntity entity: entityList){
            list.add(new MonitoriaDTO(entity));
        }
        return list;
    }
   
    
    
}
