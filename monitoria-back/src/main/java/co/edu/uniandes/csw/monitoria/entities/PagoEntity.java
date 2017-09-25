/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.entities;
/**
 *
 * @author l.mejia
 */
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;


@Entity
public class PagoEntity extends BaseEntity implements Serializable{
    private Integer valor;
    private boolean estado;    
    @ManyToOne
    private MonitorEntity monitor;
    
    public Integer getValor(){
        return valor;
    }
    public boolean getEstado(){
        return estado;
    }  
    public MonitorEntity getMonitor(){
        return monitor;
    }     
    public void setValor(Integer valor){
        this.valor=valor;
    }
    public void setEstado(Boolean estado){
        this.estado=estado;
    }
    public void setMonitor(MonitorEntity monitor){
        this.monitor=monitor;
    }
    public void MonitorEntity(MonitorEntity monitor){
        this.monitor=monitor;
    }
    
}
