/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;

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
    
    /**
     * Crea un cantante
     * @param entity
     * @return entidad del recurso creado
     * @throws BusinessLogicException 
     */
    public RecursoEntity createRecurso(RecursoEntity entity) throws BusinessLogicException{
        LOGGER.info("Inicia proceso de creación de  un Recurso");
        
        if(persistence.findByName(entity.getName())!= null){
            throw new BusinessLogicException("Ya existe un recurso con el nombre\"" + entity.getName() + "\"");
        }
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de cantante");
        return entity;
    }
    
    public List<RecursoEntity> getRecursos(){
        LOGGER.info("Inicia proceso de consultar todos los recursos");
        List<RecursoEntity> recursos = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los recursos");
        return recursos;
    }
    
    public RecursoEntity update(RecursoEntity recurso)throws BusinessLogicException, WebApplicationException{
        RecursoEntity recursoAntiguo = persistence.find(recurso.getId());
        if(recursoAntiguo == null){
            throw new WebApplicationException("No se encontro ningun recurso con el id:" + recurso.getId() + "",404);
        }
       
       return persistence.update(recurso);
    }
    
    public RecursoEntity findById(Long id) throws WebApplicationException{
        RecursoEntity recursoBuscado = persistence.find(id);
        if(recursoBuscado == null){
            throw new WebApplicationException("El recurso con el id:" + id + " no existe.", 404);
        }
        return recursoBuscado;
    }
    
    public void delete(Long id) throws WebApplicationException{
        RecursoEntity recursoBuscado = persistence.find(id);
        if(recursoBuscado == null){
            throw new WebApplicationException("El recurso con el id:" + id + " no existe.", 404);
        }
        
        persistence.delete(id);
    }
    
}
