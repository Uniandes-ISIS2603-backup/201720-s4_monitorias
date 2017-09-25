/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.resources;

import co.edu.uniandes.csw.monitoria.dtos.EstudianteDTO;
import co.edu.uniandes.csw.monitoria.dtos.EstudianteDetailDTO;
import co.edu.uniandes.csw.monitoria.ejb.EstudianteLogic;
import co.edu.uniandes.csw.monitoria.entities.EstudianteEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.EstudiantePersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;


//URI:/estudiantes
@Path("/estudiantes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class EstudianteResource {

    @Inject
    private EstudianteLogic estudianteLogic;

    /**
     * Convierte una lista de AuthorEntity a una lista de AuthorDetailDTO.
     *
     * @param entityList Lista de AuthorEntity a convertir.
     * @return Lista de AuthorDetailDTO convertida.
     * 
     */
    private List<EstudianteDTO> listEntity2DTO(List<EstudianteEntity> entityList) {
        List<EstudianteDTO> list = new ArrayList<>();
        for (EstudianteEntity entity : entityList) {
            list.add(new EstudianteDTO(entity));
        }
        return list;
    }

    /**
     * Obtiene la lista de los registros de Author
     *
     * @return Colección de objetos de AuthorDetailDTO
     * 
     */
    @GET
    public List<EstudianteDTO> getEstudiantes() {
        return listEntity2DTO(estudianteLogic.getEstudiantes());
    }

    /**
     * Obtiene los datos de una instancia de Author a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de AuthorDetailDTO con los datos del Author consultado
     * 
     */
    @GET
    @Path("{id: \\d+}")
    public EstudianteDTO getEstudiante(@PathParam("id") Long id) {
        EstudianteEntity entity = estudianteLogic.findById(id);
        if (entity == null) {
            throw new WebApplicationException("El estudiante no existe", 404);
        }
        return new EstudianteDTO(entity);
    }

    /**
     * Se encarga de crear un Author en la base de datos
     *
     * @param dto Objeto de AuthorDetailDTO con los datos nuevos
     * @return Objeto de AuthorDetailDTOcon los datos nuevos y su ID
     * 
     */
    @POST
    public EstudianteDTO createEstudiante(EstudianteDTO dto) {
       EstudianteDTO r = null;
               try {
                   r=  new EstudianteDTO(estudianteLogic.createEstudiante(dto.toEntity()));
          // r  EstudianteDTO(estudianteLogic.createEstudiante(dto.toEntity()));
        } catch (BusinessLogicException ex) {
           if(r==null){
               throw new WebApplicationException("El estudiante no se pudo crear", 404);
           }
        }
               return r;
    }


    /**
     * Actualiza la información de una instancia de Author
     *
     * @param id Identificador de la instancia de Author a modificar
     * @param dto Instancia de AuthorDetailDTO con los nuevos datos
     * @return Instancia de AuthorDetailDTO con los datos actualizados
     * 
     */
    @PUT
    @Path("{id: \\d+}")
    public EstudianteDTO updateEstudiante(@PathParam("id") Long id, EstudianteDTO dto) throws BusinessLogicException {
        EstudianteEntity entity = dto.toEntity();
        entity.setId(id);
       EstudianteEntity oldEntity = estudianteLogic.findById(id);
        if (oldEntity == null) {
            throw new WebApplicationException("El author no existe", 404);
        }
        
        return new EstudianteDTO(estudianteLogic.update(dto.toEntity()));
    }

    /**
     * Elimina una instancia de Author de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     * 
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteAuthor(@PathParam("id") Long id) throws WebApplicationException, BusinessLogicException {
        EstudianteEntity entity = estudianteLogic.findById(id);
        if (entity == null) {
            throw new WebApplicationException("El author no existe", 404);
        }
       estudianteLogic.delete(id);
    }

   
}