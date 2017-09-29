/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.SalonEntity;

/**
 *
 * @author s.guzman
 */
public class SalonDTO 
{
        /**
     * Identificador del salon
     */
    private Long id;
     /**
     * localizacion del salon
     */       
    private String localizacion;
    /**
     * representa si el recurso está disponible o no
     */    
    private Boolean disponibilidad;
    
   /**
     * Constructor por defecto
     */ 
   public SalonDTO ()
   {
       
   }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param salonE: Es la entidad que se va a convertir a DTO
     */   
   public SalonDTO (SalonEntity salonE)
   {
       System.out.println("sysout creador de DTO " + salonE.getLocalizacion());
           this.id = salonE.getId();
           this.localizacion =salonE.getLocalizacion();
           this.disponibilidad = salonE.isDisponibilidad();
   }
   
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
   public SalonEntity toEntity ()
   {
       System.out.println("sysout toEntity this.localizacion previa del dto " + this.localizacion);
       SalonEntity salonE = new SalonEntity();
       System.out.println("sysout new entity. getSalon previo (recien creado entityyy " + salonE.getLocalizacion());

       salonE.setId(this.id);
       salonE.setLocalizacion(this.localizacion);
       salonE.setDisponibilidad(this.disponibilidad);
       System.out.println("sysout new entity. getSalon post establecido el entity" + salonE.getLocalizacion());
       
       return salonE;
   }
   /**
    * Entrega el identificador del salon
    * @return 
    */
   public Long getId()
   {
       return id;
   }
   /**
    * Modifica el identificador del salon
    * @param pId  nuevo identificador para el salon
    */
   public void setId (Long pId)
   {
       this.id=pId;
   }
   /**
    * 
    * @return La localizacion del salon
    */
   public String getLocalizacion ()
   {
       return localizacion;
   }
   /**
    * Cambia la localizacion del salon
    * @param pLocalizacion nueva localizacion para el salon
    */
   public void setLocalizacion(String pLocalizacion)
   {
       this.localizacion = pLocalizacion;
   }
   /**
    * 
    * @return la disponibilidad del salon
    */
   public Boolean isDisponibilidad ()
   {
       return disponibilidad;
   }
   /**
    * Edita la disponibilidad de un salon
    * @param pDispo nueva disponibolidad del salon
    */
   public void setDisponibilidad (Boolean pDispo)
   {
       this.disponibilidad = pDispo;
   }
    
}
