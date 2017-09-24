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

    public List<SalonEntity> getSalons() 
    {
        LOGGER.info("Inicia proceso de consultar todos los salones");
        List<SalonEntity> salones = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los salones");
        return salones;
    }

    public SalonEntity getSalon(Long id) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar salon con id={0}", id);
        SalonEntity salon = persistence.find(id);
        if (salon == null) 
        {
            LOGGER.log(Level.SEVERE, "El salon con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar salon con id={0}", id);
        return salon;
    }

    public SalonEntity createSalon(SalonEntity entity) throws BusinessLogicException 
    {
        LOGGER.info("Inicia proceso de creación de Salon");
        if (entity.getSede()==null) 
        {
          throw new BusinessLogicException("No tiene sede es inválido");
        }
        SedeEntity pSE = sedeLogic.getSede(entity.getIdSede());
                
        if (pSE==null)
        {
            throw new BusinessLogicException("La sede no existe");
        }
        else
        {
            entity.setSede(pSE);
        }
        persistence.create(entity);
        sedeLogic.addSalon(pSE.getId(), entity);
        LOGGER.info("Termina proceso de creación de Salon");
        return entity;
    }

    public SalonEntity updateSalon(Long id, SalonEntity entity) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar salon con id={0}", id);
        
        if (entity.getIdSede()!=getSalon(id).getIdSede()) 
        {
            throw new BusinessLogicException("No puede cambiar la sede del salon");
        }
        SalonEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar salon con id={0}", entity.getId());
        return newEntity;
    }

    public void deleteSalon(Long id) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar salon con id={0}", id);
        SalonEntity temp = getSalon(id);
        sedeLogic.removeSalon(temp.getIdSede(), temp);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar salon con id={0}", id);
    }
    
}