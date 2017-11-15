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
     * @param Monitoria entidad Monitoria que se quiere modificar
     * @return Monitoria ya modificada
     * @throws BusinessLogicException  
     * @throws WebApplicationException si la monitoria que se quiere modificar no existe en el sistema
     */
    public MonitoriaEntity update(MonitoriaEntity Monitoria) throws BusinessLogicException, WebApplicationException{
        MonitoriaEntity MonitoriaAntigua = persistence.find(Monitoria.getId());
        
        //Valida que el estudiante a modificar si exista en el sistema
        if(Monitoria.getEstudiante().size()>1 && !Monitoria.getTipo().equals("larga")){
            throw  new BusinessLogicException("No se pueden agregar mas estudiantes a esta monitoria monitoria ");
        }        
        else if(Monitoria.getEstudiante().size()>4 && Monitoria.getTipo().equals("larga"))
        {
            throw  new BusinessLogicException("No se pueden agregar mas estudiantes a esta monitoria monitoria ");
        }
        if(MonitoriaAntigua == null){
            throw new WebApplicationException("No se encontró ninguna monitoria con el id: " + Monitoria.getId() + "", 404);
        }
        crearPago(Monitoria);
        return persistence.update(Monitoria);
    }
    public void crearPago(MonitoriaEntity Monitoria)
    {
        if(("dada").equals(Monitoria.getEstado()));
        {
            logicPago.createPago(Monitoria.getIdMonitor(), 1);
        }
    }
    
    
    public MonitoriaEntity findById(Long id)throws WebApplicationException{
        MonitoriaEntity busqueda = persistence.find(id);
        
        //Valida si existe la Estudiante con el id especificado
        if(busqueda == null){
            throw new WebApplicationException("La monitoria con el id:" + id + "No existe.", 404);
        }
        
        return busqueda;
    }
    
}
