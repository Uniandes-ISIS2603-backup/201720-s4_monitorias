/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.SalonEntity;
import co.edu.uniandes.csw.monitoria.entities.SedeEntity;

/**
 *
 * @author s.guzman
 */
public class SalonDTO 
{
    private Long id;
    
    private String name;
    
    private String localizacion;
    
    private Boolean disponibilidad;
    
    
   public SalonDTO ()
   {
       
   }
   
   public SalonDTO (SalonEntity salonE)
   {
       if (salonE!= null)
       {
           this.id = salonE.getId();
           this.name = salonE.getName();
           this.localizacion =salonE.getLocalizacion();
           this.disponibilidad = salonE.isDisponibilidad();
       }
   }
   
   public SalonEntity toEntity ()
   {
       SalonEntity salonE = new SalonEntity();
       salonE.setId(id);
       salonE.setName(name);
       salonE.setLocalizacion(localizacion);
       salonE.setDisponibilidad(disponibilidad);
       return salonE;
   }
   
   public Long getId()
   {
       return id;
   }
   public void setId (Long pId)
   {
       this.id=pId;
   }
   public String getName ()
   {
       return name;
   }
   public void setName (String pName)
   {
       this.name=pName;
   }
   public String getLocalizacion ()
   {
       return localizacion;
   }
   public void setLocalizaccion(String pLocalizacion)
   {
       this.localizacion = pLocalizacion;
   }
   public Boolean isDisponibilidad ()
   {
       return disponibilidad;
   }
   public void setDisponibilidad (Boolean pDispo)
   {
       this.disponibilidad = pDispo;
   }
    
}
