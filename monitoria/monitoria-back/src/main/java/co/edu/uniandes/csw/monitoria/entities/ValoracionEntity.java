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
 * @author mf.mena
 */
@Entity
public class ValoracionEntity extends BaseEntity implements Serializable{
    private Boolean estado;
    private Integer valor;
     public Boolean getEstado(){
        return estado;
    }
    
    public void setEstado(Boolean estado){
        this.estado=estado;
    }
     public Integer getValor(){
        return valor;
    }
    
    public void setValor(Integer valor){
        this.valor=valor;
    }
    
    
    
            
}
