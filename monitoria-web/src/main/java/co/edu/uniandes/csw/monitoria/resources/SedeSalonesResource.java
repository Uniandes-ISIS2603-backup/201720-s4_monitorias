/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.resources;

import co.edu.uniandes.csw.monitoria.dtos.SalonDetailDTO;
import co.edu.uniandes.csw.monitoria.ejb.SedeLogic;
import co.edu.uniandes.csw.monitoria.entities.SalonEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author s.guzman
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class SedeSalonesResource 
{
    @Inject
    private SedeLogic sedeLogic;

    /**
     * Convierte una lista de SalonEntity a una lista de SalonDetailDTO.
     *
     * @param entityList Lista de SalonEntity a convertir.
     * @return Lista de SalonDetailDTO convertida.
     * 
     */
    private List<SalonDetailDTO> salonesListEntity2DTO(List<SalonEntity> entityList) 
    {
        List<SalonDetailDTO> list = new ArrayList<>();
        for (SalonEntity entity : entityList) 
        {
            list.add(new SalonDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de SalonDetailDTO a una lista de SalonEntity.
     *
     * @param dtos Lista de SalonDetailDTO a convertir.
     * @return Lista de SalonEntity convertida.
     * 
     */
    private List<SalonEntity> salonesListDTO2Entity(List<SalonDetailDTO> dtos) 
    {
        List<SalonEntity> list = new ArrayList<>();
        for (SalonDetailDTO dto : dtos)
        {
            list.add(dto.toEntity());
        }
        return list;
    }
    
           /**
     * Obtiene una colección de instancias de SalonDetailDTO asociadas a una
     * instancia de Sede
     *
     * @param sedesId Identificador de la instancia de Sede
     * @return Colección de instancias de SalonDetailDTO asociadas a la
     * instancia de Sede
     * 
     */
    @GET
    public List<SalonDetailDTO> listSalones(@PathParam("sedesId") Long sedesId) 
    {
        return salonesListEntity2DTO(sedeLogic.listSalones(sedesId));
    }

    /**
     * Obtiene una instancia de Salon asociada a una instancia de Sede
     *
     * @param sedesId Identificador de la instancia de Sede
     * @param salonesId Identificador de la instancia de Salon
     * @return 
     * 
     */
    @GET
    @Path("{salonesId: \\d+}")
    public SalonDetailDTO getSalones(@PathParam("sedesId") Long sedesId, @PathParam("salonesId") Long salonesId) 
    {
        return new SalonDetailDTO(sedeLogic.getSalon(sedesId, salonesId));
    }
    
        
      /**
     * Asocia un Salon existente a un Sede
     *
     * @param sedesId Identificador de la instancia de Sede
     * @param salonesId Identificador de la instancia de Salon
     * @return Instancia de SalonDetailDTO que fue asociada a Sede
     * 
     */
    @POST
    @Path("{salonesId: \\d+}")
    public SalonDetailDTO addSalons(@PathParam("sedesId") Long sedesId, @PathParam("salonesId") Long salonesId) 
    {
        return new SalonDetailDTO(sedeLogic.addSalon(sedesId, salonesId));
    }

    /**
     * Remplaza las instancias de Salon asociadas a una instancia de Sede
     *
     * @param sedesId Identificador de la instancia de Sede
     * @param salones Colección de instancias de SalonDTO a asociar a instancia
     * de Sede
     * @return Nueva colección de SalonDTO asociada a la instancia de Sede
     * 
     */
    @PUT
    public List<SalonDetailDTO> replaceSalons(@PathParam("sedesId") Long sedesId, List<SalonDetailDTO> salones) 
    {
        return salonesListEntity2DTO(sedeLogic.replaceSalons(sedesId, salonesListDTO2Entity(salones)));
    }

    /**
     * Desasocia un Salon existente de un Sede existente
     *
     * @param sedesId Identificador de la instancia de Sede
     * @param salonesId Identificador de la instancia de Salon
     * 
     */
    @DELETE
    @Path("{salonesId: \\d+}")
    public void removeSalons(@PathParam("sedesId") Long sedesId, @PathParam("salonesId") Long salonesId) 
    {
        sedeLogic.removeSalon(sedesId, salonesId);
    }
    
    
    
}
