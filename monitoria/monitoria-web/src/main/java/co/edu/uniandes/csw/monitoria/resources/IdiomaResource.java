/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.resources;


import co.edu.uniandes.csw.monitoria.dtos.IdiomaDTO;
import co.edu.uniandes.csw.monitoria.ejb.IdiomaLogic;
import co.edu.uniandes.csw.monitoria.entities.IdiomaEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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

/**
 *
 * @author ca.mendoza
 */
@Path("idiomas")
@Produces("application/json")
@Consumes("application/json")
public class IdiomaResource {
    @Inject
    IdiomaLogic idiomaLogic;
    
    private List<IdiomaDTO> listEntity2DTO(List<IdiomaEntity> entityList)
    {
        List<IdiomaDTO> list = new ArrayList<>();
         for(IdiomaEntity entity: entityList)
        {
            list.add(new IdiomaDTO(entity));
        }
         return list;
    }
    
    @GET
    public List<IdiomaDTO> getIdiomas()
    {
        return listEntity2DTO(idiomaLogic.getIdiomas());
    }
    @GET 
    @Path("{id:\\d+}") 
    public IdiomaDTO getIdioma(@PathParam("id")Long id)
    {   return new IdiomaDTO(idiomaLogic.getIdioma(id));
    }
    
    @POST
    public IdiomaDTO createIdioma(IdiomaDTO idioma) throws BusinessLogicException 
    {
        IdiomaEntity IdiomaEntity = idioma.toEntity();
        IdiomaEntity nuevoIdioma = idiomaLogic.createIdioma(IdiomaEntity);
        return new IdiomaDTO(nuevoIdioma);
    }
    
    @PUT 
    @Path("{id:\\d+}")
    public IdiomaDTO updateIdioma(@PathParam("id") Long id, IdiomaDTO idiomaDto)
    {
        IdiomaEntity entity = idiomaDto.toEntity();
        entity.setId(id);
        return new IdiomaDTO(idiomaLogic.updateEntity(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteIdioma(@PathParam("id")Long id)
    {
        idiomaLogic.deleteIdioma(id);
    }
    
 }
