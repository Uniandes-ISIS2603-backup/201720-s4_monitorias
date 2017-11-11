/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.MonitoriaEntity;
import java.util.List;

/**
 *
 * @author l.mejia
 */
public class MonitoriaDetailDTO extends MonitoriaDTO {
    
    IdiomaDTO idioma;
    List<ActividadDTO> actividades;
    List<HorarioDTO> horarios;
    MonitorDTO monitor;
    //EstudianteDTO estudiante;0

    public MonitorDTO getMonitor() {
        return monitor;
    }

    public void setMonitor(MonitorDTO monitor) {
        this.monitor = monitor;
    }

    public EstudianteDTO getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteDTO estudiante) {
        this.estudiante = estudiante;
    }
    EstudianteDTO estudiante;

    public List<HorarioDTO> getHorario() {
        return horarios;
    }

    public void setHorario(HorarioDTO horario) {
        this.horarios = horarios;
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
        entity.getActividades().forEach((x) -> {
            this.actividades.add(new ActividadDTO(x));
        });
        //this.estudiante=new EstudianteDTO(entity.getEstudiante());
        this.monitor=new MonitorDTO(entity.getMonitor());
        entity.getHorario().forEach((x)->{
                this.horarios.add(new HorarioDTO(x));
        });
    }
    @Override
    public MonitoriaEntity toEntity()
    {   
        MonitoriaEntity entity=super.toEntity();
        entity.setIdioma(this.idioma.toEntity());
        //entity.setEstudiante(this.estudiante.toEntity());
        entity.setMonitor(this.monitor.toEntity());
        this.actividades.forEach((x) -> {
            entity.getActividades().add(x.toEntity());
        });
        this.horarios.forEach((x)->{
            entity.getHorario().add(x.toEntity());
            });
        return entity;
    }
}
