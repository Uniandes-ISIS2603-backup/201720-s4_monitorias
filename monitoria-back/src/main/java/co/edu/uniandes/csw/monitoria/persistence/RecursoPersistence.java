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
    
    public RecursoEntity createRecurso(RecursoEntity entity){
        LOGGER.info("Creando un Recurso nuevo");
        em.persist(entity);
        LOGGER.info("Creando un Recurso nuevo");
        return entity;
    }
    
    public RecursoEntity updateRecurso(RecursoEntity entity){
        return em.merge(entity);
    }
    
    public void deleteRecurso(Long id){
        RecursoEntity entity = em .find(RecursoEntity.class, id);
        em.remove(entity);
    }
    
   
    
  /*  public List<RecursoEntity> findAll(){
        TypedQuery query = em.createQuery("select u from RecursoEntity u", RecursoEntity.class);
        return query.getResultList();
    }*/
    
    
    /**
     * Encuentra un recurso de una biblioteca
     * @param bibliotecaId id de la biblioteca en la cual se desea buscar el recurso
     * @param recursoId id del recurso que se quiere buscar
     * @return el recurso encontrado, si se encuentra. Null de lo contrario.
     */
     public RecursoEntity getRecurso(Long bibliotecaId,Long recursoId){
        
        TypedQuery<RecursoEntity> q = em.createQuery("select p from RecursoEntity p where (p.biblioteca.id = :bibliotecaId)and(p.id = :recursoId)",RecursoEntity.class);
        q.setParameter("bibliotecaId", bibliotecaId);
        q.setParameter("recursoId", recursoId);
        List<RecursoEntity> results = q.getResultList();
        RecursoEntity recurso = null;
        if(results == null){
            recurso = null;
        }
        else if(results.isEmpty()){
            recurso = null;
        }
        else if(results.size() >= 1){
            recurso = results.get(0);
        }
        return recurso;
    }
    
     /**
      * 
      * Encuentra un recurso de una biblioteca
     * @param bibliotecaId id de la biblioteca en la cual se desea buscar el recurso
     * @param nombreRecurso nombre del recurso que se quiere buscar
     * @return el recurso encontrado, si se encuentra. Null de lo contrario.
      */
     public RecursoEntity findByName(Long bibliotecaId,String nombreRecurso){
        
        TypedQuery<RecursoEntity> q = em.createQuery("select p from RecursoEntity p where (p.biblioteca.id = :bibliotecaId)and(p.name = :nombreRecurso)",RecursoEntity.class);
        q.setParameter("bibliotecaId", bibliotecaId);
        q.setParameter("nombreRecurso", nombreRecurso);
        List<RecursoEntity> results = q.getResultList();
        RecursoEntity recurso = null;
        if(results == null){
            recurso = null;
        }
        else if(results.isEmpty()){
            recurso = null;
        }
        else if(results.size() >= 1){
            recurso = results.get(0);
        }
        return recurso;
    }
}
