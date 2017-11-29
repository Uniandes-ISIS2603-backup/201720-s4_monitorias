/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;

import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;
import co.edu.uniandes.csw.monitoria.entities.SalonEntity;
import co.edu.uniandes.csw.monitoria.entities.SedeEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.HorarioPersistence;
import co.edu.uniandes.csw.monitoria.persistence.SedePersistence;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

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
    @Inject 
    private HorarioPersistence horarioPersistence;

    
     /**
     * Llama a la persistencia para traer las sedes existentes en la base de datos.
     * @return lista de sedes en la base de datos
     * @throws co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException
     */
    public List<SedeEntity> getSedes() throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de consultar todas las sedes");
        List<SedeEntity> sedes = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las sedes");
        if(sedes == null)
        {
            throw new BusinessLogicException("Aún no existen sedes en la base de datos");
        }
        if(sedes.isEmpty())
        {
            throw new BusinessLogicException("Aún no existen sedes en la base de datos");
        }
        
        
        return sedes;
    }
    
     /**
     * Llama a la persitencia para buscar una sede de la base de datos
     * @param id id de la sede que se quiere buscar
     * @return entidad de la sede con ese id
     * @throws WebApplicationException  si no existe la sede
     */
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
/**
 * Llama a la persistencia par crear una nueva sede
 * @param entity
 * @return la sede creada
 * @throws BusinessLogicException 
 */
    public SedeEntity createSede(SedeEntity entity) throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de creación de sede");
        
        verificarReglas(entity);

        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de sede");
        return entity;
    }
    
    /**
     * Llama a la persistencia para editar una sede existente
     * @param id
     * @param entity
     * @return
     * @throws BusinessLogicException 
     */
    public SedeEntity updateSede(Long id, SedeEntity entity) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar sede con id={0}", id);
        
        if(persistence.find(id) == null)
        {
            throw new WebApplicationException("No se encontró ninguna sede con el id: " + id  + "", 404);
        }
        
        verificarReglas(entity);
  
        SedeEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar sede con id={0}", entity.getId());
        return newEntity;
    }
        /**
         *  llama a la persistencia para eliminar una sede
         * @param id 
         */
    public void deleteSede(Long id) 
    {
        
        
        LOGGER.log(Level.INFO, "Inicia proceso de borrar sede con id={0}", id);
        if(persistence.find(id) == null)
        {
            throw new WebApplicationException("La biblioteca con el id: " + id + " no existe. ",404);
        }
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar sede con id={0}", id);
    }
    
    /**
     * Metodo para agregar un horario a un salon de una sede
     * @param pHoraInicio Hora de inicio del horario
     * @param pHoraFin Hora de fin del horario
     * @param pSedeID Id de la sede
     */
    
    public SalonEntity addHorario ( Date pHoraInicio , Date pHoraFin, Long pSedeID)
    {
        //Sede Seleccionada
        SedeEntity pSede = getSede(pSedeID);
        //horario que se desea agregar
        HorarioEntity pHorario = new HorarioEntity();
        pHorario.setHoraInicio(pHoraInicio);
        pHorario.setHoraFin(pHoraFin);
        //Salones de la Sede
        List <SalonEntity> pSalones = pSede.getSalones();
        // Atributoque hace referencia si ya se agrego el horario 
        boolean agregado = false;
        
        // Salon al cual se agrego
        SalonEntity pRespuesta = null;
        // for para recorrer los salones
        for (int i=0; i<pSalones.size() && agregado==false; i++)
        {
            // Salon de la cual se revisaran los horarios 
          SalonEntity pSalon = pSalones.get(i);
          //horarios del salon
          List <HorarioEntity> pHorarios = pSalon.getHorarios();
          // Atributo que hace referencia a si el horario se puede agregar
          boolean agregarHorario = false;
          
          // For para recorrer los horarios del salon
          for (int j=0 ; j < pHorarios.size() && agregarHorario==false ; j++)
          {
              HorarioEntity pHorarioTemp = pHorarios.get(j);
              //que la hora de inicio no este entre otro horario 
              Boolean verificacion1 = (pHoraInicio.after(pHorarioTemp.getHoraInicio())) && (pHoraInicio.before(pHorarioTemp.getHoraFin()));
              // que la hora de fin no este entre otro horario 
              Boolean verificacion2= (pHoraFin.after(pHorarioTemp.getHoraInicio())) && (pHoraFin.before(pHorarioTemp.getHoraFin()));
              if ( !verificacion1 && !verificacion2)    
              {
                  agregarHorario = true;
              }
          }
          
          if (agregarHorario == true)
          {
              pSalon.addHorario(pHorario);
              agregado =true;
              horarioPersistence.create(pHorario);  
             pRespuesta = pSalon;
          }       
        }  
        return pRespuesta;
    }  
    
    
    private void verificarReglas ( SedeEntity entity) throws BusinessLogicException
    {
        //Ver si no hay problema con el nombre 
       if(null != persistence.findByName(entity.getName()))
        {
            throw new BusinessLogicException("Ya existe una sede con el nombre \"" + entity.getName() + "\"");
        }
                if(entity.getName() == null)
        {
            throw new BusinessLogicException("Debe asignarle un nombre a la sede");
        } 
        else if((entity.getName().trim()).equals(""))
        {
            throw new BusinessLogicException("Debe asignarle un nombre a la sede ");
        }
                
        //Revisa q no haya problema con la direccion
        String msgSinDireccion =  "No puede existir una sede sin direccion. ";   
        if( entity.getDireccion()== null)
        {
            throw new BusinessLogicException(msgSinDireccion);
        }
        else if((entity.getDireccion().trim()).equals(""))
        {
            throw new BusinessLogicException(msgSinDireccion);
        }
        if (persistence.findByDireccion(entity.getDireccion()) != null)
        {
            throw new BusinessLogicException("Ya existe una sede con esa direccion \"" + entity.getDireccion()+ "\"");
        }
    }
}
    
    

