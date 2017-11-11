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
    
    /*
    *Crea una entidad de tipo monitoria (con crear se refiere a persistirla en la base de datos)
    */
    public MonitoriaEntity createMonitoria(MonitoriaEntity entity)           
    {
        LOGGER.info("Se empieza a crear una monitoria");
        persistence.create(entity);
        LOGGER.info("Se creo la monitoria");
        return entity;
    }
    
    /*
    *Retorna un listado con todas las monitorias en la base de datos
    */
    public List<MonitoriaEntity> getMonitorias(){
        LOGGER.info("Inicia el proceso de consultar Estudiantes");
        List<MonitoriaEntity> monitorias  = persistence.findAll();
        LOGGER.info("Termina el proceso de consultar todos los Estudiantes");
        return monitorias;
    }
    
    /**
     * 
     * @param Monitoria entidad Monitoria que se quiere modificar
     * @return Monitoria ya modificada
     * @throws BusinessLogicException  
     * @throws WebApplicationException si la monitoria que se quiere modificar no existe en el sistema
     */
    public MonitoriaEntity update(MonitoriaEntity Monitoria) throws BusinessLogicException, WebApplicationException{
        MonitoriaEntity MonitoriaAntigua = persistence.find(Monitoria.getId());
        
        //Valida que el estudiante a modificar si exista en el sistema
        if(MonitoriaAntigua == null){
            throw new WebApplicationException("No se encontró ninguna monitoria con el id: " + Monitoria.getId() + "", 404);
        }
        crearPago(Monitoria);
        return persistence.update(Monitoria);
    }
    
    /*
    *Genera el pago para el monitor una vez se da la monitoria
    */
    public void crearPago(MonitoriaEntity Monitoria)
    {
        if(("dada").equals(Monitoria.getEstado()));
        {
            logicPago.createPago(Monitoria.getMonitor().getCodigo(), 1);
        }
    }
    
    /*
    * Retorna la monioria con el id correspondientes
    */
    public MonitoriaEntity findById(Long id)throws WebApplicationException{
        MonitoriaEntity busqueda = persistence.find(id);
        
        //Valida si existe la Estudiante con el id especificado
        if(busqueda == null){
            throw new WebApplicationException("La monitoria con el id:" + id + "No existe.", 404);
        }
        
        return busqueda;
    }
    
}
