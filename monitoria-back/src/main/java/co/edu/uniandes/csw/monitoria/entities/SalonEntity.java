/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.guzman
 * 
 */
@Entity
public class SalonEntity extends BaseEntity implements Serializable
{
    private boolean disponibilidad;
       
    private String localizacion;
    
    @PodamExclude
    @ManyToOne
    private SedeEntity sede;
    
   // @PodamExclude
   // @OneToMany
   // private List<HorarioEntity> horarios;
    
    public void setDisponibilidad(Boolean pDisponibilidad)
    {
        this.disponibilidad = pDisponibilidad;
    }
    
    public boolean isDisponibilidad ()
    {
        return disponibilidad;
    }
    
    
    public void setLocalizacion (String newLoc)
    {
        this.localizacion = newLoc;
    }
    
    public String getLocalizacion ()
    {
        return localizacion;
    }
  
    public void setSede (SedeEntity pSede)
    {
        this.sede = pSede;
    }
    
    public SedeEntity getSede ()
    {
        return sede;
    }
    
 //   public void SetHorarios ( List<HorarioEntity> pHorarios)
 //   {
 //       this.horarios = pHorarios;
 //   }
    
 //   public List<HorarioEntity> getHorarios ()
 //   {
 //       return horarios; 
 //   }    
}

