/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.resources;

import co.edu.uniandes.csw.monitoria.dtos.ActividadDTO;
import co.edu.uniandes.csw.monitoria.dtos.ActividadDetailDTO;
import co.edu.uniandes.csw.monitoria.ejb.ActividadLogic;
import co.edu.uniandes.csw.monitoria.entities.ActividadEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Carlos
 */
@Path("actividades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ActividadResource {
     @Inject
    ActividadLogic actividadLogic;
    
    @POST
    public ActividadDetailDTO createActividad(@PathParam("idMonitoria")Long idMonitoria, ActividadDetailDTO actividad) throws BusinessLogicException 
    {
        ActividadEntity actividadEntity = actividad.toEntity();
        ActividadEntity nuevoActividad = actividadLogic.createActividad(idMonitoria,actividadEntity);
        return new ActividadDetailDTO(nuevoActividad);
    }
    @GET
    public List<ActividadDTO> getActividads(Long idMonitoria)throws BusinessLogicException
    {
        return listEntity2DTO(actividadLogic.getActividades(idMonitoria));
    }
   
    
  
    @GET 
    @Path("{id: \\d+}")
    public ActividadDetailDTO getActividad(@PathParam("idMonitoria")Long idMonitoria,@PathParam("id") Long id) 
    {   
        return new ActividadDetailDTO(actividadLogic.getActividad(idMonitoria, id));
    }
    
   
    
    @PUT 
    @Path("{id: \\d+}")
    public ActividadDetailDTO updateActividad(@PathParam("idMonitoria")Long idMonitoria,@PathParam("id") Long id, ActividadDetailDTO actividad) throws BusinessLogicException
    {
        actividad.setId(id);
        ActividadEntity act = actividadLogic.getActividad(idMonitoria, id);
       
        return new ActividadDetailDTO(actividadLogic.updateActividad(idMonitoria, act));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteActividad(@PathParam("idMonitoria")Long idMonitoria, @PathParam("id") Long id)throws BusinessLogicException
    {
        ActividadEntity actividad = actividadLogic.getActividad(idMonitoria, id);
        if(actividad == null){
            throw new WebApplicationException("El recurso no existe");
        }
        actividadLogic.deleteActividad(idMonitoria, id);
    }
//    
//    @Path("{id: \\d+}/recursos") 
//    public Class<RecursoResource> getRecursosResources(@PathParam("id") Long id){
//        ActividadEntity entity = actividadLogic.getActividad(id);
//        if(entity == null){
//            throw new WebApplicationException("El recurso /actividad/" + id + "/recursos no existe.", 404);
//        }
//        return RecursoResource.class;
//    }
    
    private List<ActividadDetailDTO> listEntity2DetailDTO(List<ActividadEntity> entityList){
        List<ActividadDetailDTO> list = new ArrayList<>();
        for(ActividadEntity entity: entityList){
            list.add(new ActividadDetailDTO(entity));
        }
        return list;
    }
     private List<ActividadDTO> listEntity2DTO(List<ActividadEntity> entityList){
        List<ActividadDTO> list = new ArrayList<>();
        for(ActividadEntity entity: entityList){
            list.add(new ActividadDTO(entity));
        }
        return list;
    }
    
}
