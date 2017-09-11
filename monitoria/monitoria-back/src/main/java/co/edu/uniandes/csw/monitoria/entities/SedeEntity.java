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
public class SedeEntity extends BaseEntity implements Serializable
{
    private String direccion;
    
    
    public void setDireccion(String newDireccion)
    {
        this.direccion = newDireccion;
    }
    
    public String getDireccion ()
    {
        return direccion;
    }
    
    
    
    
    
}
