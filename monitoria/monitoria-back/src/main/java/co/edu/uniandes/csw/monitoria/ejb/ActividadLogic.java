/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;
import co.edu.uniandes.csw.monitoria.entities.ActividadEntity;
import co.edu.uniandes.csw.monitoria.persistence.ActividadPersistence;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
/**
 *
 * @author ca.mendoza
 */
@Stateless
public class ActividadLogic {
    
    @Inject
    private ActividadPersistence persistence;
    
      public ActividadEntity createActividad(ActividadEntity entity)
    {
        persistence.create(entity);
        return entity;
    }
      
    public List<ActividadEntity> getActividades()
    {
        return persistence.findAll();
    }
    
    public ActividadEntity getActividad(Long id) throws WebApplicationException
    {
        LOGGER.info("Inicia el proceso de consulta de una actividad por id");
        ActividadEntity toReturn = persistence.find(id);
        if(toReturn == null)
        {
            throw new WebApplicationException("No existe una actividad con el id: "+id+ " ", 404);
        }
        return persistence.find(id);
    }
    
  
    
    public ActividadEntity updateActividad(ActividadEntity entity)
    {
        LOGGER.info("Inicia el proceso de actualizar una actividad");
        ActividadEntity toUpdate = persistence.find(entity.getId());
        if(toUpdate == null)
        {
            throw new WebApplicationException("No existe una actividad con el id: "+entity.getId()+ " ", 404);
        }
        return persistence.update(entity);
    }
    
    public void deleteActividad(Long id)
    {
        LOGGER.info("Inicia el proceso de borrar una actividad");
        ActividadEntity toDelete = persistence.find(id);
            if(toDelete == null)
        {
            throw new WebApplicationException("No existe una actividad con el id: "+id+ " ", 404);
        }
        LOGGER.info("Se borro la actividad");
        persistence.delete(id);
    }
    
    
}
