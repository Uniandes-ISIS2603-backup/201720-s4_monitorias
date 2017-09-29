/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.resources;

import co.edu.uniandes.csw.monitoria.dtos.RecursoDTO;
import co.edu.uniandes.csw.monitoria.dtos.RecursoDetailDTO;
import co.edu.uniandes.csw.monitoria.ejb.RecursoLogic;
import co.edu.uniandes.csw.monitoria.entities.RecursoEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.RecursoPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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
 *
 * @author ms.osorio
 */
@Consumes("application/json")
@Produces("application/json")
public class RecursoResource {
    
    /**
     * Atributo que conecta el resource con logica.
     * Representa un recurso
     */
    
    @Inject
    RecursoLogic recursoLogic; //Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    private static final Logger LOGGER = Logger.getLogger(RecursoPersistence.class.getName());
    
    /**
     * POST http://localhost:8080/monitoria-web/api/bibliotecas Ejemplo
     * json: { "name":"English 07", "editorial": "Panamericana", "disponibilidad": "true"}
     * @param idBiblioteca representa la biblioteca a la cual pertenece el recurso
     @param recurso correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "RecursoDetailDTO", "id": 1, "name": "English 07","editorial": "Panamericana","disponibilidad": "true" }
     * @throws BusinessLogicException
     */
    @POST
    public RecursoDTO createRecurso( @PathParam("idBiblioteca")Long idBiblioteca,RecursoDetailDTO recurso) throws BusinessLogicException {
        return new RecursoDTO(recursoLogic.createRecurso(idBiblioteca, recurso.toEntity()));
        
    } 
    

    /**
     * Retorna una coleccion de recursosDTO asociados a una instancia de biblioteca
     * @param idBiblioteca
     * @return id de la biblioteca
     * @throws BusinessLogicException si no encuentra la bibliotec 
     */
    @GET
    public List<RecursoDTO> getRecursos(@PathParam("idBiblioteca") Long idBiblioteca) throws BusinessLogicException{
        
        return listEntity2DTO(recursoLogic.getRecursos(idBiblioteca));
    }
   
    /**
     * Busca un recurso con ese id, del cuál esa biblioteca sea la dueña
     * @param bibliotecaId identifcador de la biblioteca   
     * @param id identificador del recurso
     * @return el recurso encontado
     * @throws BusinessLogicException  si no encuentra ningún recurso con esos id
     */
    @GET
    @Path("{idRecurso: \\d+}")
    public RecursoDTO getRecurso(@PathParam("idBiblioteca") Long idBiblioteca,@PathParam("idRecurso") Long id) throws BusinessLogicException{
        RecursoEntity entity = recursoLogic.getRecurso(idBiblioteca, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /bibliotecas/" + idBiblioteca + "/recursos/" + id + " no existe.", 404);
        }
        return new RecursoDTO(entity);
    }
   
    /**
     * Actualiza una instancia de los recursos
     * @param idBiblioteca id de la biblioteca a la cual  pertenece el recurso
     * @param id id del recurso
     * @param recurso objeto con el resto de la información que será actualizada.
     * @return el DTO del objeto con sus nuevos valores
     * @throws BusinessLogicException si no encuentra el recurso
     */
    @PUT
    @Path("{id: \\d+}")
    public RecursoDTO updateReview(@PathParam("idBiblioteca") Long idBiblioteca, @PathParam("id") Long id, RecursoDetailDTO recurso) throws BusinessLogicException {
        recurso.setId(id);
        RecursoEntity entity = recursoLogic.getRecurso(idBiblioteca, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /bibliotecas/" + idBiblioteca + "/recursos/" + id + " no existe.", 404);
        }
        return new RecursoDTO(recursoLogic.updateRecurso(idBiblioteca, recurso.toEntity()));

    }
    
    /**
     * elimina un recursos que tenga ese id y pertenezca a esa biblioteca
     * @param idBiblioteca id de la biblioteca a la cual  pertenece el recurso
     * @param id id del recurso
     * @throws BusinessLogicException  si no existe el recurso
     */
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteRecurso(@PathParam("idBiblioteca") Long idBiblioteca, @PathParam("id") Long id) throws BusinessLogicException {
        
        RecursoEntity entity = recursoLogic.getRecurso(idBiblioteca, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /bibliotecas/" + idBiblioteca + "/recursos/" + id + " no existe.", 404);
        }
        recursoLogic.deleteRecurso(idBiblioteca, id);
    }
    
    /**
     * Convierte una lista de RecursoEntity a una lista de RecursoDTO
     * @param entityList lista de los recursos en fromato Entity
     * @return lista de recursos
     */
     private List<RecursoDTO> listEntity2DTO(List<RecursoEntity> entityList) {
        List<RecursoDTO> list = new ArrayList<>();
        for (RecursoEntity entity : entityList) {
            list.add(new RecursoDTO(entity));
        }
        return list;
    }
     
     
}
