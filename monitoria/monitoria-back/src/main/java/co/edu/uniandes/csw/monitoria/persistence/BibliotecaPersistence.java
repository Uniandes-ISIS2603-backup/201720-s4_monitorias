/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.persistence;
import co.edu.uniandes.csw.monitoria.ejb.EstudianteLogic;
import co.edu.uniandes.csw.monitoria.entities.BibliotecaEntity;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import co.edu.uniandes.csw.monitoria.entities.EstudianteEntity;
import java.util.Date;
import java.util.logging.Level;
/**
 *
 * @author Cristian
 */
@Stateless
public class BibliotecaPersistence {
    
 private static final Logger LOGGER = Logger.getLogger(BibliotecaPersistence.class.getName());
    @PersistenceContext(unitName = "monitoriaPU")
    protected EntityManager em;
    
    public BibliotecaEntity create(BibliotecaEntity entity){
        LOGGER.info("Creando un Recurso nuevo");
        em.persist(entity);
        LOGGER.info("Creando un Recurso nuevo");
        return entity;
    }
    
    public BibliotecaEntity update(BibliotecaEntity entity){
        return em.merge(entity);
    }
    
    public void delete(Long id){
        BibliotecaEntity entity = em .find(BibliotecaEntity.class, id);
        em.remove(entity);
    }
    
    public BibliotecaEntity find(Long id){
        return em.find(BibliotecaEntity.class,id);
    }
    
    public List<BibliotecaEntity> findAll(){
        TypedQuery query = em.createQuery("select u from BibliotecaEntity u", BibliotecaEntity.class);
        return query.getResultList();
    }
  
    public BibliotecaEntity findByName(String name){
        TypedQuery query = em.createQuery("select e From BibliotecaEntity e where e.name = :name", BibliotecaEntity.class);
        query = query.setParameter("name",name);
        
        List<BibliotecaEntity> sameName = query.getResultList();
        if(sameName.isEmpty()){
            return null;
        }else{return sameName.get(0);}
    }
    
    public BibliotecaEntity findByDireccion(String direccion){
        
        TypedQuery query = em.createQuery("select e From BibliotecaEntity e where e.direccion = :direccion", BibliotecaEntity.class);
        query = query.setParameter("direccion",direccion);
        
        List<BibliotecaEntity> sameDirection = query.getResultList();
        if(sameDirection.isEmpty()){
            return null;
        }
        else{return sameDirection.get(0);} 
}

}
