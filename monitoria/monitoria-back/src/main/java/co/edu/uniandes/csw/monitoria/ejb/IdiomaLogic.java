/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;
import co.edu.uniandes.csw.monitoria.entities.IdiomaEntity;
import co.edu.uniandes.csw.monitoria.persistence.IdiomaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
/**
 *
 * @author ca.mendoza
 */
@Stateless
public class IdiomaLogic {
    @Inject
    private IdiomaPersistence persistence;
    
    public List<IdiomaEntity> getIdiomas()
    {
        return persistence.findAll();
    }
    
    public IdiomaEntity getIdioma(Long id)
    {
        return persistence.find(id);
    }
    
    public IdiomaEntity createIdioma(IdiomaEntity entity)
    {
        persistence.create(entity);
        return entity;
    }
    
    public IdiomaEntity updateEntity(IdiomaEntity entity)
    {
        return persistence.update(entity);
    }
    
    public void deleteIdioma(Long id)
    {
        persistence.delete(id);
    }

   
}
