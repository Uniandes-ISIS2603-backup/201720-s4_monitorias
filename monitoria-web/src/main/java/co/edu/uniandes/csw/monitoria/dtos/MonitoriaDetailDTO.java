/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.ejb.EstudianteLogic;
import co.edu.uniandes.csw.monitoria.entities.ActividadEntity;
import co.edu.uniandes.csw.monitoria.entities.MonitoriaEntity;
import co.edu.uniandes.csw.monitoria.entities.EstudianteEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author l.mejia
 */
public class MonitoriaDetailDTO extends MonitoriaDTO {
    
    IdiomaDTO idioma;
    List<ActividadDTO> actividades=new ArrayList<>();;
    List<HorarioDTO> horario= new ArrayList<>();
    MonitorDTO monitor;
    List<EstudianteDTO> estudiantes= new ArrayList<>();
    EstudianteLogic logicEstudiante= new EstudianteLogic();
     public MonitoriaDetailDTO(MonitoriaEntity entity)
    {
        super(entity);
        if(entity.getIdioma()!=null){
        this.idioma=new IdiomaDTO(entity.getIdioma());
        }
        if(entity.getMonitor()!=null)
        {
        this.monitor=new MonitorDTO(entity.getMonitor());
        }
        if(entity.getHorario()!=null)
        {
            entity.getHorario().forEach(x->this.horario.add(new HorarioDTO(x)));
        }
        if(entity.getEstudiantes()!=null)
        {
            entity.getEstudiantes().forEach(x->this.estudiantes.add(new EstudianteDTO(x)));
            for(EstudianteEntity x:entity.getEstudiantes())
            {
                this.estudiantes.add(new EstudianteDTO(x));
            }
        }
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

    public List<EstudianteDTO> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<EstudianteDTO> estudiantes) {
        this.estudiantes = estudiantes;
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
    public IdiomaDTO getIdioma()
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
        if(this.idioma!=null)
        entity.setIdioma(this.idioma.toEntity());
        if(this.monitor!=null)
        entity.setMonitor(this.monitor.toEntity());
        if(this.horario!=null)
        horario.forEach(x->entity.getHorario().add(x.toEntity()));
        if (getEstudiantes()!= null) {
            List<EstudianteEntity> estudiantesEntity = new ArrayList<>();
            for (EstudianteDTO dtoEstudiante : getEstudiantes()) {
                EstudianteEntity agregar=dtoEstudiante.toEntity();                
                estudiantesEntity.add(agregar);
            }
            entity.setEstudiantes(estudiantesEntity);
        }
        
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
