/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;

import co.edu.uniandes.csw.monitoria.entities.SalonEntity;
import co.edu.uniandes.csw.monitoria.entities.SedeEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.SalonPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.guzman
 */
@Stateless
public class SalonLogic 
{
      
    private static final Logger LOGGER = Logger.getLogger(SalonLogic.class.getName());
    
    
    @Inject
    private SalonPersistence persistence;
    
    @Inject
    private SedeLogic sedeLogic;
    
    /**
     * Llama a la persistencia para recibir los salones de una sede
     * @param idSede
     * @return
     * @throws BusinessLogicException 
     */
    public List<SalonEntity> getSalons(Long idSede) throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de consultar todos los salones");
        SedeEntity sede = sedeLogic.getSede(idSede);
        if (sede.getSalones() == null)
        {
            throw new BusinessLogicException("La sede que consulta aún no tiene salones");

        }
        if (sede.getSalones().isEmpty())
        {
             throw new BusinessLogicException("La sede que consulta aún no tiene salones");
           
        }
       
        LOGGER.info("Termina proceso de consultar todos los salones");
        return sede.getSalones();
    }

    
    
     /**
     * Llama a el método de la clase persistenca que busca un salon en la base de datos
     * @param sedeId identificador de la sede en la que se encuentra el salon
     * @param salonId identificador del salon que se quiere buscar
     * @return retorna el salon que se encontró
     */
    public SalonEntity getSalon(Long sedeId, Long salonId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar salon con id={0}", salonId);
        return persistence.getSalon(sedeId, salonId);
    }

    
    
        /**
     * Crea un salon
     * @param sedeId id se la sede del salon
     * @param entity entidad salon a crear
     * @return entidad del salon creado
     * @throws BusinessLogicException 
     */
    public SalonEntity createSalon(Long sedeId, SalonEntity entity) throws BusinessLogicException 
    {
        LOGGER.info("Inicia proceso de creación de Salon");
        String localizacion = entity.getLocalizacion();
        
        if(localizacion == null)
        {
            throw new BusinessLogicException("No puede existir un salon sin localizacion. null");
        }
        else if((localizacion.trim()).equals(""))
        {
            throw new BusinessLogicException("No puede existir un salon sin localizacion. vacio");
        }
        SedeEntity sede = sedeLogic.getSede(sedeId);
        if(sede== null)
        {
            throw new BusinessLogicException("No puede existir un salon sin sede.");
        }
        
        entity.setSede(sede);
        entity.setDisponibilidad(Boolean.TRUE);
        //asignar salon a sede :: sede.addSalon()
        
        return persistence.create(entity);
    }

        /**
     * Llama el método update en la clase de persistencia, encargado de modificar los valores del salon
     * @param sedeId número identificador de la sede
     * @param entity representa el salon con los nuevos valores
     * @return retorna el salon con los valores ya modificados
     * @throws co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException
     */
    
    public SalonEntity updateSalon(Long sedeId, SalonEntity entity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar salon con id={0}");
        SedeEntity sede = sedeLogic.getSede(sedeId);
        entity.setSede(sede);
        
        String localizacion = entity.getLocalizacion();
        
        if(localizacion == null)
        {
            throw new BusinessLogicException("No puede existir un salon sin localizacion.");
        }
        else if((localizacion.trim()).equals(""))
        {
            throw new BusinessLogicException("No puede existir un salon sin localizacion.");
        }
        if(sede== null)
        {
            throw new BusinessLogicException("No puede existir un salon sin sede.");
        }

        return persistence.update(entity);
    }
    

    
        /**
     * Elimina una instancia de salon de la base de datos.
     * @param id identificador de la instanci a eliminar
     * @param sedeId identificador de la sede la cual es padre del salon
     * @throws co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException
     */
    public void deleteSalon(Long sedeId, Long id) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar salon con id={0}", id);
        SalonEntity temp = getSalon(sedeId, id);
      if(temp == null)
      {
          throw new BusinessLogicException("No es posible eliminar el salon con id: \"" + id + "\" ya que no existe en la sede con id: \"" + sedeId +"\"");
      }
        
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar salon con id={0}", id);
    }
    
    
}