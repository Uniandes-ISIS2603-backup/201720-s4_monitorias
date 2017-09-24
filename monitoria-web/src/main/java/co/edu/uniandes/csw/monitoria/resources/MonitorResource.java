/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.resources;

import co.edu.uniandes.csw.monitoria.dtos.MonitorDetailDTO;
import co.edu.uniandes.csw.monitoria.ejb.MonitorLogic;
import co.edu.uniandes.csw.monitoria.entities.MonitorEntity;
import co.edu.uniandes.csw.monitoria.persistence.MonitorPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
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
@Stateless
public class MonitorResource {

    @Inject
    MonitorLogic monitorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(MonitorPersistence.class.getName());

    /**
     * Registrar un nuevo monitor en la base de datos
     *
     * @param monitor correponde a la representación java del objeto json que
     * ingresa. en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java.
     * @throws WebApplicationException
     */
    @POST
    public MonitorDetailDTO createMonitor(MonitorDetailDTO monitor) throws WebApplicationException {
        
        System.out.println("ENTRAAAA");
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        MonitorEntity monitorEntity = monitor.toEntity();
        System.out.println(monitor.getCodigo()+" : el codigo");
        System.out.println(monitor.getId()+" : el id");
        System.out.println(monitor.getName()+" : el Nombre");
        System.out.println(monitor.getValPromedio()+" : el valor");
        System.out.println("EANTRAAAA");
        
        // Invoca la lógica para crear la editorial nueva
        MonitorEntity nuevoMonitor = monitorLogic.createMonitor(monitorEntity);
        System.out.println("conseguido !!!");
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new MonitorDetailDTO(nuevoMonitor);
    }

    /**
     * GET para todos los monitores.
     * @return la lista de todos los monitores en objetos json DTO.
     */
    @GET
    public List<MonitorDetailDTO> getMonitores() {
        return listEntity2DetailDTO(monitorLogic.getMonitores());
    }

    /**
     * GET para un monitor
     * @param codigo corresponde al codigo del monitor que se busca.
     * @return El monitor encontrado. 
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la editorial buscada se retorna un 404 con
     * el mensaje.
     */
    @GET
    @Path("{codigo: \\d+}")
    public MonitorDetailDTO getMonitor(@PathParam("codigo") Long codigo) throws WebApplicationException {
        MonitorEntity entity = monitorLogic.getMonitor(codigo);
        return new MonitorDetailDTO(entity);
    }

    /**
     * PUT 
     * @param codigo corresponde acodigo del monitor a actualizar.
     * @param monitor corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return La editorial actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la editorial a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{codigo: \\d+}")
    public MonitorDetailDTO updateMonitoria(@PathParam("codigo") Long codigo, MonitorDetailDTO monitor) throws WebApplicationException {
        monitor.setCodigo(codigo);        
        return new MonitorDetailDTO(monitorLogic.updateMonitor(codigo, monitor.toEntity()));
    }

    /**
     * DELETE 
     * @param codigo corresponde a el monitor a borrar.
     * @throws WebApplicationException
     * En caso de no existir el id de la editorial a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{codigo: \\d+}")
    public void deleteMonitor(@PathParam("codigo") Long codigo) throws WebApplicationException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una editorial con id {0}", codigo);
        monitorLogic.deleteMonitor(codigo);
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
