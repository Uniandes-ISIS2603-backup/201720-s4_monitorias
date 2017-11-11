/*
 * Clase que modela la entidad del recurso Monitoria.
 */
package co.edu.uniandes.csw.monitoria.entities;

/**
 *
 * @author l.mejia
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
public class MonitoriaEntity implements Serializable{
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String tipo;
    private String estado;
    
    @PodamExclude
    @OneToMany
    private List<HorarioEntity> horarios;

    @PodamExclude
    @OneToOne
    private IdiomaEntity idioma;
    
    @PodamExclude
    @ManyToOne
    private MonitorEntity monitor;
    
    @PodamExclude
    @ManyToMany 
    private List<EstudianteEntity> estudiantes;
    
    @PodamExclude
    @OneToMany(mappedBy = "monitoria",orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
    private List<ActividadEntity> actividades;
    
    
    /*
    *Retorna el monitor asignado a la monitoria
    */
    public MonitorEntity getMonitor() {
        return monitor;
    }

    /*
    *Asigna un monitor a la monitoria
    */
    public void setMonitor(MonitorEntity monitor) {
        this.monitor = monitor;
    }
    /*
    *Retorna el id de la entidad
    */
     public Long getId() {
        return id;
    }
     
     /*
     *Le asigna u id a la entidad
     */
    public void setId(Long id) {
        this.id = id;
    }
    public List<EstudianteEntity> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiante(List<EstudianteEntity> estudiantes) {
        this.estudiantes = estudiantes;
    }    
   
    
   /*
    *Retorna el horario de la monitoria
    */
    public List<HorarioEntity> getHorario() {
        return horarios;
    }
    /*
    *Le asigna los horarios a la monitoria
    */
    public void setHorario(List<HorarioEntity> horarios) {
        this.horarios = horarios;
    }
    
    /*
    *Retorna las actividades asignadas a la monitoria
    */
    public List<ActividadEntity> getActividades()
    {
        return actividades;
    }
    /*
    *Asigna las actividades la monitoria
    */
    public void setActividades(List<ActividadEntity> actividades)
    {
        this.actividades=actividades;
    }
    /*
    *Retorna el idioma de la monitoria
    */
    public IdiomaEntity getIdioma()
    {
        return this.idioma;
    }
    /*
    *Asigna el idioma a la monitoria
    */
    public void setIdioma(IdiomaEntity idioma)
    {
        this.idioma=idioma;
    }
    /*
    *Retorna el tipo de la monitoria
    */
    public String getTipo()
    {
        return tipo;
    }
    
    /*
    *Retorna el estado de la monitoria
    */
    public String getEstado(){
        return estado;
    }
    /*
    *Reorna el tipo de la monitoria
    */
    public void setTipo(String tipo)
    {
        this.tipo=tipo;
    }
    /*
    *Asigna el estado de la monitoria
    */
    public void setEstado(String estado)
    {
        this.estado=estado;
    }
    

   
}
