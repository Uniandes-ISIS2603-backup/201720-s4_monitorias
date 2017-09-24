/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;

import co.edu.uniandes.csw.monitoria.entities.BibliotecaEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.BibliotecaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author ms.osorio
 */
@Stateless
public class BibliotecaLogic {
    private static final Logger LOGGER = Logger.getLogger(BibliotecaLogic.class.getName());
    
    @Inject
    private BibliotecaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
    /***
     * Se encarga de validar las reglas de negocio para crear una biblioteca 
     * y  llama a la persistencia para crear la biblioteca
     * 
     * @param entity
     * @return
     * @throws BusinessLogicException 
     */
    public  BibliotecaEntity createBiblioteca(BibliotecaEntity entity) throws BusinessLogicException{
        LOGGER.info("Inicia la creación de una biblioteca");
        //Verifica la regla de negocio que dice que no puede haber dos bibliotecas con el mismo nombre
        LOGGER.info(entity.getName());
        if(persistence.findByName(entity.getName()) != null){
            throw new BusinessLogicException("Ya existe una biblioteca con el nombre \"" + entity.getName()+ "\"");
        }
        //Verifica la regla de negocio que dice que no puede haber dos bibliotecas con la misma ubicación
        else if(persistence.findByDireccion(entity.getUbicacion()) != null){
            throw new BusinessLogicException("Ya existe una biblioteca con esa ubicación \"" + entity.getUbicacion()+ "\"");
        }
                
        persistence.createBiblioteca(entity);
        LOGGER.info("Termina proceso de creación de cantante");
        return entity;
    }
    
    /**
     * Llama a la persistencia para traer las bibliotecas existentes en la base de datos.
     * @return lista de bibliotecas en la base de datos
     */
    public List<BibliotecaEntity> getBibliotecas(){
        LOGGER.info("Inicia el proceso de consultar Bibliotecas");
        List<BibliotecaEntity> bibliotecas  = persistence.findAll();
        LOGGER.info("Termina el proceso de consultar todas las Bibliotecas");
        return bibliotecas;
    }
    
    /**
     * Llama el método updateBiblioteca en la clase de persistencia, encargado de modificar los valores de la biblioteca
     * @param biblioteca trae los datos de la biblioteca que se quiere modificar
     * @return Biblioteca ya modificada
     * @throws BusinessLogicException
     * @throws WebApplicationException 
     */
    public BibliotecaEntity updateBiblioteca(BibliotecaEntity biblioteca) throws BusinessLogicException{
        BibliotecaEntity bibliotecaAntigua = persistence.find(biblioteca.getId());
        
        //Valida que la bibioteca a modificar si exista en el sistema
        if(bibliotecaAntigua == null){
            throw new WebApplicationException("No se encontró ninguna biblioteca con el id: " + biblioteca.getId() + "", 404);
        }
        
        return persistence.updateBiblioteca(biblioteca);
    }
    
    /**
     * Llama a la persitencia para buscar una biblioteca de la base de datos
     * @param id id de la biblioteca que se quiere buscar
     * @return Datos de la biblioteca con ese id
     * @throws WebApplicationException  
     */
    public BibliotecaEntity getBiblioteca(Long id){
        BibliotecaEntity bibliotecaBuscada = persistence.find(id);
        
        //Valida si existe la biblioteca con el id especificado
        if(bibliotecaBuscada == null){
            throw new WebApplicationException("La biblioteca con el id:" + id + "No existe.", 404);
        }
        
        return bibliotecaBuscada;
    }
    
    /**
     * Llama al método de eliminar biblioteca
     * @param id identificador de la biblioteca que se quiere eliminar
     * @throws WebApplicationException 
     */
    public void deleteBiblioteca(Long id){
        BibliotecaEntity bibliotecaBuscada = persistence.find(id);
        if(bibliotecaBuscada == null){
            throw new WebApplicationException("La biblioteca con el id: " + id + " no existe. ",404);
        }
        persistence.deleteBiblioteca(id);
    }
    
    /**
     * Valida si la biblioteca existe en la base de datos
     * @param entity objeto que representa a la biblioteca
     * @param id id de la biblioteca que se quiere buscar 
     */
    public void validarExistencia(BibliotecaEntity entity, Long id){
        if (entity == null) {
            throw new WebApplicationException("El recurso /biblioteca/" + id + "/recursos no existe.", 404);
        }
    }
}
