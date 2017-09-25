/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.resources;


import co.edu.uniandes.csw.monitoria.dtos.HorarioDTO;

import co.edu.uniandes.csw.monitoria.ejb.HorarioLogic;
import co.edu.uniandes.csw.monitoria.ejb.IdiomaLogic;
import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;
import co.edu.uniandes.csw.monitoria.entities.IdiomaEntity;
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
    
    private static final Logger LOGGER = Logger.getLogger(HorarioPersistence.class.getName()); // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
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
    public HorarioDTO createHorario(HorarioDTO horario) throws BusinessLogicException{
    HorarioEntity horarioEntity = horario.toEntity();
    HorarioEntity nuevoHorario = horarioLogic.createHorario(horarioEntity);
        return new HorarioDTO(nuevoHorario);
    }
    /**
     * Get para todas las horarios
     * http://localhost:8080/monitorias-web/api/cantantes
     * @return la lista de todas las horarios en objetos json DTO.
     * @throws BusinessLogicException 
     */
    @GET
    public List<HorarioDTO> getHorarios() throws BusinessLogicException{
        return listEntity2DetailDTO(horarioLogic.getHorarios());
    }
    /**
     * GET para una horario
     * http://localhost:8080/monitorias-web/api/horarios/1
     * 
     * @param id corresponde al id de la horario buscada
     * @return la horario encontrada Ejemplo {"Type": "HorarioDTO", "id":1,"name": "Roza Meltrozo", "Codigo": uniandes}
     * 
     * @throws BusinessLogicException 
     * 
     * En caso de no existir el id de la horario buscada se retorna un 404 con el mensaje
     */
    
    @GET
    @Path("{id: \\d+}")
    public HorarioDTO getHorario(@PathParam("id") Long id)throws BusinessLogicException{
        return new HorarioDTO(horarioLogic.findById(id));
    }
    /**
     * PUT http://localhost:8080/monitorias-web/api/horarios/1 Ejemplo
     * json{"id":1, "name": "Ramon de zubiria", "Ubicacion" : "Uniandes }
     * @param id corresponde a la horario a actualizar
     * @param horario corresponde a el objeto con los cambios que se van a realizar
     * @return La horario atualizada
     * @throws BusinessLogicException 
     * En cas de no existir el id de la horario a actualizar se retorna un 404 con el mensaje.
     */
    
    @PUT
    @Path("{id: \\d+}")
    public HorarioDTO updateHorario(@PathParam("id") Long id, HorarioDTO horario) throws BusinessLogicException{
        horario.setId(id);
        HorarioEntity horarioEntity = horario.toEntity();
        return new HorarioDTO(horarioLogic.update(horarioEntity));
    }
    /**
     * DELETE http://localhost:8080/monitorias-web/api/horarios/1
     * 
     * @param id corresponde a la horario a borrar.
     * @throws BusinessLogicException 
     * 
     * En caso de no existir el id de la horario a actualizar se retorna un 404 con el mensaje.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void delteHorario(@PathParam("id") Long id) throws BusinessLogicException{
        horarioLogic.delete(id);
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
    private List<HorarioDTO> listEntity2DetailDTO(List<HorarioEntity> entityList){
        List<HorarioDTO> list = new ArrayList<>();
        for(HorarioEntity entity: entityList){
            list.add(new HorarioDTO(entity));
        }
        return list;
    }
    
}