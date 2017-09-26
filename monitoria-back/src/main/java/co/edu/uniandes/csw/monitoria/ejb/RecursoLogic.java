/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;

import co.edu.uniandes.csw.monitoria.entities.BibliotecaEntity;
import co.edu.uniandes.csw.monitoria.entities.IdiomaEntity;
import co.edu.uniandes.csw.monitoria.entities.RecursoEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.IdiomaPersistence;
import co.edu.uniandes.csw.monitoria.persistence.RecursoPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ms.osorio
 */
@Stateless

public class RecursoLogic {
    
    private static final Logger LOGGER = Logger.getLogger(RecursoLogic.class.getName()); 
    
    @Inject
    private RecursoPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
    @Inject
    private BibliotecaLogic bibliotecaLogic;
    
    @Inject
    private IdiomaPersistence idiomaPersistence;
    
   
    /**
     * Crea un Recurso
     * @param bibliotecaId
     * @param entity
     * @return entidad del recurso creado
     * @throws BusinessLogicException 
     */
    public RecursoEntity createRecurso(Long bibliotecaId, RecursoEntity entity) throws BusinessLogicException{
        LOGGER.info("Inicia proceso de creación de  un Recurso");
        String name = entity.getName();
        
        if(name == null){
            throw new BusinessLogicException("No puede existir un recurso sin nombre. Debe asignarle uno");
        }else if((name.trim()).equals("")){
            throw new BusinessLogicException("No puede existir un recurso sin nombre. Debe asignarle uno");
        }
        
        if(entity.getIdioma() == null){
            throw new BusinessLogicException("No puede existir un recurso sin idioma. Debe asignarle uno");
        }
        
        IdiomaEntity idioma = idiomaPersistence.findByName(entity.getIdioma().getIdioma());
        if(idioma == null){
            throw new BusinessLogicException("El idioma \"" + entity.getIdioma().getIdioma() + "\" no existe");
        }
         
        BibliotecaEntity biblioteca = bibliotecaLogic.getBiblioteca(bibliotecaId);
        entity.setBiblioteca(biblioteca);
        entity.setDisponibilidad(Boolean.TRUE);
        entity.setIdioma(idioma);
        return persistence.createRecurso(entity);
    }
    
    /**
     * Llama a la persistencia para traer los recursos que esten en la base de datos
     * de los cuales la biblioteca es dueña
     * @param idBiblioteca identificador de la biblioteca 
     * @return Lista de recursos que pertenecen a esa biblioteca
     * @throws BusinessLogicException si la biblioteca no existe 
     */
    public List<RecursoEntity> getRecursos(Long idBiblioteca) throws BusinessLogicException{
        LOGGER.info("Inicia proceso de consultar todos los recursos");
        BibliotecaEntity biblioteca = bibliotecaLogic.getBiblioteca(idBiblioteca);
       if(biblioteca.getRecursos() == null){
            throw new BusinessLogicException("La biblioteca que consulta aún no tiene recursos");
        }
       
       if(biblioteca.getRecursos().isEmpty()){
            throw new BusinessLogicException("La biblioteca que consulta aún no tiene recursos");
       }
             
        return biblioteca.getRecursos();
    }
    
    /**
     * Llama el método update en la clase de persistencia, encargado de modificar los valores del recurso
     * @param bibliotecaId número identificador de la biblioteca
     * @param recurso representa el recurso con los nuevos valores
     * @return retorna el recurso con los valores ya modificados
     */
    public RecursoEntity updateRecurso(Long bibliotecaId,RecursoEntity recurso) throws BusinessLogicException{
     LOGGER.info("inicia proceso de actualizar un recurso");
     BibliotecaEntity biblioteca = bibliotecaLogic.getBiblioteca(bibliotecaId);
     
     recurso.setBiblioteca(biblioteca);
     String name = recurso.getName();
     if(name == null){
            throw new BusinessLogicException("No puede existir un recurso sin nombre. Debe asignarle uno");
        }else if((name.trim()).equals("")){
            throw new BusinessLogicException("No puede existir un recurso sin nombre. Debe asignarle uno");
        }
    // IdiomaEntity idioma = idiomaLogic.getIdioma(recurso.getIdioma().getId());
     
    // if(idioma == null){
       //  throw new BusinessLogicException("No existe el idioma con el id: \"" + recurso.getIdioma().getId()+"\"");
     //}
     
     return persistence.updateRecurso(recurso);
    }
    
    /**
     * Llama a el método de la clase persistenca que busca un recurso en la base de datos
     * @param bibliotecaId identificador de la biblioteca en la que se encuentra el recurso
     * @param recursoId identificador del recurso que se quiere buscar
     * @return retorna el recurso que se encontró
     */
    public RecursoEntity getRecurso(Long bibliotecaId ,Long recursoId){
        
       return persistence.getRecurso(bibliotecaId, recursoId);
    }
    
    /**
     * Elimina una instancia de Review de la base de datos.
     * @param id identificador de la instanci a eliminar
     * @param bibliotecaId identificador de la biblioeca la cual es padre del Recurso
     */
    public void deleteRecurso(Long bibliotecaId,Long id) throws BusinessLogicException{
      LOGGER.info("inicia proceso de borrar Recurso");
      RecursoEntity old = getRecurso(bibliotecaId,id);
      if(old == null){
          throw new BusinessLogicException("No es posible eliminar el recurso con id: \"" + id + "\" ya que no existe en la biblioteca con id: \"" + bibliotecaId +"\"");
      }
        persistence.deleteRecurso(old.getId());
    }
    
}
