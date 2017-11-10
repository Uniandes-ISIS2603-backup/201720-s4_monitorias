/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;
 /*
     imports
    */
import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Cristian
 */
public class HorarioDTO {

    /*
    atributo id
    */
    private long id;
 /*
    atributo horaInicio
    */
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicio;
     /*
    atributo horaFin
    */
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaFin;
     /*
    atributo estado
    */
    private boolean estado;

    /**
     * Constructor por defecto
     */
    public HorarioDTO() {
    }
 /*
   *@return  Long retorna el id  del horario
    */
    public Long getId() {
        return id;
    }
 /*
   *@param recibe el id Long
    */
    public void setId(Long id) {
        this.id = id;
    }
     /*
   *@param recibe por parametro el estado del horario false=desocupado ,true=ocupado
    */
    public void setEstado(boolean pestado){
        this.estado=estado;
    }
     /*
   *@return  boolean retorna el estado del horario
    */
    public boolean getEstado(){
        return this.estado;
    }
     /*
   *@return  retorna la fecha de inicio del horario
    */
    public Date getHoraInicio() {

        return horaInicio;
    }
 /*
   *@param  recibe por parametro la fecha de inicio.
    */
    public void setHoraInicio(Date fecha) {
        this.horaInicio = fecha;
    }
 /*
   *@return  Long retorna la fecha de final
    */
    public Date getHoraFin() {
        return horaFin;
    }
 /*
   *@param  recibe por parametro la hora de final
    */
    public void setHoraFin(Date fecha) {
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
