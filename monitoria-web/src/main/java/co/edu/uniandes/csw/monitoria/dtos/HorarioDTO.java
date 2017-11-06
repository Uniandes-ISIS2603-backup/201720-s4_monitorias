/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Cristian
 */
public class HorarioDTO {

    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicio;
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaFin;
    private boolean estado;

    /**
     * Constructor por defecto
     */
    public HorarioDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setEstado(boolean pestado){
        this.estado=estado;
    }
    public boolean getEstado(){
        return this.estado;
    }
    public Date getHoraInicio() {

        return horaInicio;
    }

    public void setHoraInicio(Date fecha) {
        this.horaInicio = fecha;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date fecha) {
        this.horaFin = fecha;
    }

    public void setFecha(Date fecha) {
        this.horaFin = fecha;
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param editorial: Es la entidad que se va a convertir a DTO
     */
    public HorarioDTO(HorarioEntity horario) {
        this.id = horario.getId();
        this.horaInicio = horario.getHoraInicio();
        this.horaFin = horario.getHoraFin();
        this.estado= horario.getEstado();
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public HorarioEntity toEntity() {
        HorarioEntity entity = new HorarioEntity();
        entity.setId(this.id);
        entity.setHoraInicio(horaInicio);
        entity.setHoraFin(horaFin);
        entity.setEstado(this.estado);
        return entity;
    }

}
