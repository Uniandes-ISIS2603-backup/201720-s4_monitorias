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
/*
 * Clase de persistencia del recurso Valoracion.
 */


import co.edu.uniandes.csw.monitoria.entities.PagoEntity;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author l.mejia
 */
@Stateless
public class PagoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(PagoPersistence.class.getName());
    @PersistenceContext(unitName = "monitoriaPU")
    protected EntityManager em;
    
    /**
     * Crea una entidad Pago y la almacena en la base de datos
     * @param entity Entidad Valoracion a crear
     * @return la entidad creada
     */
    public PagoEntity create(PagoEntity entity){
        LOGGER.info("Creando un Recurso nuevo");
        em.persist(entity);
        LOGGER.info("Creando un Recurso nuevo");
        return entity;
    }
    /**
     * Hace los cambios correspondientes al Pago deseado
     * @param entity entidad pago a cambiar
     * @return la entidad con los cambios realizados
     */
    public PagoEntity update(PagoEntity entity){
        return em.merge(entity);
    }
    
    /**
     * Busca la entidad de un Pago
     * @param id id de el Pago que se desea buscar
     * @return el pago con el id dado por parametro
     */
    public PagoEntity find(Long id){
        return em.find(PagoEntity.class,id);
    }
    
    /**
     * Elimina un Pago
     * @param if id de el Pago que se desea eliminar
     */
  public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando pago con id={0}", id);
        PagoEntity entity = em.find(PagoEntity.class, id);
        em.remove(entity);
    }
    
    /**
     * busca todos los recursos Pago en la base de datos
     * @return una lista de los recursos pago en la base de datos
     */
    public List<PagoEntity> findAll(){
        TypedQuery query = em.createQuery("select u from PagoEntity u", PagoEntity.class);
        return query.getResultList();
    }
}
