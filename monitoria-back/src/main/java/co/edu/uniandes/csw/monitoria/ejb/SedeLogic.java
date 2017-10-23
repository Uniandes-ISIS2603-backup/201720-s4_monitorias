/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;

import co.edu.uniandes.csw.monitoria.entities.SedeEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.SedePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author s.guzman
 */
@Stateless
public class SedeLogic 
{
        private static final Logger LOGGER = Logger.getLogger(SedeLogic.class.getName());

    @Inject
    private SedePersistence persistence;

    
     /**
     * Llama a la persistencia para traer las sedes existentes en la base de datos.
     * @return lista de sedes en la base de datos
     * @throws co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException
     */
    public List<SedeEntity> getSedes() throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de consultar todas las sedes");
        List<SedeEntity> sedes = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las sedes");
        if(sedes == null)
        {
            throw new BusinessLogicException("Aún no existen sedes en la base de datos");
        }
        if(sedes.isEmpty())
        {
            throw new BusinessLogicException("Aún no existen sedes en la base de datos");
        }
        
        
        return sedes;
    }
    
     /**
     * Llama a la persitencia para buscar una sede de la base de datos
     * @param id id de la sede que se quiere buscar
     * @return entidad de la sede con ese id
     * @throws WebApplicationException  si no existe la sede
     */
    public SedeEntity getSede(Long id)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar sede con id={0}", id);
        SedeEntity sede = persistence.find(id);
        if (sede == null) 
        {
            LOGGER.log(Level.SEVERE, "La sede con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar sede con id={0}", id);
        return sede;
    }
/**
 * Llama a la persistencia par crear una nueva sede
 * @param entity
 * @return la sede creada
 * @throws BusinessLogicException 
 */
    public SedeEntity createSede(SedeEntity entity) throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de creación de sede");
        //Ver si no hay problema con el nombre 
       if(null != persistence.findByName(entity.getName()))
        {
            throw new BusinessLogicException("Ya existe una sede con el nombre \"" + entity.getName() + "\"");
        }
                if(entity.getName() == null)
        {
            throw new BusinessLogicException("Debe asignarle un nombre a la sede");
        } 
        else if((entity.getName().trim()).equals(""))
        {
            throw new BusinessLogicException("Debe asignarle un nombre a la sede ");
        }
                
        //Revisa q no haya problema con la direccion
                
        if( entity.getDireccion()== null)
        {
            throw new BusinessLogicException("No puede existir una sede sin direccion. ");
        }
        else if((entity.getDireccion().trim()).equals(""))
        {
            throw new BusinessLogicException("No puede existir una sede sin direccion. ");
        }
        if (persistence.findByDireccion(entity.getDireccion()) != null)
        {
            throw new BusinessLogicException("Ya existe una sede con esa direccion \"" + entity.getDireccion()+ "\"");
        }

        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de sede");
        return entity;
    }
    
    /**
     * Llama a la persistencia para editar una sede existente
     * @param id
     * @param entity
     * @return
     * @throws BusinessLogicException 
     */
    public SedeEntity updateSede(Long id, SedeEntity entity) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar sede con id={0}", id);
        
        if(persistence.find(id) == null)
        {
            throw new WebApplicationException("No se encontró ninguna sede con el id: " + id  + "", 404);
        }
        SedeEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar sede con id={0}", entity.getId());
        return newEntity;
    }
        /**
         *  llama a la persistencia para eliminar una sede
         * @param id 
         */
    public void deleteSede(Long id) 
    {
        
        
        LOGGER.log(Level.INFO, "Inicia proceso de borrar sede con id={0}", id);
        if(persistence.find(id) == null)
        {
            throw new WebApplicationException("La biblioteca con el id: " + id + " no existe. ",404);
        }
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar sede con id={0}", id);
    }
    

    
}
    
    

