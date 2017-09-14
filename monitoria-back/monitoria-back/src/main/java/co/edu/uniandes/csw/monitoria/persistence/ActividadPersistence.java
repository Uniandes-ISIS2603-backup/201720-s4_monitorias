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
import co.edu.uniandes.csw.monitoria.entities.ActividadEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ca.mendoza
 */
@Stateless
public class ActividadPersistence {
    private static final Logger LOGGER = Logger.getLogger(ActividadPersistence.class.getName());

    @PersistenceContext(unitName = "monitoriaPU")
    protected EntityManager em;

    /**
     *
     * @param entity objeto Actividad que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ActividadEntity create(ActividadEntity entity) {
        LOGGER.info("Creando una Actividad nueva");
        em.persist(entity);
        LOGGER.info("Creando una Actividad nueva");
        return entity;
    }

    /**
     * Actualiza una Actividad.
     *
     * @param entity: el Actividad que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un Actividad con los cambios aplicados.
     */
    public ActividadEntity update(ActividadEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Actividad con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * Borra una Actividad de la base de datos recibiendo como argumento el id
     * de la Actividad
     *
     * @param id: id correspondiente a la Actividad a borrar.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Actividad con id={0}", id);
        ActividadEntity entity = em.find(ActividadEntity.class, id);
        em.remove(entity);
    }

    /**
     * Busca si hay alguna Actividad con el id que se envía de argumento
     *
     * @param id: id correspondiente a la Actividad buscada.
     * @return una Actividad.
     */
    public ActividadEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Actividad con id={0}", id);
        return em.find(ActividadEntity.class, id);
    }

    /**
     * Devuelve todas las Actividades de la base de datos.
     *
     * @return una lista con todas las Actividades que encuentre en la base de
     * datos.
     */
    public List<ActividadEntity> findAll() {
        LOGGER.info("Consultando todas las Actividades");
        TypedQuery query = em.createQuery("select u from ActividadEntity u", ActividadEntity.class);
        return query.getResultList();
    }
    
}
