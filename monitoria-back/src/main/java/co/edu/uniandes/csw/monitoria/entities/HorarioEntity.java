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
    long idHorario;

    @Temporal(TemporalType.TIME)
    Date horaInicio;
    @Temporal(TemporalType.TIME)
    Date horaFin;
    
    @PodamExclude
   @ManyToOne
   private MonitorEntity rMonitor;
    
    /**
     *
     * @param id
     * @return
     */
    public void setIDHorario(long id)
    {
       idHorario=id;
    }
    public long getIDHorario()
    {
        return idHorario;
    }

    public void setHoraInicio(Date inicio)
    {
        horaInicio=inicio;
    }
    public Date getHoraInicio(){
        return horaInicio;
    }
     public void setHoraFin(Date fin)
    {
        horaFin=fin;
    }
    public Date getHoraFin(){
        return horaFin;
    }

}
