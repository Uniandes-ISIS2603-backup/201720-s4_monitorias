/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.persistence;

import co.edu.uniandes.csw.monitoria.entities.BibliotecaEntity;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniandes.csw.monitoria.entities.RecursoEntity;
import java.util.Date;
import java.util.logging.Level;
/**
 *
 * @author Cristian
 */
@Stateless
public class RecursoPersistence {
    
   private static final Logger LOGGER = Logger.getLogger(RecursoPersistence.class.getName());
    
    @PersistenceContext(unitName = "monitoriaPU")
    protected EntityManager em;
    
    public RecursoEntity create(RecursoEntity entity){
        LOGGER.info("Creando un Recurso nuevo");
        em.persist(entity);
        LOGGER.info("Creando un Recurso nuevo");
        return entity;
    }
    
    public RecursoEntity update(RecursoEntity entity){
        return em.merge(entity);
    }
    
    public void delete(Long id){
        RecursoEntity entity = em .find(RecursoEntity.class, id);
        em.remove(entity);
    }
    
    public RecursoEntity find(Long id){
        return em.find(RecursoEntity.class,id);
    }
    
    public List<RecursoEntity> findAll(){
        TypedQuery query = em.createQuery("select u from RecursoEntity u", RecursoEntity.class);
        return query.getResultList();
    }
  
    public RecursoEntity findByName(String name){
        TypedQuery query = em.createQuery("select e From RecursoEntity e where e.name = :name", RecursoEntity.class);
        query = query.setParameter("name",name);
        
        List<RecursoEntity> sameName = query.getResultList();
        if(sameName.isEmpty()){
            return null;
        }else{return sameName.get(0);}
    }
}
