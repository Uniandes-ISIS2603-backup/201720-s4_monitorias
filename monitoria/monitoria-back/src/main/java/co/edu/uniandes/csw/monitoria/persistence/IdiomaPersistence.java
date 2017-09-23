/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.persistence;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import co.edu.uniandes.csw.monitoria.entities.IdiomaEntity;
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

import co.edu.uniandes.csw.monitoria.ejb.IdiomaLogic;
import co.edu.uniandes.csw.monitoria.entities.BibliotecaEntity;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import co.edu.uniandes.csw.monitoria.entities.IdiomaEntity;
import java.util.Date;
import java.util.logging.Level;

@Stateless

  public class IdiomaPersistence {  
 private static final Logger LOGGER = Logger.getLogger(IdiomaPersistence.class.getName());
    @PersistenceContext(unitName = "monitoriaPU")
    protected EntityManager em;
    
    public IdiomaEntity create(IdiomaEntity entity){
        LOGGER.info("Creando un Recurso nuevo");
        em.persist(entity);
        LOGGER.info("Creando un Recurso nuevo");
        return entity;
    }
    
    public IdiomaEntity update(IdiomaEntity entity){
        return em.merge(entity);
    }
    
    public void delete(Long id){
       IdiomaEntity entity = em .find(IdiomaEntity.class, id);
        em.remove(entity);
    }
    
    public IdiomaEntity find(Long id){
        return em.find(IdiomaEntity.class,id);
    }
    
    public List<IdiomaEntity> findAll(){
        TypedQuery query = em.createQuery("select u from IdiomaEntity u", IdiomaEntity.class);
        return query.getResultList();
    }
  
    public IdiomaEntity findByName(String name){
        TypedQuery query = em.createQuery("select e From IdiomaEntity e where e.nombre = :name", IdiomaEntity.class);
        query = query.setParameter("name",name);
        
        List<IdiomaEntity> sameName = query.getResultList();
        if(sameName.isEmpty()){
            return null;
        }else{return sameName.get(0);}
    }
}
