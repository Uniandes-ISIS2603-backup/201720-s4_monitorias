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
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author ms.osorio
 */
@Consumes("application/json")
@Produces("application/json")
@Stateless
public class RecursoResource {
    
    /**
     * Atributo que conecta el resource con logica.
     * Representa un recurso
     */
    
    @Inject
    RecursoLogic recursoLogic; //Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    private static final Logger LOGGER = Logger.getLogger(RecursoPersistence.class.getName());
    
    /**
     * POST http://localhost:8080/monitoria-web/api/recursos Ejemplo
     * json: { "name":"English 07", "editorial": "Panamericana", "disponibilidad": "true"}
     *
     * @param Recurso correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
 la base de datos y el tipo del objeto java. Ejemplo: { "type":
 "RecursoDetailDTO", "id": 1, "name": "English 07","editorial": "Panamericana","disponibilidad": "true" }
     * @throws BusinessLogicException
     */
    
    @POST
    public RecursoDTO createRecurso( @PathParam("bibliotecaId")Long bibliotecaId,RecursoDTO recurso) throws BusinessLogicException {
        return new RecursoDTO(recursoLogic.createRecurso(bibliotecaId, recurso.toEntity()));
    } 
    
    @GET
    public List<RecursoDTO> getRecursos(@PathParam("idRecurso") Long idBiblioteca) throws BusinessLogicException{
        return listEntity2DTO(recursoLogic.getRecursos(idBiblioteca));
    }
   
    @GET
    @Path("{id: \\d+}")
    public RecursoDTO getRecurso(@PathParam("bibliotecaId") Long bibliotecaId,@PathParam("id") Long id) throws BusinessLogicException{
        RecursoEntity entity = recursoLogic.getRecurso(bibliotecaId, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + bibliotecaId + "/reviews/" + id + " no existe.", 404);
        }
        return new RecursoDTO(entity);
    }
   
    @PUT
    @Path("{id: \\d+}")
    public RecursoDTO updateReview(@PathParam("bibliotecaId") Long bibliotecaId, @PathParam("id") Long id, RecursoDTO recurso) throws BusinessLogicException {
        recurso.setId(id);
        RecursoEntity entity = recursoLogic.getRecurso(bibliotecaId, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + bibliotecaId + "/reviews/" + id + " no existe.", 404);
        }
        return new RecursoDTO(recursoLogic.updateRecurso(bibliotecaId, recurso.toEntity()));

    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteRecurso(@PathParam("bibliotecaId") Long bibliotecaId, @PathParam("id") Long id) throws BusinessLogicException {
        RecursoEntity entity = recursoLogic.getRecurso(bibliotecaId, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /bibliotecas/" + bibliotecaId + "/recursos/" + id + " no existe.", 404);
        }
        recursoLogic.deleteRecurso(bibliotecaId, id);
    }
    
     private List<RecursoDTO> listEntity2DTO(List<RecursoEntity> entityList) {
        List<RecursoDTO> list = new ArrayList<>();
        for (RecursoEntity entity : entityList) {
            list.add(new RecursoDTO(entity));
        }
        return list;
    }
}
