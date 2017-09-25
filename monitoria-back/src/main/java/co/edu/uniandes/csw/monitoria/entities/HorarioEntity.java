/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author Cristian
 */
@Entity
public class HorarioEntity extends BaseEntity implements Serializable {
   
  
    private String horaInicio;
    private String horaFin;
    @PodamExclude
   @ManyToOne
   private MonitorEntity rMonitor;
    
   

    public void setHoraInicio(String inicio)
    {
        horaInicio=inicio;
    }
    public String getHoraInicio(){
        return horaInicio;
    }
     public void setHoraFin(String fin)
    {
        horaFin=fin;
    }
    public String getHoraFin(){
        return horaFin;
    }
    public MonitorEntity getMonitor(){
        return this.rMonitor;
    }
    public void setMonitor(MonitorEntity pMonitor){
        this.rMonitor=pMonitor;
    }

}
