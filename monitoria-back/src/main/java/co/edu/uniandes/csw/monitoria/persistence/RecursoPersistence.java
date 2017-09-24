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

import co.edu.uniandes.csw.monitoria.entities.RecursoEntity;
/**
 *
 * @author ms.osorio
 */
@Stateless
public class RecursoPersistence {
    
   private static final Logger LOGGER = Logger.getLogger(RecursoPersistence.class.getName());
    
    @PersistenceContext(unitName = "monitoriaPU")
    protected EntityManager em;
    
    /**
     * Método encargado de persistir los recursos
     * @param entity representa el recurso a persistir
     * @return recurso persistido
     */
    public RecursoEntity createRecurso(RecursoEntity entity){
        LOGGER.info("Creando un Recurso nuevo");
        em.persist(entity);
        LOGGER.info("Creando un Recurso nuevo");
        return entity;
    }
    
    /**
     * método encargado re actualizar los datos de un recurso
     * @param entity representa el recurso con los nuevos datos
     * @return recurso con los nuevos datos
     */
    public RecursoEntity updateRecurso(RecursoEntity entity){
        return em.merge(entity);
    }
    
    /**
     * Método encargado de eliminar un recurso
     * @param id identificador del recurso que se quiere eliminar
     */
    public void deleteRecurso(Long id){
        RecursoEntity entity = em .find(RecursoEntity.class, id);
        em.remove(entity);
    }
    
    /**
     * Encuentra un recurso de una biblioteca
     * @param bibliotecaId id de la biblioteca en la cual se desea buscar el recurso
     * @param recursoId id del recurso que se quiere buscar
     * @return el recurso encontrado, si se encuentra. Null de lo contrario.
     */
     public RecursoEntity getRecurso(Long bibliotecaId,Long recursoId){
        
        TypedQuery<RecursoEntity> query = em.createQuery("Select u from RecursoEntity u where (u.biblioteca.id = :bibliotecaId) and (u.id = :recursoId)", RecursoEntity.class);
        query.setParameter("bibliotecaId", bibliotecaId);
        query.setParameter("recursoId",recursoId);
        List<RecursoEntity> result = query.getResultList();
        RecursoEntity recurso = null;
        if(result == null){
            recurso = null;
        }else if(result.isEmpty()){
            recurso = null;
        }
        else{
            recurso = result.get(0);
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
        else {
            recurso = results.get(0);
        }
        return recurso;
    }
     
}
