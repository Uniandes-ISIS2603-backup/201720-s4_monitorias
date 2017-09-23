/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.resources;

import co.edu.uniandes.csw.monitoria.dtos.RecursoDetailDTO;
import co.edu.uniandes.csw.monitoria.ejb.RecursoLogic;
import co.edu.uniandes.csw.monitoria.entities.RecursoEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.RecursoPersistence;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
     * @param cantante correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
 la base de datos y el tipo del objeto java. Ejemplo: { "type":
 "RecursoDetailDTO", "id": 1, "name": "English 07","editorial": "Panamericana","disponibilidad": "true" }
     * @throws BusinessLogicException
     */
    
    @POST
    public RecursoDetailDTO createCantante(RecursoDetailDTO cantante) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        RecursoEntity cantanteEntity = cantante.toEntity();
        // Invoca la lógica para crear la cantante nueva
        RecursoEntity nuevoCantante = recursoLogic.createRecurso(cantanteEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new RecursoDetailDTO(nuevoCantante);
    } 
    
}
