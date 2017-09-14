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
/**
 *
 * @author Cristian
 */
@Stateless
public class HorarioPersistence {
     private static final Logger LOGGER = Logger.getLogger(HorarioPersistence.class.getName());
    @PersistenceContext(unitName = "monitoriaPU")
    protected EntityManager em;
    
    public HorarioEntity create(HorarioEntity entity){
        LOGGER.info("Creando un Recurso Horario nuevo");
        em.persist(entity);
        LOGGER.info("Creando un Recurso Horario nuevo");
        return entity;
    }
    
    public HorarioEntity update(HorarioEntity entity){
         LOGGER.info("Actualizando un Recurso Horario");
        return em.merge(entity);
    }
    
    public void delete(Long id){
        HorarioEntity entity = em .find(HorarioEntity.class, id);
        em.remove(entity);
        LOGGER.info("Eliminando un Recurso Horario"+id);
    }
    
    public HorarioEntity find(Long id){
      LOGGER.info("Encontrando un Recurso Horario"); 
      return em.find(HorarioEntity.class,id);
      
    }
    
    public List<HorarioEntity> findAll(){
        TypedQuery query = em.createQuery("select u from HorarioEntity u", HorarioEntity.class);
        return query.getResultList();
    }
  
    public HorarioEntity findHorarioInicio(Date inicio){
        TypedQuery query = em.createQuery("select e From HorarioEntity e where e.horaInicio = :inicio", HorarioEntity.class);
        query = query.setParameter("inicio",inicio);
        
        List<HorarioEntity> sameName = query.getResultList();
        if(sameName.isEmpty()){
            return null;
        }else{return sameName.get(0);
        
        }
    }
     public HorarioEntity findHorarioFin(Date fin){
        TypedQuery query = em.createQuery("select e From HorarioEntity e where e.horaFin = :fin", HorarioEntity.class);
        query = query.setParameter("fin",fin);
        
        List<HorarioEntity> sameName = query.getResultList();
        if(sameName.isEmpty()){
            return null;
        }else{return sameName.get(0);
        
        }
    }
}
