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
        //Verifica la regla de negocio que dice que no puede haber dos Estudiantes con el mismo nombre
        if(persistence.findByName(entity.getName()) != null){
            throw new BusinessLogicException("Ya existe un Estudiante con el nombre \"" + entity.getName()+ "\"");
        }
        
                
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de cantante");
        return entity;
    }
    
    public List<EstudianteEntity> getEstudiantes(){
        LOGGER.info("Inicia el proceso de consultar Estudiantes");
        List<EstudianteEntity> Estudiantes  = persistence.findAll();
        LOGGER.info("Termina el proceso de consultar todos los Estudiantes");
        return Estudiantes;
    }
    
    /**
     * 
     * @param Estudiante trae los datos de el Estudiante que se quiere modificar
     * @return Estudiante ya modificado
     * @throws BusinessLogicException
     * @throws WebApplicationException 
     */
    public EstudianteEntity update(EstudianteEntity Estudiante) throws BusinessLogicException, WebApplicationException{
        EstudianteEntity EstudianteAntigua = persistence.find(Estudiante.getId());
        
        //Valida que el estudiante a modificar si exista en el sistema
        if(EstudianteAntigua == null){
            throw new WebApplicationException("No se encontró ninguna Estudiante con el id: " + Estudiante.getId() + "", 404);
        }
        
        return persistence.update(Estudiante);
    }
    
    public EstudianteEntity findById(Long id)throws WebApplicationException{
        EstudianteEntity EstudianteBuscado = persistence.find(id);
        
        //Valida si existe la Estudiante con el id especificado
        if(EstudianteBuscado == null){
            throw new WebApplicationException("El Estudiante con el id:" + id + "No existe.", 404);
        }
        
        return EstudianteBuscado;
    }
    
    public void delete(Long id) throws WebApplicationException{
        EstudianteEntity EstudianteBuscado = persistence.find(id);
        if(EstudianteBuscado == null){
            throw new WebApplicationException("El Estudiante con el id: " + id + " no existe. ",404);
        }
        persistence.delete(id);
    }
}
