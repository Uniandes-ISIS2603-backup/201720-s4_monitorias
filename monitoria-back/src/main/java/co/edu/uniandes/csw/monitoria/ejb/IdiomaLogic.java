/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;
import co.edu.uniandes.csw.monitoria.entities.IdiomaEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.IdiomaPersistence;
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
public class IdiomaLogic {
    private static final Logger LOGGER = Logger.getLogger(IdiomaLogic.class.getName());
    @Inject
    private IdiomaPersistence persistence;
    
     public IdiomaEntity createIdioma(IdiomaEntity entity) throws BusinessLogicException
    {   LOGGER.info("Inicia proceso de creacion de un idioma");
        if(persistence.findByName(entity.getIdioma())!=null)
        {
            throw new BusinessLogicException("Ya existe un Idioma con el nombre dado por parametro \"" + entity.getIdioma()+ "\"");
        }
        persistence.create(entity);
        LOGGER.info("Se creo el idioma exitosamente");
        return entity;
    }
    
    public List<IdiomaEntity> getIdiomas()
    {
        LOGGER.info("Inica el proceso de consulta de idiomas");
        List<IdiomaEntity> toReturn = persistence.findAll();
        LOGGER.info("Se consultaron exitosamente los idiomas");
        return toReturn;
    }
    
    public IdiomaEntity getIdioma(Long id)
    {
        LOGGER.info("Inicia el proceso de consulta de idioma por id");
        IdiomaEntity toReturn = persistence.find(id);
        if(toReturn == null)
        {
            throw new WebApplicationException("No existe un idioma con el id: "+id+ " ", 404);
        }
        return toReturn;
    }
    
   
    
    public IdiomaEntity updateIdioma(IdiomaEntity entity)
    {
        IdiomaEntity toUpdate = persistence.find(entity.getId());
        if(toUpdate ==null)
        {
            throw new WebApplicationException("No se puede actualizar el idioma porque no existe", 404);
        }
        else if(!toUpdate.getIdioma().equals(entity.getIdioma()))
        {
            throw new WebApplicationException("No se puede actualizar el idioma porque no se puede modificar el nombre inicial");
        }
        else
        {
        return persistence.update(entity);
        }
    }
    
    public void deleteIdioma(Long id)
    {
        IdiomaEntity toDelete = persistence.find(id);
        if(toDelete ==null)
        {
            throw new WebApplicationException("No se puede eliminar el idioma con id: "+ id +"porque no existe", 404);
        }
        persistence.delete(id);
    }

   
}
