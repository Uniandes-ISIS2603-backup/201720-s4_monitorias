/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity; 
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author mf.mena
 */
@Entity
public class ValoracionEntity extends BaseEntity implements Serializable{
    private String comentario;
    private Integer calificacion;
    
    @Temporal(TemporalType.DATE)
    @PodamExclude
    private Date fecha;
    
     public String getComentario(){
        return comentario;
    }
    
    public void setComentario(String comentario){
        this.comentario=comentario;
    }
     public Integer getCalificacion(){
        return calificacion;
    }
    
    public void setCalificacion(Integer calificacion){
        this.calificacion=calificacion;
    }
    
    public Date getFecha(){
        return fecha;
    }
    
    public void setFecha(Date fecha){
        this.fecha=fecha;
    }
    
    
            
}