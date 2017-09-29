/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.resources;


import co.edu.uniandes.csw.monitoria.dtos.IdiomaDTO;
import co.edu.uniandes.csw.monitoria.dtos.IdiomaDetailDTO;
import co.edu.uniandes.csw.monitoria.ejb.IdiomaLogic;
import co.edu.uniandes.csw.monitoria.entities.IdiomaEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ca.mendoza
 */
@Path("idiomas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class IdiomaResource {
    
    @Inject
    IdiomaLogic idiomaLogic;
    
    @POST
    public IdiomaDetailDTO createIdioma(IdiomaDetailDTO idioma) throws BusinessLogicException 
    {
        IdiomaEntity idiomaEntity = idioma.toEntity();
        IdiomaEntity nuevoIdioma = idiomaLogic.createIdioma(idiomaEntity);
        return new IdiomaDetailDTO(nuevoIdioma);
    }
    @GET
    public List<IdiomaDTO> getIdiomas()throws BusinessLogicException
    {
        return listEntity2DTO(idiomaLogic.getIdiomas());
    }
   
    
  
    @GET 
    @Path("{id: \\d+}")
    public IdiomaDetailDTO getIdioma(@PathParam("id") Long id) 
    {   return new IdiomaDetailDTO(idiomaLogic.getIdioma(id));
    }
    
   
    
    @PUT 
    @Path("{id: \\d+}")
    public IdiomaDetailDTO updateIdioma(@PathParam("id") Long id, IdiomaDetailDTO idioma) throws BusinessLogicException
    {
        idioma.setId(id);
        IdiomaEntity idiomaEntity = idioma.toEntity();
       
        return new IdiomaDetailDTO(idiomaLogic.updateIdioma(idiomaEntity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteIdioma(@PathParam("id") Long id)throws BusinessLogicException
    {
        idiomaLogic.deleteIdioma(id);
    }
    @Path("{id: \\d+}/recursos") 
    public Class<RecursoResource> getRecursosResources(@PathParam("id") Long id){
        IdiomaEntity entity = idiomaLogic.getIdioma(id);
        if(entity == null){
            throw new WebApplicationException("El recurso /idioma/" + id + "/recursos no existe.", 404);
        }
        return RecursoResource.class;
    }
    
    private List<IdiomaDetailDTO> listEntity2DetailDTO(List<IdiomaEntity> entityList){
        List<IdiomaDetailDTO> list = new ArrayList<>();
        for(IdiomaEntity entity: entityList){
            list.add(new IdiomaDetailDTO(entity));
        }
        return list;
    }
     private List<IdiomaDTO> listEntity2DTO(List<IdiomaEntity> entityList){
        List<IdiomaDTO> list = new ArrayList<>();
        for(IdiomaEntity entity: entityList){
            list.add(new IdiomaDTO(entity));
        }
        return list;
    }
    
 }
