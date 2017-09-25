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
    
    
    
    
}
