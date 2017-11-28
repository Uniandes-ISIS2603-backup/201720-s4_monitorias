/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.resources;

import co.edu.uniandes.csw.monitoria.dtos.ValoracionDTO;
import co.edu.uniandes.csw.monitoria.dtos.ValoracionDetailDTO;
import co.edu.uniandes.csw.monitoria.ejb.ValoracionLogic;
import co.edu.uniandes.csw.monitoria.entities.ValoracionEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.ValoracionPersistence;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

/**
 *
 * @author l.mejia
 */
@Path("valoraciones")
@Consumes("application/json")
@Produces("application/json")
@Stateless
public class ValoracionResource {
    @Inject
    ValoracionLogic logic;
    
    private static final Logger LOGGER = Logger.getLogger(ValoracionPersistence.class.getName());
    
    @POST
    public ValoracionDTO createValoracion(ValoracionDetailDTO valoracion)throws BusinessLogicException
    {
        ValoracionEntity entity= valoracion.toEntity();
        ValoracionEntity nuevaValoracion= logic.createValoracion(entity);
        SimpleDateFormat format=new SimpleDateFormat("YYYY-MM-DD");
        try
        {
            nuevaValoracion.setFecha(format.parse(LocalDateTime.now().toString()));
            logic.update(nuevaValoracion);
        }
        catch(Exception e)
        {
        }
        
        return new ValoracionDTO(nuevaValoracion);
    }
    
    @GET
    public List<ValoracionDTO> getValoraciones()
    {
        return listEntity2DTO(logic.getValoraciones());
    }
    
     private List<ValoracionDTO> listEntity2DTO(List<ValoracionEntity> entityList){
        List<ValoracionDTO> list = new ArrayList<>();
        for(ValoracionEntity entity: entityList){
            list.add(new ValoracionDTO(entity));
        }
        return list;
    }
     
    @GET
    @Path("{id:\\d+}")
    public ValoracionDetailDTO getValoracion(@PathParam("id") Long id)
    {
            return new ValoracionDetailDTO(logic.findById(id));
    }
    
    @PUT
    @Path("{id:\\d+}")
    public ValoracionDetailDTO updateValoracion(@PathParam("id") Long id, ValoracionDTO valoracion) throws BusinessLogicException
    {
        valoracion.setId(id);
        logic.findById(id);
        return new ValoracionDetailDTO(logic.update(valoracion.toEntity()));
    } 
    @DELETE
    @Path("{id:\\d+}")
    public void deleteValoracion(@PathParam("id") Long id) throws BusinessLogicException
    {
        
        logic.deleteValoracion(id);
        
    }
}
