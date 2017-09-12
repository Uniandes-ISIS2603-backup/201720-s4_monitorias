/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.persistence;

import co.edu.uniandes.csw.monitoria.entities.MonitorEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author mf.mena
 */
@Stateless
public class MonitorPersistence {
     private static final Logger LOGGER = Logger.getLogger(MonitorPersistence.class.getName());
    @PersistenceContext(unitName = "monitoriaPU")
    protected EntityManager em;
    
    
    public MonitorEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando MOnitor con id={0}", id);
        return em.find(MonitorEntity.class, id);
    }

    public MonitorEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando monitor con name= ", name);
        TypedQuery<MonitorEntity> q
                = em.createQuery("select u from MonitorEntity u where u.name = :name", MonitorEntity.class);
        q = q.setParameter("name", name);
        return q.getSingleResult();
    }

    public List<MonitorEntity> findAll() {
        LOGGER.info("Consultando todos los monitores");
        Query q = em.createQuery("select u from MonitorEntity u");
        return q.getResultList();
    }

    public MonitorEntity create(MonitorEntity entity) {
        LOGGER.info("Creando un monitor nuevo");
        em.persist(entity);
        LOGGER.info("Monitor creado");
        return entity;
    }

    public MonitorEntity update(MonitorEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Monitor con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando monitor con id={0}", id);
        MonitorEntity entity = em.find(MonitorEntity.class, id);
        em.remove(entity);
    }
}

