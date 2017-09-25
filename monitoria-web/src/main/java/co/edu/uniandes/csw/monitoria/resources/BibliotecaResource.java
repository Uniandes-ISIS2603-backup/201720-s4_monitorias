/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.resources;

import co.edu.uniandes.csw.monitoria.dtos.BibliotecaDetailDTO;
import co.edu.uniandes.csw.monitoria.ejb.BibliotecaLogic;
import co.edu.uniandes.csw.monitoria.entities.BibliotecaEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.BibliotecaPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author ms.osorio
 */
@Path("bibliotecas")
@Consumes("application/json")
@Produces("application/json")
@Stateless
@Dependent
public class BibliotecaResource {
    /**
     * Atributo que conecta el resource con logica.
     * Representa una biblioteca
     */
    @Inject 
    BibliotecaLogic bibliotecaLogic;
    
    @Inject
    RecursoResource recurso;
    
    private static final Logger LOGGER = Logger.getLogger(BibliotecaPersistence.class.getName()); // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * POST http://localhost:8080/monitorias-web/api/bibliotecas Ejemplo
     * json {"name": "Ramón Zubiria", "Ubicacion": "Uniandes"}
     * @param biblioteca corresponde a la representacion java del objeto json
     * enviado en el llamado
     * @return Devuelve el objeto json de entrada que contiene el id creado por la base de datos y el tipo del objeto java. 
     * Ejemplo: {"Type": "BibliotecaDetailDTO", "id": 1, "name": "Ramón Zubiria", "Ubicaion": "Uniandes"}
     * @throws BusinessLogicException 
     */
    @POST
    public BibliotecaDetailDTO createBiblioteca(BibliotecaDetailDTO biblioteca) throws BusinessLogicException{
        
    BibliotecaEntity bibliotecaEntity = biblioteca.toEntity();
    BibliotecaEntity nuevaBiblioteca = bibliotecaLogic.createBiblioteca(bibliotecaEntity);
        return new BibliotecaDetailDTO(nuevaBiblioteca);
    }
    
    /**
     * Get para todas las bibliotecas
     * http://localhost:8080/monitorias-web/api/bibliotecas
     * @return la lista de todas las bibliotecas en objetos json DTO.
     * @throws BusinessLogicException 
     */
    @GET
    public List<BibliotecaDetailDTO> getBiblioteca() throws BusinessLogicException{
        return listEntity2DetailDTO(bibliotecaLogic.getBibliotecas());
    }
    /**
     * GET para una biblioteca
     * http://localhost:8080/monitorias-web/api/bibliotecas/1
     * 
     * @param id corresponde al id de la biblioteca buscada
     * @return la biblioteca encontrada Ejemplo {"Type": "BibliotecaDetailDTO", "id":1,"name": "Ramón Zubiria", "Ubicación": uniandes}
     * 
     * @throws BusinessLogicException 
     * 
     * En caso de no existir el id de la biblioteca buscada se retorna un 404 con el mensaje
     */
    
    @GET
    @Path("{id: \\d+}")
    public BibliotecaDetailDTO getBiblioteca(@PathParam("id") Long id)throws BusinessLogicException{
        return new BibliotecaDetailDTO(bibliotecaLogic.getBiblioteca(id));
    }
    /**
     * PUT http://localhost:8080/monitorias-web/api/bibliotecas/1 Ejemplo
     * json{"id":1, "name": "Ramon de zubiria", "Ubicacion" : "Uniandes }
     * @param id corresponde a la biblioteca a actualizar
     * @param biblioteca corresponde a el objeto con los cambios que se van a realizar
     * @return La biblioteca atualizada
     * @throws BusinessLogicException 
     * En cas de no existir el id de la biblioteca a actualizar se retorna un 404 con el mensaje.
     */
    
    @PUT
    @Path("{id: \\d+}")
    public BibliotecaDetailDTO updateBiblioteca(@PathParam("id") Long id, BibliotecaDetailDTO biblioteca) throws BusinessLogicException{
        biblioteca.setId(id);
        BibliotecaEntity bibliotecaEntity = biblioteca.toEntity();
        return new BibliotecaDetailDTO(bibliotecaLogic.updateBiblioteca(bibliotecaEntity));
    }
    
    /**
     * DELETE http://localhost:8080/monitorias-web/api/bibliotecas/1
     * 
     * @param id corresponde a la biblioteca a borrar.
     * @throws BusinessLogicException 
     * 
     * En caso de no existir el id de la biblioteca a actualizar se retorna un 404 con el mensaje.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void delteBiblioteca(@PathParam("id") Long id) throws BusinessLogicException{
        bibliotecaLogic.deleteBiblioteca(id);
    }
    
    /**
     * Retorna los recursos de la biblioteca
     * @param idBiblioteca id de la instancia biblioteca la cual es padre de los recursos
     * @return lista de recursos de la biblioteca
     */
    @Path("{idBiblioteca: \\d+}/recursos")
    public Class<RecursoResource> getRecursosResources(@PathParam("idBiblioteca") Long idBiblioteca){
        BibliotecaEntity entity = bibliotecaLogic.getBiblioteca(idBiblioteca);
        bibliotecaLogic.validarExistencia(entity, idBiblioteca);
        return RecursoResource.class;
    }
    
    
    /**
     * Lista entidades a DTO
     * 
     * Este método convierte una lista de objetos BibliotecaEntity a una lista de objetos BibliotecaDetailDTO(json)
     * @param entityList corresponde a la lista de cantantes de tipo Entity
     * que se va a convertir a DTO.
     * 
     * @return  la lista de bibliotecas en forma DTO (json)
     */
    private List<BibliotecaDetailDTO> listEntity2DetailDTO(List<BibliotecaEntity> entityList){
        List<BibliotecaDetailDTO> list = new ArrayList<>();
        for(BibliotecaEntity entity: entityList){
            list.add(new BibliotecaDetailDTO(entity));
        }
        return list;
    }
    
}
