/*
 * Clase de persistencia del recurso Monitoria.
 */
package co.edu.uniandes.csw.monitoria.persistence;


import co.edu.uniandes.csw.monitoria.entities.MonitoriaEntity;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author l.mejia
 */
@Stateless
public class MonitoriaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(MonitoriaPersistence.class.getName());
    @PersistenceContext(unitName = "monitoriaPU")
    protected EntityManager em;
    
    /**
     * Crea una entidad monitoria y la almacena en la base de datos
     * @param entity Entidad monitoria a crear
     * @return la entidad creada
     */
    public MonitoriaEntity create(MonitoriaEntity entity){
        LOGGER.info("Creando un Recurso nuevo");
        em.persist(entity);
        LOGGER.info("Creando un Recurso nuevo");
        return entity;
    }
    /**
     * Hace los cambios correspondientes a la monitoria deseada
     * @param entity entidad monitoria a cambiar
     * @return la entidad con los cambios realizados
     */
    public MonitoriaEntity update(MonitoriaEntity entity){
        return em.merge(entity);
    }
    
    /**
     * Busca la entidad de una monitoria
     * @param id id de la monitoria que se desea buscar
     * @return la monitoria con el id dado por parametro
     */
    public MonitoriaEntity find(Long id){
        return em.find(MonitoriaEntity.class,id);
    }
    
    /**
     * busca todos los recursos monitoria en la base de datos
     * @return una lista de los recursos monitoria en la base de datos
     */
    public List<MonitoriaEntity> findAll(){
        Query q = em.createQuery("select u from MonitoriaEntity u");
        return q.getResultList();
    }
    
}
