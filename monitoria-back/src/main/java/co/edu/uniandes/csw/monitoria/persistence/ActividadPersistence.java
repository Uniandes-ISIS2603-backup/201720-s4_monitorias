/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.persistence;


import javax.ejb.Stateless;
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
        LOGGER.info("Creando una nueva actividad");
        em.persist(entity);
        LOGGER.info("Se creo una nueva actividad");
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
        LOGGER.log(Level.INFO, "Actualizando Actividad con id: ", entity.getId());
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
     * @param monitoriaId
     * @param id: id correspondiente a la Actividad buscada.
     * @return una Actividad.
     */
    public ActividadEntity find(Long monitoriaId, Long id) {
         TypedQuery<ActividadEntity> q = em.createQuery("select p from ActividadEntity p where (p.monitoria.id = :monitoriaId) and (p.id = :actividadid)", ActividadEntity.class);
        q.setParameter("monitoriaId", monitoriaId);
        q.setParameter("actividadid", id);
        List<ActividadEntity> results = q.getResultList();
        ActividadEntity actividad = null;
        if (results == null) {
            actividad = null;
        } else if (results.isEmpty()) {
            actividad = null;
        } else if (results.size() >= 1) {
            actividad = results.get(0);
        }

        return actividad;
    }
    
     public ActividadEntity findByTareaAsginada(Long monitoriaId, String tareaAsignada){
       TypedQuery<ActividadEntity> q = em.createQuery("select p from ActividadEntity p where (p.monitoria.id = :monitoriaId)and(p.tareaAsignada = :tareaAsignada)",ActividadEntity.class);
        q.setParameter("monitoriaId", monitoriaId);
        q.setParameter("tareaAsignada", tareaAsignada);
        List<ActividadEntity> results = q.getResultList();
        ActividadEntity actividad = null;
         if (results == null) {
            actividad = null;
        } else if (results.isEmpty()) {
            actividad = null;
        } else if (results.size() >= 1) {
            actividad = results.get(0);
        }

        return actividad;
    }
 }
