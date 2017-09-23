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
    
    @Inject
    private ValoracionPersistence persistence;
    
    public ValoracionEntity createValoracion(ValoracionEntity entity)           
    {
        LOGGER.info("Se empieza a crear una Valoracion");
        persistence.create(entity);
        LOGGER.info("Se creo la Valoracion");
        return entity;
    }
    
    
    public List<ValoracionEntity> getValoraciones(){
        LOGGER.info("Inicia el proceso de consultar Estudiantes");
        List<ValoracionEntity> Estudiantes  = persistence.findAll();
        LOGGER.info("Termina el proceso de consultar todos los Estudiantes");
        return Estudiantes;
    }
    
    /**
     * 
     * @param Valoracion entidad valoracion que se quiere modificar
     * @return Monitoria ya modificada
     * @throws BusinessLogicException  
     * @throws WebApplicationException si la monitoria que se quiere modificar no existe en el sistema
     */
    public ValoracionEntity update(ValoracionEntity Valoracion) throws BusinessLogicException, WebApplicationException{
        ValoracionEntity modificar = persistence.find(Valoracion.getId());
        
        //Valida que el estudiante a modificar si exista en el sistema
        if(modificar == null){
            throw new WebApplicationException("No se encontró ninguna monitoria con el id: " + Valoracion.getId() + "", 404);
        }
        
        return persistence.update(Valoracion);
    }
    
    public ValoracionEntity findById(Long id)throws WebApplicationException{
        ValoracionEntity busqueda = persistence.find(id);
        
        //Valida si existe la Estudiante con el id especificado
        if(busqueda == null){
            throw new WebApplicationException("La valoracion con el id:" + id + "No existe.", 404);
        }
        
        return busqueda;
    }
}
