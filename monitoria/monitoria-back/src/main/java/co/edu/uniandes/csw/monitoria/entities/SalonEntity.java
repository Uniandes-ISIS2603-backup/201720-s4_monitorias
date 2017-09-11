/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author s.guzman
 */
@Entity
public class SalonEntity extends BaseEntity implements Serializable
{
    private boolean disponibilidad;
    
    
    private String localizacion;
    
    
    public void cambiarDisponibilidad()
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
            
    
}
