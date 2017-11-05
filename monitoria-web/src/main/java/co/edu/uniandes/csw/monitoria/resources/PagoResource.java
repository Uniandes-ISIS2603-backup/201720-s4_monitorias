/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.resources;

import co.edu.uniandes.csw.monitoria.dtos.PagoDTO;
import co.edu.uniandes.csw.monitoria.ejb.PagoLogic;
import co.edu.uniandes.csw.monitoria.entities.PagoEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import java.util.ArrayList;
import javax.ws.rs.GET;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author mf.mena
 */
@Path("pagos")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class PagoResource {
    
     @Inject
    PagoLogic pagoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    
     /**
     * GET para todos los pagos.
     * @return la lista de todos los monitores en objetos json DTO.
     */
    @GET
    public List<PagoDTO> getPago() {
        return listEntity2DetailDTO(pagoLogic.getPagos());
    }
     private List<PagoDTO> listEntity2DetailDTO(List<PagoEntity> entityList) {
        List<PagoDTO> list = new ArrayList<>();
        for (PagoEntity entity : entityList) {
            list.add(new PagoDTO(entity));
        }
        return list;
    }
     
    @PUT
    @Path("{id: \\d+}")
    public PagoDTO updatePago(@PathParam("id") Long id, PagoDTO pago) throws BusinessLogicException{        
        return new PagoDTO(pagoLogic.updatePago(id, pago.toEntity()));
    }
    
     @DELETE
    @Path("{id: \\d+}")
    public void deletePago(@PathParam("id") Long id)throws BusinessLogicException {
        pagoLogic.deletePago(id);
    }
}
