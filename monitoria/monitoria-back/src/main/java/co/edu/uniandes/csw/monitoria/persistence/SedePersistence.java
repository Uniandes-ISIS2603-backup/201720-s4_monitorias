/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.persistence;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import co.edu.uniandes.csw.monitoria.entities.EstudianteEntity;
import java.util.Date;
import java.util.logging.Level;
import co.edu.uniandes.csw.monitoria.entities.SedeEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author s.guzman
 */
@Stateless
public class SedePersistence 
{
      private static final Logger LOGGER = Logger.getLogger(SedePersistence.class.getName());
    
    @PersistenceContext (unitName = "monitoriaPU")
    protected EntityManager em;
    
        /**
     *
     * @param entity objeto Sede que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public SedeEntity create(SedeEntity entity) 
    {
        LOGGER.info("Creando un sede nuevo");

        em.persist(entity);
        LOGGER.info("Creando un sede nuevo");
        return entity;
    }
    
    
    /**
     * Actualiza un sede.
     *
     * @param entity: el sede que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un sede con los cambios aplicados.
     */
    public SedeEntity update(SedeEntity entity) 
    {
        LOGGER.log(Level.INFO, "Actualizando Sede con id={0}", entity.getId());

        return em.merge(entity);
    }

    /**
     *
     * Borra un sede de la base de datos recibiendo como argumento el id
     * de la Sede
     *
     * @param id: id correspondiente a la Sede a borrar.
     */
    public void delete(Long id) 
    {
        LOGGER.log(Level.INFO, "Borrando Sede con id={0}", id);
        SedeEntity entity = em.find(SedeEntity.class, id);
        em.remove(entity);
    }

    /**
     * Busca si hay algun sede con el id que se envía de argumento
     *
     * @param id: id correspondiente a la Sede buscada.
     * @return un sede.
     */
    public SedeEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Sede con id={0}", id);
        return em.find(SedeEntity.class, id);
    }

    /**
     * Devuelve todas las Sede de la base de datos.
     *
     * @return una lista con tods los Sede que encuentre en la base de
     * datos, 
     */
    public List<SedeEntity> findAll() {
        LOGGER.info("Consultando todas las Sede");
        // Se crea un query para buscar todas las Sede en la base de datos.
        TypedQuery query = em.createQuery("select u from SedeEntity u", SedeEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de Sede.
        return query.getResultList();
    }
    
    
}

