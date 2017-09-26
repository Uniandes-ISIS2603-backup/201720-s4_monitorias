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
import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;
import java.util.Date;
import java.util.logging.Level;
import javax.persistence.Query;
/**
 *
 * @author Cristian
 */
@Stateless
public class HorarioPersistence {
     private static final Logger LOGGER = Logger.getLogger(HorarioPersistence.class.getName());
    @PersistenceContext(unitName = "monitoriaPU")
    protected EntityManager em;
    
      public HorarioEntity create(HorarioEntity entity) {
        LOGGER.info("Creando un horario nuevo");
        em.persist(entity);
        LOGGER.info("Horario creada");
        return entity;
    }
      
     public HorarioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando horarios con id={0}", id);
        return em.find(HorarioEntity.class,id);
    }
     public List<HorarioEntity> findAll() {
        LOGGER.info("Consultando todos los horarios");
        Query q = em.createQuery("select u from HorarioEntity u");
        return q.getResultList();
    }
     public HorarioEntity update(HorarioEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando horario con id={0}", entity.getId());
        return em.merge(entity);
    }
     public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando horario con id={0}", id);
        HorarioEntity entity = em.find(HorarioEntity.class, id);
        em.remove(entity);
    }  
}
