/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;
import co.edu.uniandes.csw.monitoria.entities.ActividadEntity;
import co.edu.uniandes.csw.monitoria.entities.MonitoriaEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.ActividadPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
/**
 *
 * @author ca.mendoza
 */
@Stateless
public class ActividadLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ActividadLogic.class.getName());
    
    @Inject
    private ActividadPersistence persistence;
    
    @Inject
    private MonitoriaLogic monitoriaLogic;
    
   
    
      public ActividadEntity createActividad(Long monitoriaId, ActividadEntity entity)  throws WebApplicationException, BusinessLogicException
    {
//        ActividadEntity buscada = persistence.findByTareaAsginada(monitoriaId, entity.getTareaAsignada());
//        if(buscada!=null)
//        {
//            throw new WebApplicationException("Ya existe una actividad con esa tarea asignada.", 404);
//        }
        MonitoriaEntity monitoria = monitoriaLogic.findById(monitoriaId);
        entity.setMonitoria(monitoria);
        return persistence.create(entity);
        
    }
      
    public List<ActividadEntity> getActividades(Long monitoriaId) throws BusinessLogicException
    {
        MonitoriaEntity monitoria = monitoriaLogic.findById(monitoriaId);
        if (monitoria.getActividades() == null) {
            throw new BusinessLogicException("La monitoria no tiene actividades");
        }
        if (monitoria.getActividades().isEmpty()) {
            throw new BusinessLogicException("La monitoria aun no tiene actividades");
        }
        return monitoria.getActividades();
    }
    
    public ActividadEntity getActividad(Long idMonitoria, Long id) throws WebApplicationException
    {
        return persistence.find(idMonitoria, id);
    }
    
  
    
    public ActividadEntity updateActividad(Long idMonitoria, ActividadEntity entity) throws BusinessLogicException
    {
        LOGGER.info("Inicia el proceso de actualizar una actividad");
//        ActividadEntity toUpdate = persistence.find(idMonitoria, entity.getId());
//        if(toUpdate == null)
//        {
//            throw new WebApplicationException("No existe una actividad con el id: "+entity.getId()+ " ", 404);
//        }
//        if(entity.getDescripcion()==null||entity.getTareaAsignada()==null)
//        {
//            throw new WebApplicationException("Nopueden haber campos vacios");
//        }
        MonitoriaEntity monitoria = monitoriaLogic.findById(idMonitoria);
        entity.setMonitoria(monitoria);
        return persistence.update(entity);
    }
    
    public void deleteActividad(Long idMonitoria,Long id)
    {
//        LOGGER.info("Inicia el proceso de borrar una actividad");
//        ActividadEntity toDelete = persistence.find(idMonitoria, id);
//            if(toDelete == null)
//        {
//            throw new WebApplicationException("No existe una actividad con el id: "+id+ " ", 404);
//        }
       ActividadEntity old = getActividad(idMonitoria, id);
        persistence.delete(old.getId());
    }
    
    
}
