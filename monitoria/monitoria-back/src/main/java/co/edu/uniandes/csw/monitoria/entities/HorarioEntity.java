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
import javax.persistence.TemporalType;

/**
 *
 * @author Cristian
 */
@Entity
public class HorarioEntity extends BaseEntity implements Serializable {
    long idHorario;
    @Temporal(TemporalType.DATE)
    Date horaInicio;
    @Temporal(TemporalType.DATE)
    Date horaFin;
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
