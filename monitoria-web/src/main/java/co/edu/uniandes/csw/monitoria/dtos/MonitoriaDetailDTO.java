/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.ActividadEntity;
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
    List<HorarioDTO> horario;
    MonitorDTO monitor;
     List<EstudianteDTO> estudiantes;
     
     public MonitoriaDetailDTO(MonitoriaEntity entity)
    {
        super(entity);
        this.idioma=new IdiomaDTO(entity.getIdioma());
        this.monitor=new MonitorDTO(entity.getMonitor());
        entity.getHorario().forEach(x->this.horario.add(new HorarioDTO(x)));
        entity.getEstudiante().forEach(x->this.estudiantes.add(new EstudianteDTO(x)));       
        if (entity.getActividades() != null) {
            actividades = new ArrayList<>();
            for (ActividadEntity entityActividad : entity.getActividades()) {
                actividades.add(new ActividadDTO(entityActividad));
            }
        }
    }
      public MonitoriaDetailDTO()
    {
        super();
    }
      
      
    public MonitorDTO getMonitor() {
        return monitor;
    }

    public void setMonitor(MonitorDTO monitor) {
        this.monitor = monitor;
    }

    public List<EstudianteDTO> getEstudiante() {
        return estudiantes;
    }

    public void setEstudiante(List<EstudianteDTO> estudiante) {
        this.estudiantes = estudiante;
    }
   

    public List<HorarioDTO> getHorario() {
        return horario;
    }

    public void setHorario(List<HorarioDTO> horario) {
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
    @Override
    public MonitoriaEntity toEntity()
    {   
        MonitoriaEntity entity=super.toEntity();
        entity.setIdioma(this.idioma.toEntity());
        entity.setMonitor(this.monitor.toEntity());
        horario.forEach(x->entity.getHorario().add(x.toEntity()));
        estudiantes.forEach(x->entity.getEstudiante().add(x.toEntity()));
        entity.setMonitor(this.monitor.toEntity());
        if (getActividades() != null) {
            List<ActividadEntity> actividadesEntity = new ArrayList<>();
            for (ActividadDTO dtoActividad : getActividades()) {
                actividadesEntity.add(dtoActividad.toEntity());
            }
            entity.setActividades(actividadesEntity);
        }
        return entity;
    }
}
