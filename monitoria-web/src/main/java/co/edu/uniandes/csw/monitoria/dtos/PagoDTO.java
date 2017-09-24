/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;
import co.edu.uniandes.csw.monitoria.entities.PagoEntity;

/**
 *
 * @author mf.mena
 */
public class PagoDTO {
     /**
     * Constructor por defecto
     */
    public PagoDTO() {
    }

    /**
     * Conviertir Entity a DTO
     * (Crea un nuevo DTO con los valores que recibe en la entidad que viene de argumento.
     * @para pago: Es la entidad que se va a convertir a DTO 
     */
    public PagoDTO(PagoEntity pago) {
        
    }
    public PagoEntity toEntity() {
        return null;
    }
    
}
