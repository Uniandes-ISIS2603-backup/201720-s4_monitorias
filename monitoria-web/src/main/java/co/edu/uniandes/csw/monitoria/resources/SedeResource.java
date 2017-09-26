/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.resources;

import co.edu.uniandes.csw.monitoria.dtos.SedeDetailDTO;
import co.edu.uniandes.csw.monitoria.ejb.SedeLogic;
import co.edu.uniandes.csw.monitoria.entities.SedeEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso REST correspondiente a "sedes".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "sedes". Al ejecutar la aplicación, el recurse
 * será accesibe a través de la ruta "/api/sedes"
 *
 * @author s.guzman
 */
@Path("sedes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class SedeResource {

    @Inject
    SedeLogic sedeLogic;
    /**
     * Metdo para obtener la representacion detail de las sedes
     * @return lista de representacion detail de las sedes
     * @throws BusinessLogicException 
     */
    @GET
    public List<SedeDetailDTO> getSedes() throws BusinessLogicException 
    {
        return listSedeEntity2DetailDTO(sedeLogic.getSedes());
    }
    /**
     * Metodo para obtener la representacion detail de una sede
     * @param id de la sede a consultar
     * @return la representacion detail de la sede
     * @throws BusinessLogicException  si la sede no existe
     */
    @GET
    @Path("{id: \\d+}")
    public SedeDetailDTO getSede(@PathParam("id") Long id) throws BusinessLogicException 
    {
        SedeEntity entity = sedeLogic.getSede(id);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /sedes/" + id + " no existe.", 404);
        }
        return new SedeDetailDTO(entity);
    }

    /**
     *metodo para crear una nueva sede
     * @param sede representacion detail de la sede a crear
     * @return la representacion detail de la sede creada
     * @throws BusinessLogicException
     */
    @POST
    public SedeDetailDTO createSede(SedeDetailDTO sede) throws BusinessLogicException 
    {        
         return new SedeDetailDTO(sedeLogic.createSede(sede.toEntity()));
    }

    /**
     *Metodo para editar una sede
     *
     * @param id de la sede a editar
     * @param sede nueva representacion detail de la sede
     * @return la representacion detail de la nueva sede
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{id: \\d+}")
    public SedeDetailDTO updateSede(@PathParam("id") Long id, SedeDetailDTO sede) throws BusinessLogicException 
    {
        sede.setId(id);
        SedeEntity entity = sedeLogic.getSede(id);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /sedes/" + id + " no existe.", 404);
        }
        return new SedeDetailDTO(sedeLogic.updateSede(id, sede.toEntity()));
    }
    /**
     * metodo para eliminar una sede
     * @param id de la sede a eliminar
     * @throws BusinessLogicException 
     */
    @DELETE
    @Path("{sedesId: \\d+}")
    public void deleteSede(@PathParam("sedesId") Long id) throws BusinessLogicException 
    {
        SedeEntity entity = sedeLogic.getSede(id);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /sedes/" + id + " no existe.", 404);
        }
        sedeLogic.deleteSede(id);
    }

    /**
     *Metodo para acceder al sub recurso
     * @param sedesId identificador de la sede
     * @return instancia de sedesSalonesResource
     */
    @Path("{sedesId: \\d+}/salones")
    public Class<SedeSalonesResource> getSedeSalonesResource(@PathParam("sedesId") Long sedesId) 
    {
        SedeEntity entity = sedeLogic.getSede(sedesId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /sedes/" + sedesId + "/reviews no existe.", 404);
        }
        return SedeSalonesResource.class;
    }


    private List<SedeDetailDTO> listSedeEntity2DetailDTO(List<SedeEntity> entityList) 
    {
        List<SedeDetailDTO> list = new ArrayList<>();
        for (SedeEntity entity : entityList) {
            list.add(new SedeDetailDTO(entity));
        }
        return list;
    }

}