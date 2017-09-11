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
public class MonitorEntity extends BaseEntity implements Serializable {
    private Integer tipo;
    private Double valPromedio;
    
    
    public Integer getTipo(){
        return tipo;
    }
    
    public void setTipo(Integer tipo){
        this.tipo=tipo;
    }
    public Double getValPromedio(){
        return valPromedio;
    }
    
    public void setTipo(Double valPromedio){
        this.valPromedio=valPromedio;
    }
    
}
