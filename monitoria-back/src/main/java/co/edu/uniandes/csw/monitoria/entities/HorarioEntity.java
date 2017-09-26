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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author Cristian
 */
@Entity
public class HorarioEntity extends IdentityEntity implements Serializable {
   
   @Temporal(TemporalType.DATE)
    private Date horaInicio;
    @Temporal(TemporalType.DATE)
    private Date horaFin;
    @PodamExclude
   @ManyToOne
   private MonitorEntity rMonitor;
   @ManyToOne
   private SalonEntity salon;
   

    public void setHoraInicio(Date inicio)
    {
        this.horaInicio=inicio;
    }
    public Date getHoraInicio(){
        return horaInicio;
    }
     public void setHoraFin(Date fin)
    {
        this.horaFin=fin;
    }
    public Date getHoraFin(){
        return horaFin;
    }
    public MonitorEntity getMonitor(){
        return this.rMonitor;
    }
    public void setMonitor(MonitorEntity pMonitor){
        this.rMonitor=pMonitor;
    }
     public SalonEntity getSalon(){
        return this.salon;
    }
    public void setSalon(SalonEntity pSalon){
        this.salon=pSalon;
    }

}
