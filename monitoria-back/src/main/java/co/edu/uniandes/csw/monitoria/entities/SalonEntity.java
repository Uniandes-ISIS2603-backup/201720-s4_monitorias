/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.guzman
 * 
 */
@Entity
public class SalonEntity implements Serializable
{
    private boolean disponibilidad;
       
    private String localizacion;
    
    @PodamExclude
    @ManyToOne (fetch = FetchType.LAZY)
    private SedeEntity sede;
    
    @PodamExclude
    @OneToMany (mappedBy = "salon")
    private List<HorarioEntity> horariosMonitoria;
    
    @PodamExclude
    @OneToMany (mappedBy = "salon")
    private List<HorarioEntity> horariosAtencion;
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    
    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }
    
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
    
    public void setHorariosMonitoria (List<HorarioEntity> pHorarios)
    {
       this.horariosMonitoria = pHorarios;
   }
    
   public List<HorarioEntity> getHorariosMonitoria ()
   {
       return horariosMonitoria; 
   }    
   
       public void setHorariosAtencion (List<HorarioEntity> pHorarios)
    {
       this.horariosAtencion = pHorarios;
   }
    
   public List<HorarioEntity> getHorariosAtencion ()
   {
       return horariosAtencion; 
   }  
   
}

