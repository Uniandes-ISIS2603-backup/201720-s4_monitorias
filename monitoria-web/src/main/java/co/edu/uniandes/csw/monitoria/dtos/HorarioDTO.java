/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;

/**
 *
 * @author Cristian
 */
public class HorarioDTO {

    private long id;
   private String horaInicio;
   private String horaFin;
    
    /**
     * Constructor por defecto
     */
    public HorarioDTO() {
    }

    /**
     * Conviertir Entity a DTO
     * (Crea un nuevo DTO con los valores que recibe en la entidad que viene de argumento.
     * @param editorial: Es la entidad que se va a convertir a DTO 
     */
    public HorarioDTO(HorarioEntity horario) {
        this.id = horario.getId();
        this.horaInicio = horario.getHoraInicio();
        this.horaFin=horario.getHoraFin();
    
    }

    /**
     * @return el id del estudiante
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id poner el id del estudiante
     */
    public void setId(Long id) {
        this.id = id;
    }
       /**
     * @return el id del estudiante
     */
    public String getHoraInicio() {
        return horaInicio;
    }

    /**
     * @param id poner el id del estudiante
     */
    public void setHoraInicio(String horaInit) {
        this.horaInicio=horaInit;
    }
        /**
     * @return el id del estudiante
     */
    public String getHoraFin() {
        return horaInicio;
    }

    /**
     * @param id poner el id del estudiante
     */
    public void setHoraFin(String horafin) {
        this.horaFin=horafin;
    }
 

    /**
     * Convertir DTO a Entity
     * @return Un Entity con los valores del DTO 
     */
    public HorarioEntity toEntity() {
       HorarioEntity entity = new HorarioEntity();
        entity.setId(this.id);
        entity.setHoraInicio(horaInicio);
        entity.setHoraFin(horaFin);
        return entity;
    }
    

}
