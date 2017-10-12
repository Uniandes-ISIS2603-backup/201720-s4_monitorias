/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.MonitoriaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author l.mejia
 */
public class MonitoriaDetailDTO extends MonitoriaDTO {
    
    IdiomaDTO idioma;
    List<ActividadDTO> actividades;
    HorarioDTO horario;

    public HorarioDTO getHorario() {
        return horario;
    }

    public void setHorario(HorarioDTO horario) {
        this.horario = horario;
    }
    public List<ActividadDTO> getActividades()
    {
        return actividades;
    }
    public void setActividades(List<ActividadDTO> actividades)
    {
        this.actividades=actividades;
    }
    public IdiomaDTO getIdiomaDTO()
    {
        return this.idioma;
    }
    
    public void setIdioma(IdiomaDTO idioma)
    {
        this.idioma=idioma;
    }
    public MonitoriaDetailDTO()
    {
        super();
    }
    
    public MonitoriaDetailDTO(MonitoriaEntity entity)
    {
        this.idioma=new IdiomaDTO(entity.getIdioma());
        this.horario=new HorarioDTO(entity.getHorario());
        entity.getActividades().forEach((x) -> {
            this.actividades.add(new ActividadDTO(x));
        });
    }
    @Override
    public MonitoriaEntity toEntity()
    {   
        MonitoriaEntity entity=super.toEntity();
        entity.setIdioma(this.idioma.toEntity());
        entity.setHorario(this.horario.toEntity());
        this.actividades.forEach((x) -> {
            entity.getActividades().add(x.toEntity());
        });
        return entity;
    }
}
