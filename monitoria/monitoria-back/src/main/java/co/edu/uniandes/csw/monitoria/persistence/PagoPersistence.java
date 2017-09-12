/*
 * Clase de persistencia del recurso Valoracion.
 */
package co.edu.uniandes.csw.monitoria.persistence;

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
     * Hace los cambios correspondientes a la Pago deseada
     * @param entity entidad monitoria a cambiar
     * @return la entidad con los cambios realizados
     */
    public PagoEntity update(PagoEntity entity){
        return em.merge(entity);
    }
    
    /**
     * Busca la entidad de un Pago
     * @param id id de la Valoracion que se desea buscar
     * @return la monitoria con el id dado por parametro
     */
    public PagoEntity find(Long id){
        return em.find(PagoEntity.class,id);
    }
    
    /**
     * busca todos los recursos Valoracion en la base de datos
     * @return una lista de los recursos monitoria en la base de datos
     */
    public List<PagoEntity> findAll(){
        TypedQuery query = em.createNamedQuery("delect u from BibliotecaEntity u", PagoEntity.class);
        return query.getResultList();
    }
}
