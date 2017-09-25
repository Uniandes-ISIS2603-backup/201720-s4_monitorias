/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.resources;

import co.edu.uniandes.csw.monitoria.dtos.SalonDetailDTO;
import co.edu.uniandes.csw.monitoria.ejb.SalonLogic;
import co.edu.uniandes.csw.monitoria.entities.SalonEntity;
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
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso REST correspondiente a "salones".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "salones". Al ejecutar la aplicación, el recurse
 * será accesibe a través de la ruta "/api/salones"
 *
 * @author s.guzman
 */
@Path("/salones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class SalonResource {

    @Inject
    SalonLogic salonLogic;

       /**
     * Obtiene la lista de los registros de Salon
     *
     * @return Colección de objetos de SalonDetailDTO
     * @throws co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException
     * 
     */
    @GET
    public List<SalonDetailDTO> getSalones() throws BusinessLogicException 
    {
        return listSalonEntity2DetailDTO(salonLogic.getSalons());
    }

    @GET
    @Path("{id: \\d+}")
    public SalonDetailDTO getSalon(@PathParam("id") Long id) throws BusinessLogicException 
    {
        SalonEntity entity = salonLogic.getSalon(id);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /salones/" + id + " no existe.", 404);
        }
        return new SalonDetailDTO(entity);
    }

    /**
     * Ejemplo: { "description": "La comunicación en arquitectos de software.",
     * "editorial": { "id": 200, "name": "Oveja Negra 2" }, "image":
     * "https://images-na.ssl-images-amazon.com/images/I/516GyHY9p6L.jpg",
     * "isbn": "930330149-8", "name": "La comunicación en el software",
     * "publishingdate": "2017-08-20T00:00:00-05:00" }
     *
     * @param salon
     * @return
     * @throws BusinessLogicException
     */
    @POST
    public SalonDetailDTO createSalon(SalonDetailDTO salon) throws BusinessLogicException 
    {        
         return new SalonDetailDTO(salonLogic.createSalon(salon.toEntity()));
    }

    /**
     *
     * Ejemplo: { "localizacion": "W501", "disponibilidad": "true", "sedeID": 5}
     *
     * @param id
     * @param salon
     * @return
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{id: \\d+}")
    public SalonDetailDTO updateSalon(@PathParam("id") Long id, SalonDetailDTO salon) throws BusinessLogicException 
    {
        SalonEntity entity = salon.toEntity();
        entity.setId(id);
        SalonEntity oldEntity = salonLogic.getSalon(id);
        if (oldEntity == null) 
        {
            throw new WebApplicationException("El recurso /salones/" + id + " no existe.", 404);
        }
        return new SalonDetailDTO(salonLogic.updateSalon(entity));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteSalon(@PathParam("id") Long id) throws BusinessLogicException 
    {
        SalonEntity entity = salonLogic.getSalon(id);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /salones/" + id + " no existe.", 404);
        }
        salonLogic.deleteSalon(id);
    }




    private List<SalonDetailDTO> listSalonEntity2DetailDTO(List<SalonEntity> entityList) 
    {
        List<SalonDetailDTO> list = new ArrayList<>();
        for (SalonEntity entity : entityList) {
            list.add(new SalonDetailDTO(entity));
        }
        return list;
    }

}