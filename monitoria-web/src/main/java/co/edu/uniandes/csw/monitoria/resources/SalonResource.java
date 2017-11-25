/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.resources;

import co.edu.uniandes.csw.monitoria.dtos.SalonDTO;
import co.edu.uniandes.csw.monitoria.dtos.SalonDetailDTO;
import co.edu.uniandes.csw.monitoria.ejb.SalonLogic;
import co.edu.uniandes.csw.monitoria.entities.SalonEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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
 * Clase que implementa el recurso REST correspondiente a "salones".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "salones". Al ejecutar la aplicación, el recurse
 * será accesibe a través de la ruta "/api/salones"
 *
 * @author s.guzman
 */
//@Path("/salones")
@Consumes("application/json")
@Produces("application/json")
//@RequestScoped
public class SalonResource {
/**
 * conexion con la logica de salon
 */
    @Inject
    SalonLogic salonLogic;

       /**
     * Obtiene la lista de los registros de Salon
     *
     * @param idSede identificador del salon
     * @return Colección de objetos de SalonDetailDTO
     * @throws co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException
     * 
     */
    @GET
    public List<SalonDTO> getSalones(@PathParam("sedesId") Long idSede) throws BusinessLogicException 
    {
        return listSalonEntity2DTO(salonLogic.getSalons(idSede));
    }

    /**
     * Metodo para obtener la representacion minimum de un salon
     * @param idSede
     * @param id
     * @return
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{idSalon: \\d+}")
    public SalonDTO getSalon(@PathParam("sedesId") Long idSede, @PathParam("idSalon") Long id) throws BusinessLogicException 
    {
        SalonEntity entity = salonLogic.getSalon(idSede, id);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /sede/" + idSede+ "/salones/ " + id + "no existe.", 404);
        }
        return new SalonDTO(entity);
    }

    /**
     * Metodo para crear un nuevo salon
     * @param idSede
     * @param salon
     * @return
     * @throws BusinessLogicException
     */
    @POST
    public SalonDTO createSalon( @PathParam("sedesId")Long idSede,SalonDetailDTO salon) throws BusinessLogicException 
    {        
        return new SalonDTO(salonLogic.createSalon(idSede, salon.toEntity()));
    }

    /**
     * Metodo para editar un salon de una sede
     * Ejemplo: { "localizacion": "W501", "disponibilidad": "true"}
     *
     * @param idSede
     * @param id
     * @param salon
     * @return
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{id: \\d+}")
    public SalonDTO updateSalon(@PathParam("sedesId") Long idSede, @PathParam("id") Long id, SalonDetailDTO salon) throws BusinessLogicException 
    {
        salon.setId(id);
        SalonEntity entity = salonLogic.getSalon(idSede, id);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /Sede/" + idSede + "/salones/" + id + " no existe.", 404);
        }
        return new SalonDTO(salonLogic.updateSalon(idSede, salon.toEntity()));
    }
    /**
     * metodo para eliminar un salon de una sede
     * @param idSede
     * @param id
     * @throws BusinessLogicException 
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteSalon(@PathParam("sedesId") Long idSede, @PathParam("id") Long id) throws BusinessLogicException 
    {
        SalonEntity entity = salonLogic.getSalon(idSede, id);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /Sede/" + idSede + "/salones/"+ id + " no existe.", 404);
        }
        salonLogic.deleteSalon(idSede, id);
    }




    private List<SalonDTO> listSalonEntity2DTO(List<SalonEntity> entityList) 
    {
        List<SalonDTO> list = new ArrayList<>();
        for (SalonEntity entity : entityList) {
            list.add(new SalonDetailDTO(entity));
        }
        return list;
    }

}