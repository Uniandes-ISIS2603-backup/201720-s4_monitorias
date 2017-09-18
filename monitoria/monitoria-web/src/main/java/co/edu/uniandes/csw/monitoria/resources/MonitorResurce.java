/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.resources;

import co.edu.uniandes.csw.monitoria.dtos.MonitorDetailDTO;
import co.edu.uniandes.csw.monitoria.ejb.MonitorLogic;
import co.edu.uniandes.csw.monitoria.entities.MonitorEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.MonitorPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author mf.mena
 */
@Path("monitor")
@Produces("application/json")
@Consumes("application/json")
public class MonitorResurce {
    
    @Inject
    MonitorLogic monitorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(MonitorPersistence.class.getName());
    
    /**
     * POST http://localhost:8080/backstepbystep-web/api/editorials Ejemplo
     * json: { "name":"Norma" }
     *
     * @param editorial correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "editorialDetailDTO", "id": 1, "name": "Norma" }
     * @throws BusinessLogicException
     */
    @POST
    public MonitorDetailDTO createMonitor(MonitorDetailDTO editorial) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        MonitorEntity monitorEntity = editorial.toEntity();
        // Verifica la regla de negocio que dice que todo monitor tiene que tener un codigo
        if (monitorEntity.getCodigo() == null) {
            throw new WebApplicationException("Es necesario llenar el campo Codigo",413);
        }
         // Verifica la regla de negocio que dice que todo monitor tiene que tener un Tipo
        if (monitorEntity.getTipo() == null) {
            throw new WebApplicationException("Es necesario llenar el campo tipo",414);
        }
         // Verifica la regla de negocio que dice que no puede haber dos monitore con el mismo codigo      
         MonitorEntity entity = monitorLogic.getMonitor(monitorEntity.getCodigo());
        if (entity != null) {
           throw new WebApplicationException("Ya existe una Monitor con el Codigo",412);
        }
       
       
        
        // Invoca la lógica para crear la editorial nueva
        MonitorEntity nuevoMonitor =monitorLogic.createMonitor(monitorEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new MonitorDetailDTO(nuevoMonitor);
    }

    /**
     * GET para todas las editoriales.
     * http://localhost:8080/backstepbystep-web/api/editorials
     *
     * @return la lista de todas las editoriales en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<MonitorDetailDTO> getMonitores() throws BusinessLogicException {
        return listEntity2DetailDTO(monitorLogic.getMonitores());
    }

    /**
     * GET para una editorial
     * http://localhost:8080/backstepbystep-web/api/editorials/1
     *
     * @param id corresponde al id de la editorial buscada.
     * @return La editorial encontrada. Ejemplo: { "type": "editorialDetailDTO",
     * "id": 1, "name": "Norma" }
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la editorial buscada se retorna un 404 con
     * el mensaje.
     */
    @GET
    @Path("{codigo: \\d+}")
    public MonitorDetailDTO getMonitor(@PathParam("codigo") Long codigo) throws BusinessLogicException {
        MonitorEntity entity = monitorLogic.getMonitor(codigo);
        if (entity == null) {
            throw new WebApplicationException("No existe objeto Monitor con el CODIGO solicitado", 404);
        }
        return new MonitorDetailDTO(entity);
    }

    /**
     * PUT http://localhost:8080/backstepbystep-web/api/editorials/1 Ejemplo
     * json { "id": 1, "name": "cambio de nombre" }
     *
     * @param id corresponde a la editorial a actualizar.
     * @param editorial corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return La editorial actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la editorial a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{codigo: \\d+}")
    public MonitorDetailDTO updateMonitoria(@PathParam("codigo") Long codigo, MonitorDetailDTO monitor) throws BusinessLogicException {
        monitor.setCodigo(codigo);
        MonitorEntity entity = monitorLogic.getMonitor(codigo);
        if (entity == null) {
            throw new WebApplicationException("No existe objeto Monitor con el CODIGO solicitado", 404);
        }
        if (monitor.getId()!=null&&monitor.getId()!=entity.getId() ){
             throw new WebApplicationException("No se puede modificar el id del monitor ",413);
        }
        return new MonitorDetailDTO(monitorLogic.updateMonitor(codigo, monitor.toEntity()));
    }

    /**
     * DELETE http://localhost:8080/backstepbystep-web/api/editorials/1
     *
     * @param id corresponde a la editorial a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la editorial a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{codigo: \\d+}")
    public void deleteMonitor(@PathParam("codigo") Long codigo) throws WebApplicationException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una editorial con id {0}", codigo);
        MonitorEntity entity = monitorLogic.getMonitor(codigo);
        if (entity == null) {
           throw new WebApplicationException("No existe objeto Monitor con el CODIGO solicitado", 404);
        }
        monitorLogic.deleteMonitor(entity.getId());
    }

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EditorialEntity a una lista de
     * objetos EditorialDetailDTO (json)
     *
     * @param entityList corresponde a la lista de editoriales de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de editoriales en forma DTO (json)
     */
    private List<MonitorDetailDTO> listEntity2DetailDTO(List<MonitorEntity> entityList) {
        List<MonitorDetailDTO> list = new ArrayList<>();
        for (MonitorEntity entity : entityList) {
            list.add(new MonitorDetailDTO(entity));
        }
        return list;
    }

    
}
