/*
 * Clase de persistencia del recurso Valoracion.
 */
package co.edu.uniandes.csw.monitoria.persistence;

import co.edu.uniandes.csw.monitoria.entities.ValoracionEntity;
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
public class ValoracionPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(RecursoPersistence.class.getName());
    @PersistenceContext(unitName = "monitoriasPU")
    protected EntityManager em;
    
    /**
     * Crea una entidad Valoracion y la almacena en la base de datos
     * @param entity Entidad Valoracion a crear
     * @return la entidad creada
     */
    public ValoracionEntity create(ValoracionEntity entity){
        LOGGER.info("Creando un Recurso nuevo");
        em.persist(entity);
        LOGGER.info("Creando un Recurso nuevo");
        return entity;
    }
    /**
     * Hace los cambios correspondientes a la Valoracion deseada
     * @param entity entidad monitoria a cambiar
     * @return la entidad con los cambios realizados
     */
    public ValoracionEntity update(ValoracionEntity entity){
        return em.merge(entity);
    }
    
    /**
     * Busca la entidad de una Valoracion
     * @param id id de la Valoracion que se desea buscar
     * @return la monitoria con el id dado por parametro
     */
    public ValoracionEntity find(Long id){
        return em.find(ValoracionEntity.class,id);
    }
    
    /**
     * busca todos los recursos Valoracion en la base de datos
     * @return una lista de los recursos monitoria en la base de datos
     */
    public List<ValoracionEntity> findAll(){
        TypedQuery query = em.createNamedQuery("delect u from BibliotecaEntity u", ValoracionEntity.class);
        return query.getResultList();
    }
}
