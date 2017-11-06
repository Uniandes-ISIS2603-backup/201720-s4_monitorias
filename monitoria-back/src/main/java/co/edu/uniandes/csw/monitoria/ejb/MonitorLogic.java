/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;

import co.edu.uniandes.csw.monitoria.entities.IdiomaEntity;
import co.edu.uniandes.csw.monitoria.entities.MonitorEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.MonitorPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author mf.mena
 */
@Stateless
public class MonitorLogic {
    private static final Logger LOGGER = Logger.getLogger(MonitorLogic.class.getName());

    @Inject
    private MonitorPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

     @Inject
    private IdiomaLogic idiomaLogic;    
     /**
     * Crear un monitor nuevo
     * @param entity
     * @return la entidad ya guardada
     * @throws WebApplicationException
     */
    public MonitorEntity createMonitor(MonitorEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de un monitor"); 
         // Verifica la regla de negocio que dice que todo monitor tiene que tener un codigo
        if (entity.getCodigo() == null) {
            throw new BusinessLogicException("Es necesario llenar el campo Codigo");
        }
         // Verifica la regla de negocio que dice que todo monitor tiene que tener un Tipo
        if (entity.getTipo() == null) {
            throw new BusinessLogicException("Es necesario llenar el campo tipo");
        }
         // Verifica la regla de negocio que dice que no puede haber dos monitore con el mismo codigo      
        if (persistence.findByCodigo(entity.getCodigo())!=null) {
           throw new BusinessLogicException("Ya existe una Monitor con el Codigo");
        }
        
         // Verifica que los idiomas existan      
        if (entity.getIdioma()!=null&& entity.getIdioma().size()>0) {
            List<IdiomaEntity> idiomas=entity.getIdioma();
           for(int i=0;i<idiomas.size();i++){
               existeIdioma(idiomas.get(i));
           }
        }
        
        // Invoca la persistencia para crear el monitor
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Monitor");
        return entity;
    }

    /**
     * Obtener todos los monitores existentes en la base de datos.
     * @return una lista de Monitores.
     */
    public List<MonitorEntity> getMonitores() {
        LOGGER.info("Inicia proceso de consultar todos los monitores");
        List<MonitorEntity> monitores = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las editoriales");
        return monitores;
    }

    /**
     *
     * Obtener un montitor por medio de su codigo.
     * 
     * @param codigo: codigo del monitor para ser buscada.
     * @return El monitor solicitado por medio de su codigo.
     * @throws WebApplicationException: excepcion
     * 
     */
    public MonitorEntity getMonitor(Long codigo)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de consultar monitor con codigo={0}", codigo);
            MonitorEntity monitor = persistence.findByCodigo(codigo);
        if (monitor == null) {
            LOGGER.log(Level.SEVERE, "El monitor con el codigo {0} no existe", codigo);
            throw new BusinessLogicException("No existe objeto Monitor con el CODIGO solicitado");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar monitor con codigo={0}", codigo);
        System.out.println("Encontre la foto en la base de datos ");
        System.out.println("Encontre la foto en la base de datos "+monitor.getFoto());
        return monitor;
    }
    
    

    /**
     *
     * Actualizar un monitor.
     * @param codigo: codigo del monitor para buscarla en la base de datos.
     * @param entity: monitor con los cambios para ser actualizado, por ejemplo el nombre.
     * @return el monitor con los cambios actualizados en la base de datos.
     * @throws WebApplicationException
     */
    public MonitorEntity updateMonitor(Long codigo, MonitorEntity entity)throws WebApplicationException{
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar editorial con id={0}", codigo);
        //Verificar la regla de negocio de que no se puede modificar el codigo de un monitor
        MonitorEntity actual =persistence.findByCodigo(codigo);     
        
        if (actual == null) {
            throw new WebApplicationException("No existe objeto Monitor con el CODIGO solicitado", 404);
        }
        if (entity.getCodigo() != null && actual.getCodigo() != entity.getCodigo()) {
            throw new WebApplicationException("No se puede modificar el id del monitor ", 413);
        }
        if(entity.getFoto()==null)
            entity.setFoto(actual.getFoto());
        if(entity.getNombre()==null)
            entity.setNoombre(actual.getNombre());
        if(entity.getIdioma()==null)
            entity.setIdioma(actual.getIdioma());
        if(entity.getTipo()==null)
            entity.setTipo(actual.getTipo());
         if(entity.getValPromedio()==null)
            entity.setValorPromedio(actual.getValPromedio());
          if(entity.getHorarios()==null)
            entity.setHorarios(actual.getHorarios());
          
        
           
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        MonitorEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar monitor con codigo={0}", entity.getCodigo());
        return newEntity;
    }

    /**
     * Borrar un Monitor
     * @param codigo: codido del monitor a borrar
     */
    public void deleteMonitor(Long codigo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar Monitor con id={0}", codigo);
       MonitorEntity actual =persistence.findByCodigo(codigo);     
        
        if (actual == null) {
            throw new BusinessLogicException("No existe objeto Monitor con el CODIGO solicitado");
        }
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        persistence.delete(actual.getCodigo());
        LOGGER.log(Level.INFO, "Termina proceso de borrar Monitor con id={0}", codigo);
    }
    
    
    public void existeIdioma(IdiomaEntity busqueda) throws WebApplicationException{
        
        IdiomaEntity resultado=null;
         resultado=idiomaLogic.getIdioma(busqueda.getId());
         if(resultado==null)
            throw new WebApplicationException("No Existe el idioma deseado ", 413);
         busqueda.setIdioma(resultado.getIdioma());
         System.out.println("Encuentra el idioma "+busqueda.getIdioma());
        
    }
    
}
