/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.resources;

import co.edu.uniandes.csw.monitoria.dtos.ActividadDTO;
import co.edu.uniandes.csw.monitoria.ejb.ActividadLogic;
import co.edu.uniandes.csw.monitoria.entities.ActividadEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Carlos
 */
@Path("actividades")
@Produces("application/json")
@Consumes("application/json")
public class ActividadResource {
    @Inject
    private ActividadLogic actividadLogic;
    private List<ActividadDTO> listEntity2DTO(List<ActividadEntity> entityList)
    {
        List<ActividadDTO> list = new ArrayList<>();
         for(ActividadEntity entity: entityList)
        {
            list.add(new ActividadDTO(entity));
        }
         return list;
    }
    @GET
    public List<ActividadDTO> getActividades()
    {
        return listEntity2DTO(actividadLogic.getActividades());
    }
    @GET 
    @Path("{id:\\d+}") 
    public ActividadDTO getActividad(@PathParam("id")Long id) 
    {   return new ActividadDTO(actividadLogic.getActividad(id));
    }
    @POST
    public ActividadDTO createActividad(ActividadDTO actividad) throws BusinessLogicException 
    {
        ActividadEntity actividadEntity = actividad.toEntity();
        ActividadEntity nuevaActividad = actividadLogic.createActividad(actividadEntity);
        return new ActividadDTO(nuevaActividad);
    }
    @PUT 
    @Path("{id:\\d+}")
    public ActividadDTO updateActividad(@PathParam("id") Long id, ActividadDTO actividadDto)
    {
        ActividadEntity entity = actividadDto.toEntity();
        entity.setId(id);
        return new ActividadDTO(actividadLogic.updateActividad(entity));
    }
    @DELETE
    @Path("{id: \\d+}")
    public void deleteActividad(@PathParam("id") Long id)throws BusinessLogicException
    {
        actividadLogic.deleteActividad(id);
    }
}
