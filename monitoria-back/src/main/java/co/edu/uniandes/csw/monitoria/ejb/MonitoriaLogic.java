/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;

import co.edu.uniandes.csw.monitoria.entities.MonitoriaEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.MonitoriaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;


/**
 *
 * @author l.mejia
 */
public class MonitoriaLogic {
    private static final Logger LOGGER = Logger.getLogger(MonitoriaLogic.class.getName());
    
    @Inject
    private MonitoriaPersistence persistence;
    @Inject
    private PagoLogic logicPago;
    
    public MonitoriaEntity createMonitoria(MonitoriaEntity entity)           
    {
        LOGGER.info("Se empieza a crear una monitoria");
        persistence.create(entity);
        LOGGER.info("Se creo la monitoria");
        return entity;
    }
    
    
    public List<MonitoriaEntity> getMonitorias(){
        LOGGER.info("Inicia el proceso de consultar Estudiantes");
        List<MonitoriaEntity> monitorias  = persistence.findAll();
        LOGGER.info("Termina el proceso de consultar todos los Estudiantes");
        return monitorias;
    }
    
    /**
     * 
     * @param monitoria entidad Monitoria que se quiere modificar
     * @return Monitoria ya modificada
     * @throws BusinessLogicException  
     * @throws WebApplicationException si la monitoria que se quiere modificar no existe en el sistema
     */
    public MonitoriaEntity update(MonitoriaEntity monitoria) throws BusinessLogicException{
        MonitoriaEntity monitoriaAntigua = persistence.find(monitoria.getId());
        
        //Valida que el estudiante a modificar si exista en el sistema
        if(monitoriaAntigua == null){
            throw new BusinessLogicException("No se encontró ninguna monitoria con el id: " + monitoria.getId() + "");
        }
            crearPago(monitoria);
        return persistence.update(monitoria);
    }
    public void crearPago(MonitoriaEntity monitoria)
    {
        if(("dada").equals(monitoria.getEstado())) {
            logicPago.createPago(monitoria.getIdMonitor(), 1);
        }
    }
    
    public MonitoriaEntity findById(Long id)throws BusinessLogicException{
        MonitoriaEntity busqueda = persistence.find(id);
        
        //Valida si existe la Estudiante con el id especificado
        if(busqueda == null){
            throw new BusinessLogicException("La monitoria con el id:" + id + "No existe.");
        }
        
        return busqueda;
    }
    
}
