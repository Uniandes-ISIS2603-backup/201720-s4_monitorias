/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;
import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.HorarioPersistence;
import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
/**
 *
 * @author Cristian
 */
@Stateless
public class HorarioLogic {
      private static final Logger LOGGER = Logger.getLogger(HorarioLogic.class.getName());
    
    @Inject
    private HorarioPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
  
    public HorarioEntity create(HorarioEntity entity)throws BusinessLogicException 
     {
        LOGGER.info("Inicia proceso de creación de un horario");
        //Verifica que no esten dos horarios con el mismo id
       
        
        // Invoca la persistencia para crear el horario
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de una horario");
         return entity;
     }
    public List<HorarioEntity> getHorarios() {
        LOGGER.info("Inicia proceso de consultar todos los horarios");
      
        List<HorarioEntity> horarios = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los horarios");
        return horarios;
    }
    public HorarioEntity getHorarioById(Long id) throws BusinessLogicException{
         if(persistence.find(id)==null) 
         {
             throw new BusinessLogicException("No existe un horario con el id dado.");
         }
        return persistence.find(id);
        
    }
     public HorarioEntity updateHorario(Long id, HorarioEntity entity)throws BusinessLogicException
      {
          LOGGER.info("Inicia proceso de actualizar un horario");
          
      
           if(persistence.find(id)==null)
           {
               throw new BusinessLogicException("No existe un horario con el id dado.");
           }
           
          
         
          persistence.update(entity);
          return entity;
      }
   
          public void removeHorario(Long id) throws BusinessLogicException
      {
        
          if (persistence.find(id)==null){
              throw new BusinessLogicException("No existe un horario con el id \"" + id+"\"");
          }
         persistence.delete(id);
      
      }

    /**
     *
     * @param horaInicio
     * @return
     */
    public HorarioEntity findByHorarioInicio(Date horaInicio){
      try{
        return persistence.findByHoraInicio(horaInicio);
      }
      catch(Exception e)
      {
          throw e;
      }
        
}     
         
    
     
    
}

