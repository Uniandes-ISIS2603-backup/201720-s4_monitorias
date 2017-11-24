/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.resources;


import co.edu.uniandes.csw.monitoria.dtos.HorarioDetailDTO;
import javax.ws.rs.WebApplicationException;
import co.edu.uniandes.csw.monitoria.ejb.HorarioLogic;
import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.HorarioPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author cc.cardenas
 */
@Path("horarios")
@Consumes("application/json")
@Produces("application/json")
@Stateless
public class HorarioResource {
    /**
     * Atributo que conecta el resource con logica.
     * Representa una horario
     */
    @Inject 
    HorarioLogic horarioLogic;
    private final static String error="No existe un horario";
   
    /**
     * POST http://localhost:8080/monitorias-web/api/cantantes Ejemplo
     * json {"name": "Roza Meltrozo", "Ubicacion": "Uniandes"}
     * @param horario corresponde a la representacion java del objeto json
     * enviado en el llamado
     * @return Devuelve el objeto json de entrada que contiene el id creado por la base de datos y el tipo del objeto java. 
     * Ejemplo: {"Type": "HorarioDTO", "id": 1, "name": "Roza Meltrozo", "Ubicaion": "Uniandes"}
     * @throws BusinessLogicException 
     */
 @POST
    public HorarioDetailDTO createHorario(HorarioDetailDTO horario) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
      HorarioEntity horarioEntity = horario.toEntity();
        // Invoca la lógica para crear la horario nueva
      HorarioEntity nuevoHorario = horarioLogic.create(horarioEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new  HorarioDetailDTO(nuevoHorario);
    }
      @GET
    public List<HorarioDetailDTO> getHorarios() throws BusinessLogicException {
        return listEntity2DetailDTO(horarioLogic.getHorarios());
    }
 

    @GET
    @Path("{id: \\d+}")
    public HorarioDetailDTO getHorario(@PathParam("id") Long id) throws BusinessLogicException {
       HorarioEntity entity = horarioLogic.getHorarioById(id);
        if(entity==null)
       {
           throw new  WebApplicationException(error,404);
       }
        return  new HorarioDetailDTO(horarioLogic.getHorarioById(id));
    }
    
    /**
     *
     * @param id
     * @return
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{id: \\d+}")
    public HorarioDetailDTO updateHorario(@PathParam("id") Long id) throws BusinessLogicException {
       HorarioEntity entity = horarioLogic.getHorarioById(id);
        if(entity==null)
       {
           throw new  WebApplicationException(error,404);
       }
        return  new HorarioDetailDTO(horarioLogic.updateHorario(id, entity));
    }
    
     @DELETE
    @Path("{id: \\d+}")
    public void deleteHorario(@PathParam("id") Long id) throws BusinessLogicException {
        
       if(horarioLogic.getHorarioById(id)==null)
       {
           throw new  WebApplicationException(error,404);
       }
       horarioLogic.removeHorario(id);
    }
    /**
     * Lista entidades a DTO
     * 
     * Este método convierte una lista de objetos HorarioEntity a una lista de objetos HorarioDTO(json)
     * @param entityList corresponde a la lista de cantantes de tipo Entity
     * que se va a convertir a DTO.
     * 
     * @return  la lista de horarios en forma DTO (json)
     */
 private List<HorarioDetailDTO> listEntity2DetailDTO(List<HorarioEntity> entityList) {
        List<HorarioDetailDTO> list;
        list = new ArrayList<>();
        entityList.forEach((entity) -> {
            list.add(new HorarioDetailDTO(entity));
        });
        return list;
    }
    
}