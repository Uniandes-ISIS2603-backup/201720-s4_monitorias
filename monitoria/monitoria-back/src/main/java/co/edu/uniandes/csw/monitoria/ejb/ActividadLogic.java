/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;
import co.edu.uniandes.csw.monitoria.entities.ActividadEntity;
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
    
    public List<ActividadEntity> getActividades()
    {
        return persistence.findAll();
    }
    
    public ActividadEntity getActividad(Long id)
    {
        return persistence.find(id);
    }
    
    public ActividadEntity createActividad(ActividadEntity entity)
    {
        persistence.create(entity);
        return entity;
    }
    
    public ActividadEntity updateActividad(ActividadEntity entity)
    {
        return persistence.update(entity);
    }
    
    public void deleteActividad(Long id)
    {
        persistence.delete(id);
    }
    
    
}
