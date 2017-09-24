/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;

import co.edu.uniandes.csw.monitoria.entities.SalonEntity;
import co.edu.uniandes.csw.monitoria.entities.SedeEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.SedePersistence;
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
public class SedeLogic 
{
        private static final Logger LOGGER = Logger.getLogger(SedeLogic.class.getName());

    @Inject
    private SedePersistence persistence;

    public List<SedeEntity> getSedes() 
    {
        LOGGER.info("Inicia proceso de consultar todas las sedes");
        List<SedeEntity> sedes = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las sedes");
        return sedes;
    }

    public SedeEntity getSede(Long id)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar sede con id={0}", id);
        SedeEntity sede = persistence.find(id);
        if (sede == null) 
        {
            LOGGER.log(Level.SEVERE, "La sede con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar sede con id={0}", id);
        return sede;
    }

    public SedeEntity createSede(SedeEntity entity) 
    {
        LOGGER.info("Inicia proceso de creación de sede");
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de sede");
        return entity;
    }

    public SedeEntity updateSede(Long id, SedeEntity entity) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar sede con id={0}", id);
        SedeEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar sede con id={0}", entity.getId());
        return newEntity;
    }

    public void deleteSede(Long id) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar sede con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar sede con id={0}", id);
    }
    
    
    public SalonEntity getSalon(Long sedeId, Long salonsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un autor del libro con id = {0}", sedeId);
        List<SalonEntity> list = getSede(sedeId).getSalones();
        SalonEntity salonE = new SalonEntity();
        salonE.setId(salonsId);
        int index = list.indexOf(salonE);
        if (index >= 0) 
        {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un Salon existente a un Sede
     *
     * @param sedeId Identificador de la instancia de Sede
     * @param pSE salon que se agregara
     * @return Instancia de SalonEntity que fue asociada a Sede
     * 
     */
    public SalonEntity addSalon(Long sedeId, SalonEntity pSE) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar un autor al libro con id = {0}", sedeId);
        SedeEntity sedeEntity = getSede(sedeId);
        sedeEntity.getSalones().add(pSE);
        return getSalon(sedeId, pSE.getId());
    }

    /**
     * Remplaza las instancias de Salon asociadas a una instancia de Sede
     *
     * @param sedeId Identificador de la instancia de Sede
     * @param list Colección de instancias de SalonEntity a asociar a instancia
     * de Sede
     * @return Nueva colección de SalonEntity asociada a la instancia de Sede
     * 
     */
    public List<SalonEntity> replaceSalons(Long sedeId, List<SalonEntity> list) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar un autor del libro con id = {0}", sedeId);
        SedeEntity sedeEntity = getSede(sedeId);
        sedeEntity.setSalones(list);
        return sedeEntity.getSalones();
    }

    /**
     * Desasocia un Salon existente de un Sede existente
     *
     * @param sedeId Identificador de la instancia de Sede
     * @param pSE Salon a eliminarse 
     * 
     */
    public void removeSalon(Long sedeId, SalonEntity pSE) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un autor del libro con id = {0}", sedeId);
        SedeEntity entity = getSede(sedeId);
        entity.getSalones().remove(pSE);
    }
}
    
    

