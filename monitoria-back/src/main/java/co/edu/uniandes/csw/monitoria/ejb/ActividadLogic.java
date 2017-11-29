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
import javax.ejb.Stateless;
import javax.inject.Inject;
/**
 *
 * @author ca.mendoza
 */
@Stateless
public class ActividadLogic {
    
   
    
    @Inject
    private ActividadPersistence persistence;
    
    @Inject
    private MonitoriaLogic monitoriaLogic;
    
   
    
      public ActividadEntity createActividad(Long monitoriaId, ActividadEntity entity)  throws BusinessLogicException
    {
       if(persistence.findByTareaAsginada(monitoriaId, entity.getTareaAsignada())!=null)
        {
            throw new BusinessLogicException("La monitoria ya tiene una actividad con esa tarea asignada");
        }
       else
       {
        MonitoriaEntity monitoria = monitoriaLogic.findById(monitoriaId);
        entity.setMonitoria(monitoria);
        return persistence.create(entity);
       }
        
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
    
    public ActividadEntity getActividad(Long idMonitoria, Long id) 
    {
        return persistence.find(idMonitoria, id);
    }
    
  
    
    public ActividadEntity updateActividad(Long idMonitoria, ActividadEntity entity) throws BusinessLogicException
    {

        MonitoriaEntity monitoria = monitoriaLogic.findById(idMonitoria);
        entity.setMonitoria(monitoria);
        return persistence.update(entity);
    }
    
    public void deleteActividad(Long idMonitoria,Long id)
    {

       ActividadEntity old = getActividad(idMonitoria, id);
        persistence.delete(old.getId());
    }
    
    
}