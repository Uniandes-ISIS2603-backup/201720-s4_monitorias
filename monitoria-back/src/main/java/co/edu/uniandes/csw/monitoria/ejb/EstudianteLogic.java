/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;
import co.edu.uniandes.csw.monitoria.entities.EstudianteEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.EstudiantePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
/**
 *
 * @author Cristian
 */
@Stateless
public class EstudianteLogic {
      private static final Logger LOGGER = Logger.getLogger(EstudianteLogic.class.getName());
    
    @Inject
    private EstudiantePersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
    public  EstudianteEntity createEstudiante(EstudianteEntity entity) throws BusinessLogicException{
        LOGGER.info("Inicia la creación de una Estudiante");
       if(persistence.findByCodigo(entity.getCodigo())!=null){
            LOGGER.info("EXISTE UN ESTUDIANTE CON ID");
          throw new BusinessLogicException("Ya existe un Estudiante con el id \"" + entity.getCodigo()+ "\"");  
       }
        if(persistence.findByCorreo(entity.getCorreo())!=null){
             LOGGER.info("EXISTE UN ESTUDIANTE CON CORREO");
           throw new BusinessLogicException("Ya existe un Estudiante con el correo \"" + entity.getCorreo()+ "\""); 
       }
        
                
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Estudiante");
        return entity;
    }
    
    public List<EstudianteEntity> getEstudiantes(){
        LOGGER.info("Inicia el proceso de consultar Estudiantes");
        List<EstudianteEntity> estudiantes  = persistence.findAll();
        LOGGER.info("Termina el proceso de consultar todos los Estudiantes");
        return estudiantes;
    }
    
    /**
     * 
     * @param estudiante
     * @return Estudiante ya modificado
     * @throws BusinessLogicException
     * @throws WebApplicationException 
     */
    public EstudianteEntity update(EstudianteEntity estudiante) throws BusinessLogicException{
        EstudianteEntity estudianteAntigua = persistence.findByCodigo(estudiante.getCodigo());
        estudianteAntigua.setName(estudiante.getName());
        estudianteAntigua.setPenalizacion(estudiante.getPenalizacion());
      
        //Valida que el estudiante a modificar si exista en el sistema
        if(estudianteAntigua == null){
            throw new WebApplicationException("No se encontró ninguna Estudiante con el id: " + estudiante.getId() + "", 404);
        }
      else
        return persistence.update(estudianteAntigua);
    }
    
    public EstudianteEntity findById(Long id){
        EstudianteEntity estudianteBuscado = persistence.findById(id);
        
        //Valida si existe la Estudiante con el id especificado
        if(estudianteBuscado == null){
            throw new WebApplicationException("El Estudiante con el id:" + id + "No existe.", 404);
        }
       
        return estudianteBuscado;
    }
    
    
    public void delete(Long id) throws  BusinessLogicException{
         LOGGER.log(Level.INFO, "Inicia proceso de borrar Estudiante con id={0}", id);
         persistence.delete(id);
    }
}
