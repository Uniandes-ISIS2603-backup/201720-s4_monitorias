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
/**
 *
 * @author Cristian
 */
@Stateless
public class EstudiantePersistence {
    
 
    private static final Logger LOGGER = Logger.getLogger(EstudiantePersistence.class.getName());
    @PersistenceContext(unitName = "monitoriaPU")
    protected EntityManager em;
    
    public EstudianteEntity create(EstudianteEntity entity){
        LOGGER.info("Creando un Recurso Estudiante nuevo");
        em.persist(entity);
        LOGGER.info("Creando un Recurso Estudiante nuevo");
        return entity;
    }
    
    public EstudianteEntity update(EstudianteEntity entity){
         LOGGER.info("Actualizando un Recurso Estudiante");
        return em.merge(entity);
    }
    
    public void delete(Long id){
    EstudianteEntity entity= em.find(EstudianteEntity.class, id);
    em.remove(entity);
    }
    
    public EstudianteEntity findById(Long id){
      LOGGER.info("Encontrando un Recurso Estudiante"); 
      return em.find(EstudianteEntity.class,id);
      
    }
    public EstudianteEntity findByCodigo(Long codigo){
        
      TypedQuery<EstudianteEntity> q
                = em.createQuery("select u from EstudianteEntity u where u.codigo = :codigo", EstudianteEntity.class);
        q = q.setParameter("codigo", codigo);
        // Se invoca el query se obtiene la lista resultado
        List<EstudianteEntity> sameName = q.getResultList();
        EstudianteEntity result = null;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        return result;
    }
    
    public List<EstudianteEntity> findAll(){
        TypedQuery query = em.createQuery("select u from EstudianteEntity u", EstudianteEntity.class);
        return query.getResultList();
    }
   public EstudianteEntity findByName(String name){
        TypedQuery query = em.createQuery("select e From EstudianteEntity e where e.name = :name", EstudianteEntity.class);
        query = query.setParameter("name",name);
        
        List<EstudianteEntity> sameName = query.getResultList();
        if(sameName.isEmpty()){
            return null;
        }else{return sameName.get(0);
        
        }
   }

    public EstudianteEntity findByCorreo(String correo) {
       
        TypedQuery query = em.createQuery("select e From EstudianteEntity e where e.correo = :correo", EstudianteEntity.class);
        query = query.setParameter("correo",correo);
        
        List<EstudianteEntity> sameName = query.getResultList();
        if(sameName.isEmpty()){
            return null;
        }else{return sameName.get(0);
        
        }
    }
}
