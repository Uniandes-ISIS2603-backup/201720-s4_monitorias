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
         if( entity.getIdioma() == null){
            throw new BusinessLogicException("No se puede crear el idioma sin indicar su nombre");
        }
         else if(persistence.findByName(entity.getIdioma())!=null)
        {
            throw new BusinessLogicException("Ya existe un Idioma con el nombre dado por parametro \"" + entity.getIdioma()+ "\"");
        }
        persistence.create(entity);
        LOGGER.info("Se creo el idioma exitosamente");
        return entity;
    }
    
    public List<IdiomaEntity> getIdiomas() throws BusinessLogicException
    {
        LOGGER.info("Inica el proceso de consulta de idiomas");
        List<IdiomaEntity> toReturn = persistence.findAll();
        LOGGER.info("Se consultaron exitosamente los idiomas");
         if(toReturn == null||toReturn.isEmpty()){
            throw new BusinessLogicException("No existen idiomas.");
        }
        return toReturn;
    }
    
    public IdiomaEntity getIdioma(Long id) throws WebApplicationException
    {
        LOGGER.info("Inicia el proceso de consulta de idioma por id");
        IdiomaEntity toReturn = persistence.find(id);
        if(toReturn == null)
        {
            throw new WebApplicationException("No existe un idioma con el id: "+id+ " ", 404);
        }
        return toReturn;
    }
    
   
    
    public IdiomaEntity updateIdioma(IdiomaEntity idioma)
    {
        IdiomaEntity toUpdate = persistence.find(idioma.getId());
        if(toUpdate ==null)
        {
            throw new WebApplicationException("No se puede actualizar el idioma porque no existe", 404);
        }
        else if(!toUpdate.getIdioma().equals(idioma.getIdioma()))
        {
            throw new WebApplicationException("No se puede actualizar el idioma porque no se puede modificar el nombre inicial");
        }
        else
        {
        return persistence.update(idioma);
        }
    }
    
    public void deleteIdioma(Long id) throws WebApplicationException
    {
        IdiomaEntity toDelete = persistence.find(id);
        if(toDelete ==null)
        {
            throw new WebApplicationException("No se puede eliminar el idioma con id: "+ id +"porque no existe", 404);
        }
        persistence.delete(id);
    }
    
    public IdiomaEntity findByName(IdiomaEntity entity)
    {
       IdiomaEntity resp = persistence.findByName(entity.getIdioma());
       if(resp == null)
       {
           throw new WebApplicationException("No se puede  retornar el idioma con nombre: "+ entity.getIdioma() +"porque no existe", 404);
       }
       return resp ;
                
    }

   
}
