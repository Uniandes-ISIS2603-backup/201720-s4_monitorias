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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/

import java.io.Serializable;

/**
 *
 * @author ca.mendoza
 */
@Entity
public class ActividadEntity extends BaseEntity implements Serializable {
    private String descripcion;
    private String tareaAsignada;
    
   
    
    public String getDescripcion()
    {
    return descripcion;
    }
    public String getTareaAsignada()
    {
        return tareaAsignada;
    }
    
    public void setDescripcion(String pDescripcion)
    {
        this.descripcion=pDescripcion;
    }
    public void setTareaAsignada(String pTareaAsignada)
    {
        this.tareaAsignada= pTareaAsignada;
    }
    
    
}
