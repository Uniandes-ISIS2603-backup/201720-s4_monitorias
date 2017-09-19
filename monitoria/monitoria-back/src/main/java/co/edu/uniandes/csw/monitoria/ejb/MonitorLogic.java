/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;

import co.edu.uniandes.csw.monitoria.entities.MonitorEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.MonitorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author mf.mena
 */
@Stateless
public class MonitorLogic {
    private static final Logger LOGGER = Logger.getLogger(MonitorLogic.class.getName());

    @Inject
    private MonitorPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

     /**
     * Crear un monitor nuevo
     * @param entity
     * @return la entidad ya guardada
     * @throws BusinessLogicException
     */
    public MonitorEntity createMonitor(MonitorEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de un monitor");               
        // Invoca la persistencia para crear el monitor
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Monitor");
        return entity;
    }

    /**
     * Obtener todos los monitores existentes en la base de datos.
     * @return una lista de Monitores.
     */
    public List<MonitorEntity> getMonitores() {
        LOGGER.info("Inicia proceso de consultar todos los monitores");
        List<MonitorEntity> monitores = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las editoriales");
        return monitores;
    }

    /**
     *
     * Obtener un montitor por medio de su codigo.
     * 
     * @param codigo: codigo del monitor para ser buscada.
     * @return El monitor solicitado por medio de su codigo.
     * @throws co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException: excepcion
     * 
     */
    public MonitorEntity getMonitor(Long codigo) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar monitor con codigo={0}", codigo);
            MonitorEntity monitor = persistence.findByCodigo(codigo);
        if (monitor == null) {
            LOGGER.log(Level.SEVERE, "El monitor con el codigo {0} no existe", codigo);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar monitor con codigo={0}", codigo);
        return monitor;
    }

    /**
     *
     * Actualizar un monitor.
     * @param id: id del monitor para buscarla en la base de datos.
     * @param entity: monitor con los cambios para ser actualizada, por ejemplo el nombre.
     * @return lel monitor con los cambios actualizados en la base de datos.
     * @throws co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException: No se puenden cambiar ni el codigo
     * ni el id del monitor
     */
    public MonitorEntity updateMonitor(Long codigo, MonitorEntity entity)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar editorial con id={0}", codigo);
        //Verificar la regla de negocio de que no se puede modificar el codigo de un monitor
        MonitorEntity actual =persistence.findByCodigo(codigo);           
           
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        MonitorEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar monitor con id={0}", entity.getId());
        return newEntity;
    }

    /**
     * Borrar un Monitor
     * @param id: id de la editorial a borrar
     */
    public void deleteMonitor(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar Monitor con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar Monitor con id={0}", id);
    }
    
}
