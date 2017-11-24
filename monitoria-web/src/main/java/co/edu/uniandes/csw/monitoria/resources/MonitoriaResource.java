/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.resources;

import co.edu.uniandes.csw.monitoria.dtos.MonitoriaDTO;
import co.edu.uniandes.csw.monitoria.dtos.MonitoriaDetailDTO;
import co.edu.uniandes.csw.monitoria.ejb.MonitoriaLogic;
import co.edu.uniandes.csw.monitoria.entities.MonitoriaEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.MonitoriaPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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
    @Inject
    private MonitoriaLogic logic;
    
    private static final Logger LOGGER = Logger.getLogger(MonitoriaPersistence.class.getName());
    
    
    
    @POST
    public MonitoriaDetailDTO createMonitoria(MonitoriaDetailDTO monitoria) throws BusinessLogicException{
    MonitoriaEntity entity =monitoria.toEntity();
    MonitoriaEntity nuevaMonitoria = logic.createMonitoria(entity);
    return new MonitoriaDetailDTO(nuevaMonitoria);
    }
    @GET
    public List<MonitoriaDTO> getMonitoria() throws BusinessLogicException{
        return listEntity2DTO(logic.getMonitorias());
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
        actualizar.setId(id);
        return new MonitoriaDTO((logic.update(actualizar.toEntity())));
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
    private List<MonitoriaDetailDTO> listEntity2DetailDTO(List<MonitoriaEntity> entityList) {
        List<MonitoriaDetailDTO> list = new ArrayList<>();
        for (MonitoriaEntity entity : entityList) {
            list.add(new MonitoriaDetailDTO(entity));
        }
        return list;
    }
    
    
}
