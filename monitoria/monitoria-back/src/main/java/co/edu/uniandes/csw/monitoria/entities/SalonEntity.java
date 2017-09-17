/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    
    @OneToOne
    private SedeEntity sede;
    @PodamExclude
    @OneToMany
    private List<HorarioEntity> horarios;
    
    public void setDisponibilidad()
    {
        if (disponibilidad==true)
        {
            this.disponibilidad=false;
        }
        else
        {
            this.disponibilidad=true;
        }
    }
    
    public boolean getDisponibilidad ()
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
    
    public void SetHorario ( List<HorarioEntity> pHorarios)
    {
        this.horarios = pHorarios;
    }
    
    public List<HorarioEntity> getHorario ()
    {
        return horarios; 
    }
    
    
}

