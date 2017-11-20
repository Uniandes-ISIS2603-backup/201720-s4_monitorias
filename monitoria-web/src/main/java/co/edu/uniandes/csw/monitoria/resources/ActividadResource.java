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
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ActividadResource {

    @Inject
    ActividadLogic actividadLogic;

    @POST
    public ActividadDTO createActividad(@PathParam("idMonitoria") Long idMonitoria, ActividadDTO actividad) throws BusinessLogicException {
        return new ActividadDTO(actividadLogic.createActividad(idMonitoria, actividad.toEntity()));
    }

    @GET
    public List<ActividadDTO> getActividades(@PathParam("idMonitoria") Long idMonitoria) throws BusinessLogicException {
        return listEntity2DTO(actividadLogic.getActividades(idMonitoria));
    }

    @GET
    @Path("{id: \\d+}")
    public ActividadDTO getActividad(@PathParam("idMonitoria") Long idMonitoria, @PathParam("id") Long id) {
        return new ActividadDTO(actividadLogic.getActividad(idMonitoria, id));
    }

    @PUT
    @Path("{id: \\d+}")
    public ActividadDTO updateActividad(@PathParam("idMonitoria") Long idMonitoria, @PathParam("id") Long id, ActividadDetailDTO actividad) throws BusinessLogicException {
        actividad.setId(id);

        return new ActividadDTO(actividadLogic.updateActividad(idMonitoria, actividad.toEntity()));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteActividad(@PathParam("idMonitoria") Long idMonitoria, @PathParam("id") Long id) throws BusinessLogicException {
        ActividadEntity actividad = actividadLogic.getActividad(idMonitoria, id);
        if (actividad == null) {
            throw new WebApplicationException("El recurso no existe");
        }
        actividadLogic.deleteActividad(idMonitoria, id);
    }

    private List<ActividadDetailDTO> listEntity2DetailDTO(List<ActividadEntity> entityList) {
        List<ActividadDetailDTO> list = new ArrayList<>();
        for (ActividadEntity entity : entityList) {
            list.add(new ActividadDetailDTO(entity));
        }
        return list;
    }

    private List<ActividadDTO> listEntity2DTO(List<ActividadEntity> entityList) {
        List<ActividadDTO> list = new ArrayList<>();
        for (ActividadEntity entity : entityList) {
            list.add(new ActividadDTO(entity));
        }
        return list;
    }

}
