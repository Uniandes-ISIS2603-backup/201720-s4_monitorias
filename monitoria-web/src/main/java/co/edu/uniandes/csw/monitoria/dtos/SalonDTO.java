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
    private String nombre;

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
       System.out.println("sysout creador de DTO " + salonE.getNombre());
           this.id = salonE.getId();
           this.nombre =salonE.getNombre();
   }
   
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
   public SalonEntity toEntity ()
   {
       System.out.println("sysout toEntity this.localizacion previa del dto " + this.nombre);
       SalonEntity salonE = new SalonEntity();
       System.out.println("sysout new entity. getSalon previo (recien creado entityyy " + salonE.getNombre());

       salonE.setId(this.id);
       salonE.setNombre(this.nombre);
       System.out.println("sysout new entity. getSalon post establecido el entity" + salonE.getNombre());
       
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
   public String getNombre ()
   {
       return nombre;
   }
   /**
    * Cambia la localizacion del salon
    * @param pLocalizacion nueva localizacion para el salon
    */
   public void setNombre(String pLocalizacion)
   {
       this.nombre = pLocalizacion;
   }
   /**
    * 
    * @return la disponibilidad del salon
    */

    
}
