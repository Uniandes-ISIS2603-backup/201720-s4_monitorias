/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;
import co.edu.uniandes.csw.monitoria.entities.ValoracionEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.ValoracionPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
/**
 *
 * @author Mafe
 */
@Stateless
public class ValoracionLogic {
    private static final Logger LOGGER = Logger.getLogger(ValoracionLogic.class.getName());
    
    
    private ValoracionPersistence persistence;
    

    
    private MonitorLogic monitorLogic;
    
    @Inject
    public ValoracionLogic(ValoracionPersistence persistence,MonitorLogic monitorLogic)
    {
        this.persistence=persistence;
        this.monitorLogic=monitorLogic;        
    }
    public ValoracionLogic(  )
    {
        this.persistence=null;
        this.monitorLogic=null;        
    }
    
    public ValoracionEntity createValoracion(ValoracionEntity entity)throws BusinessLogicException           

    {
        LOGGER.info("Se empieza a crear una Valoracion");
        persistence.create(entity);
        LOGGER.info("Se creo la Valoracion");
        
        LOGGER.info("Entra el monitor"+entity.getMonitor().getCodigo());
        monitorLogic.calcularPromedio(entity.getMonitor());
        return entity;
    }
    
    
    public List<ValoracionEntity> getValoraciones(){
        LOGGER.info("Inicia el proceso de consultar Valoraciones");
        List<ValoracionEntity> valoraciones  = persistence.findAll();
        LOGGER.info("Termina el proceso de consultar todos los Valoraciones");
        return valoraciones;
    }
    
    /**
     * 
     * @param Valoracion entidad valoracion que se quiere modificar
     * @return Monitoria ya modificada
     * @throws BusinessLogicException  
     * @throws WebApplicationException si la monitoria que se quiere modificar no existe en el sistema
     */
    public ValoracionEntity update(ValoracionEntity valoracion) throws BusinessLogicException{
        ValoracionEntity modificar = persistence.find(valoracion.getId());
        
        //Valida que el estudiante a modificar si exista en el sistema
        if(modificar == null){
            throw new WebApplicationException("No se encontró ninguna monitoria con el id: " + valoracion.getId() + "", 404);
        }
        
        return persistence.update(valoracion);
    }
    
    public ValoracionEntity findById(Long id)throws BusinessLogicException{
        ValoracionEntity busqueda = persistence.find(id);
        
        //Valida si existe la Estudiante con el id especificado
        if(busqueda == null){
            throw new WebApplicationException("La valoracion con el id:" + id + "No existe.", 404);
        }
        
        return busqueda;
    }
    public void deleteValoracion(Long id) 
    {
        if(persistence.find(id) == null)
        {
            throw new WebApplicationException("La valoracion con el id: " + id + " no existe. ",404);
        }
        persistence.delete(id);
  
    }
}
