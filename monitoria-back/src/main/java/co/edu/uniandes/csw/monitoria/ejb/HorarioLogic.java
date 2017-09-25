/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;
import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.HorarioPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
/**
 *
 * @author Cristian
 */
@Stateless
public class HorarioLogic {
      private static final Logger LOGGER = Logger.getLogger(HorarioLogic.class.getName());
    
    @Inject
    private HorarioPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
    public  HorarioEntity createHorario(HorarioEntity entity) throws BusinessLogicException{
        LOGGER.info("Inicia la creación de una Horario");
        String aux1= entity.getHoraFin();
        String aux2=entity.getHoraInicio();
        HorarioEntity ent= persistence.findHorarioFin(aux1);
         HorarioEntity ent2= persistence.findHorarioInicio(aux2);
        if(ent==null||ent2==null){
            throw new WebApplicationException("Ya existe un horario con esa fecha indicada");
        }
        else{
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de cantante");
        }
        return entity;
        
    }
    
    public List<HorarioEntity> getHorarios(){
        LOGGER.info("Inicia el proceso de consultar Horarios");
        List<HorarioEntity> Horarios  = persistence.findAll();
        LOGGER.info("Termina el proceso de consultar todos los Horarios");
        return Horarios;
    }
    
    /**
     * 
     * @param Horario trae los datos de el Horario que se quiere modificar
     * @return Horario ya modificado
     * @throws BusinessLogicException
     * @throws WebApplicationException 
     */
    public HorarioEntity update(HorarioEntity Horario) throws BusinessLogicException, WebApplicationException{
        HorarioEntity HorarioAntigua = persistence.find(Horario.getId());
        
        //Valida que el horario a modificar si exista en el sistema
        if(HorarioAntigua == null){
            throw new WebApplicationException("No se encontró ninguna Horario con el id: " + Horario.getId() + "", 404);
        }
        
        return persistence.update(Horario);
    }
    
    public HorarioEntity findById(Long id)throws WebApplicationException{
        HorarioEntity HorarioBuscado = persistence.find(id);
        
        //Valida si existe la Horario con el id especificado
        if(HorarioBuscado == null){
            throw new WebApplicationException("El Horario con el id:" + id + "No existe.", 404);
        }
        
        return HorarioBuscado;
    }
    
    public void delete(Long id) throws WebApplicationException{
        HorarioEntity HorarioBuscado = persistence.find(id);
        if(HorarioBuscado == null){
            throw new WebApplicationException("El Horario con el id: " + id + " no existe. ",404);
        }
        persistence.delete(id);
    }
}

