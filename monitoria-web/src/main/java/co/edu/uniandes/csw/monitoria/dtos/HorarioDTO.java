
package co.edu.uniandes.csw.monitoria.dtos;
import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class HorarioDTO {

    private long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicio;
   
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaFin;

    private boolean disponibilidad;

    public HorarioDTO() {
        //constructor por defecto solo
    }
    /*
    Constructor de un horario DTO a partir de un horario entity
    */
    public HorarioDTO(HorarioEntity horario) {
        this.id = horario.getId();
        System.out.println(this.id);
        this.horaInicio = horario.getHoraInicio();
        this.horaFin = horario.getHoraFin();
        this.disponibilidad= horario.getDisponibilidad();
    }
    /*
    obtiene el id
    */
    public Long getId() {
        return id;
    }
  /*
    modifica el id
    */
    public void setId(Long id) {
        this.id = id;
    }
/*
    obtiene la hora de inicio
    */
    public Date getHoraInicio() {

        return horaInicio;
    }
 /*
    modifica la hora de inicio
    */
    public void setHoraInicio(Date fecha) {
        this.horaInicio = fecha;
    }
 /*
    obtiene la hora de fin
    */
    public Date getHoraFin() {
        return horaFin;
    }
/*
    modifica la hora de fin
    */
    public void setHoraFin(Date fecha) {
        this.horaFin = fecha;
    }
/*
    obtiene la disponibilidad del horario
    */
    public boolean getDisponibilidad() {
        return disponibilidad;
    }
    /*
    modifica la disponibilidad del horario
    */
    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }




    public HorarioEntity toEntity() {
        HorarioEntity entity = new HorarioEntity();
        entity.setId(id);
        entity.setHoraInicio(horaInicio);
        entity.setHoraFin(horaFin);
        entity.setDisponibilidad(disponibilidad);
        return entity;
    }

}
