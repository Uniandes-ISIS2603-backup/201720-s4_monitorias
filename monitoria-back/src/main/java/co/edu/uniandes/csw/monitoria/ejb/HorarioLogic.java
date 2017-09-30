/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;
import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;
import co.edu.uniandes.csw.monitoria.entities.SalonEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.HorarioPersistence;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
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
    @Inject 
    private SalonLogic salon;
    public HorarioEntity create(HorarioEntity entity)throws BusinessLogicException 
     {
        LOGGER.info("Inicia proceso de creación de un horario");
        //Verifica que no esten dos horarios con el mismo id
        if(persistence.find(entity.getId())!=null){
            throw new BusinessLogicException("No pueden existir dos horarios con el mismo id ( " + entity.getId()+ " )");
        }
        
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
         LOGGER.info("Inicia proceso de eliminar un horario");
          if (persistence.find(id)==null) throw new BusinessLogicException("No existe un horario con el id \"" + id+"\"");
         persistence.delete(id);
         LOGGER.info("Termina proceso de eliminar un horario");  
      }
          
         
             public HorarioEntity agregarHorarioOcupado(HorarioEntity pHorario, Long idSede) throws BusinessLogicException
    {
        boolean encontrado= false;
        List<SalonEntity> salones=salon.getSalons(idSede);
       HorarioEntity horarioRespuesta=pHorario;
        for(int i=0;i<salones.size()&&encontrado==false;i++)
        {
            SalonEntity salon= salones.get(i);
           List<HorarioEntity> horariosAtencion= salon.getHorariosAtencion();
           List<HorarioEntity> horarioOcupados=salon.getHorariosMonitoria();
           
           for(HorarioEntity horarioat:horariosAtencion){
           for(HorarioEntity horario:horarioOcupados)
        {
            if(pHorario.getHoraInicio().after(horarioat.getHoraInicio())==true&&pHorario.getHoraFin().before(horarioat.getHoraFin())==true&&horario.getHoraInicio().compareTo(pHorario.getHoraInicio())!=0&&horario.getHoraInicio().compareTo(pHorario.getHoraFin())!=0&&pHorario.getHoraInicio().after(horario.getHoraInicio())==false&&pHorario.getHoraInicio().before(horario.getHoraFin())==true&&pHorario.getHoraFin().before(horario.getHoraFin())==false&&pHorario.getHoraFin().before(horario.getHoraInicio())==true){
                pHorario.setSalon(salon);
                horarioRespuesta.setSalon(salon);
                horarioOcupados.add(pHorario);
                encontrado=true;
        }
            else
            {
               throw new BusinessLogicException("No se puede agregar este horario,el espacio esta ocupado.");
            }
        }
        }
        
        }
       return horarioRespuesta;
    }
     
    
}

