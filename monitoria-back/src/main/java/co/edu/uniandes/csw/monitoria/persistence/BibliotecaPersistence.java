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
import javax.transaction.UserTransaction;
/**
 *
 * @author ms.osorio
 */
@Stateless
public class BibliotecaPersistence {
    
 private static final Logger LOGGER = Logger.getLogger(BibliotecaPersistence.class.getName());
    @PersistenceContext(unitName = "monitoriaPU")
    protected EntityManager em;
    
    //private UserTransaction utx;
    
    /**
     * método encargado de persistir una biblioteca
     * @param entity representa a la biblioteca con sus datos
     * @return biblioteca persistida
     */
    public BibliotecaEntity createBiblioteca(BibliotecaEntity entity) {
        
       // try{
        LOGGER.info("Creando un Recurso nuevo");
        //utx.begin();
        em.persist(entity);
        //utx.commit();
        LOGGER.info("Creando un Recurso nuevo");
       /* } catch(Exception e){
            e.printStackTrace();
            try{
                utx.rollback();
            
            }catch(Exception e1){
            e1.printStackTrace();
            }
        } */
        return entity;
    }
    
    /**
     * Método encargado de actualizar los datos de una biblioteca
     * @param entity representa a la biblioteca con los datos
     * @return biblioteca con los nuevos datos
     */
    public BibliotecaEntity updateBiblioteca(BibliotecaEntity entity){
        return em.merge(entity);
    }
    
    /**
     * Método encargado de eliminar una biblioteca
     * @param id identificador de la biblioteca
     */
    public void deleteBiblioteca(Long id){
        BibliotecaEntity entity = em .find(BibliotecaEntity.class, id);
        em.remove(entity);
    }
    
    /**
     * encuentra la biblioteca con ese id
     * @param id identificador de la bieblioteca
     * @return Biblioteca buscada
     */
    public BibliotecaEntity find(Long id){
        return em.find(BibliotecaEntity.class,id);
    }
    
    /**
     * Busca todas las bibliotecas de la base de datos
     * @return Lista de bibliotecas existentes en la base de datos
     */
    public List<BibliotecaEntity> findAll(){
        TypedQuery query = em.createQuery("select u from BibliotecaEntity u", BibliotecaEntity.class);
        return query.getResultList();
    }
  
    /**
     * Busca la biblioteca con ese nombre
     * @param name nombre de la biblioteca
     * @return biblioteca con ese nombre
     */
    public BibliotecaEntity findByName(String name){
        
        TypedQuery query = em.createQuery("select e From BibliotecaEntity e where e.name = :name", BibliotecaEntity.class);
        query = query.setParameter("name",name);
        
        List<BibliotecaEntity> sameName = query.getResultList();
        if(sameName.isEmpty()){
            return null;
        }
        else{return sameName.get(0);} 
    }
    
    /**
     * Busca la biblioteca con esa dirección
     * @param direccion dirección de la biblioteca
     * @return  biblioteca con esa dirección
     */
    public BibliotecaEntity findByDireccion(String direccion){
        
        TypedQuery query = em.createQuery("select e From BibliotecaEntity e where e.ubicacion = :ubicacion", BibliotecaEntity.class);
        query = query.setParameter("ubicacion",direccion);
        
        List<BibliotecaEntity> sameDirection = query.getResultList();
        if(sameDirection.isEmpty()){
            return null;
        }
        else{return sameDirection.get(0);} 
}

}
