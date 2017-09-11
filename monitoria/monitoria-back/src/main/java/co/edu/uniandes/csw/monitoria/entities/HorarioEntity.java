/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author Cristian
 */
@Entity
public class HorarioEntity extends BaseEntity implements Serializable {
    long idHorario;
    Date horarioInicio;
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
        horarioInicio=inicio;
    }
    public Date getHoraInicio(){
        return horarioInicio;
    }
     public void setHoraFin(Date fin)
    {
        horaFin=fin;
    }
    public Date getHoraFin(){
        return horaFin;
    }

}
