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
    HorarioDTO horario;
    MonitorDTO monitor;
     EstudianteDTO estudiante;

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
        //this.idioma=new IdiomaDTO(entity.getIdioma());
//        this.horario=new HorarioDTO(entity.getHorario());
        super(entity);
        if (entity.getActividades() != null) {
            actividades = new ArrayList<>();
            for (ActividadEntity entityActividad : entity.getActividades()) {
                actividades.add(new ActividadDTO(entityActividad));
            }
        }
    }
    @Override
    public MonitoriaEntity toEntity()
    {   
        MonitoriaEntity entity=super.toEntity();
       // entity.setIdioma(this.idioma.toEntity());
//        entity.setHorario(this.horario.toEntity());
//        entity.setEstudiante(this.estudiante.toEntity());
//   entity.setMonitor(this.monitor.toEntity());
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
