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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;


@Entity
public class PagoEntity implements Serializable{
    
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id; 
    private Integer valor;
    private boolean estado;
    @PodamExclude
    @ManyToOne
    private MonitorEntity monitor;
    
     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
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
    public void monitorEntity(MonitorEntity monitor){
        this.monitor=monitor;
    }
    
}
