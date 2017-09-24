/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;

import co.edu.uniandes.csw.monitoria.entities.BibliotecaEntity;
import co.edu.uniandes.csw.monitoria.entities.RecursoEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.RecursoPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author ms.osorio
 */
@Stateless
public class RecursoLogic {
    
    private static final Logger LOGGER = Logger.getLogger(RecursoLogic.class.getName()); 
    
    @Inject
    private RecursoPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
    @Inject
    private BibliotecaLogic bibliotecaLogic;
    
    /**
     * Crea un Recurso
     * @param entity
     * @return entidad del recurso creado
     * @throws BusinessLogicException 
     */
    public RecursoEntity createRecurso(Long bibliotecaId, RecursoEntity entity) throws BusinessLogicException{
        LOGGER.info("Inicia proceso de creación de  un Recurso");
        
        BibliotecaEntity biblioteca = bibliotecaLogic.getBiblioteca(bibliotecaId);
        entity.setBiblioteca(biblioteca);
        return persistence.createRecurso(entity);
    }
    
    public List<RecursoEntity> getRecursos(Long idBiblioteca) throws BusinessLogicException{
        LOGGER.info("Inicia proceso de consultar todos los recursos");
        BibliotecaEntity biblioteca = bibliotecaLogic.getBiblioteca(idBiblioteca);
        if(biblioteca.getRecursos() == null){
            throw new BusinessLogicException("La biblioteca que consulta aún no tiene recursos");
        }
        
        if(biblioteca.getRecursos().isEmpty()){
             throw new BusinessLogicException("La biblioteca que consulta aún no tiene Recursos");
        }     
        return biblioteca.getRecursos();
    }
    
    public RecursoEntity updateRecurso(Long bibliotecaId,RecursoEntity recurso)throws BusinessLogicException, WebApplicationException{
     LOGGER.info("inicia proceso de actualizar un recurso");
     BibliotecaEntity biblioteca = bibliotecaLogic.getBiblioteca(bibliotecaId);
     recurso.setBiblioteca(biblioteca);
     return persistence.updateRecurso(recurso);
    }
    
    /**
     * Trae un recurso
     * @param id
     * @return
     * @throws WebApplicationException 
     */
    public RecursoEntity getRecurso(Long bibliotecaId ,Long recursoId) throws WebApplicationException{
       return persistence.getRecurso(bibliotecaId, recursoId);
    }
    
    /**
     * Elimina una instancia de Review de la base de datos.
     * @param id identificador de la instanci a eliminar
     * @param bibliotecaId identificador de la biblioeca la cual es padre del Recurso
     */
    public void deleteRecurso(Long bibliotecaId,Long id) throws WebApplicationException{
      LOGGER.info("inicia proceso de borrar Recurso");
      RecursoEntity old = getRecurso(bibliotecaId,id);
        persistence.deleteRecurso(old.getId());
    }
    
}
