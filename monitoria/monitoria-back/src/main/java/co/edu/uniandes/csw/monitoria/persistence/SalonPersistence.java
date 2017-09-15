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
import co.edu.uniandes.csw.monitoria.entities.SalonEntity;
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
public class SalonPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(SalonPersistence.class.getName());
    
    @PersistenceContext (unitName = "monitoriaPU")
    protected EntityManager em;
    
        /**
     *
     * @param entity objeto Salon que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public SalonEntity create(SalonEntity entity) 
    {
        LOGGER.info("Creando un salon nuevo");

        em.persist(entity);
        LOGGER.info("Creando un salon nuevo");
        return entity;
    }
    
    
    /**
     * Actualiza un salon.
     *
     * @param entity: el salon que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un salon con los cambios aplicados.
     */
    public SalonEntity update(SalonEntity entity) 
    {
        LOGGER.log(Level.INFO, "Actualizando Salon con id={0}", entity.getId());

        return em.merge(entity);
    }

    /**
     *
     * Borra un salon de la base de datos recibiendo como argumento el id
     * de la Salon
     *
     * @param id: id correspondiente a la Salon a borrar.
     */
    public void delete(Long id) 
    {
        LOGGER.log(Level.INFO, "Borrando Salon con id={0}", id);
        SalonEntity entity = em.find(SalonEntity.class, id);
        em.remove(entity);
    }

    /**
     * Busca si hay algun salon con el id que se envía de argumento
     *
     * @param id: id correspondiente a la Salon buscada.
     * @return un salon.
     */
    public SalonEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Salon con id={0}", id);
        return em.find(SalonEntity.class, id);
    }

    /**
     * Devuelve todas las Salon de la base de datos.
     *
     * @return una lista con tods los Salon que encuentre en la base de
     * datos, 
     */
    public List<SalonEntity> findAll() {
        LOGGER.info("Consultando todas las Salon");
        // Se crea un query para buscar todas las Salon en la base de datos.
        TypedQuery query = em.createQuery("select u from SalonEntity u", SalonEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de Salon.
        return query.getResultList();
    }
    
}


