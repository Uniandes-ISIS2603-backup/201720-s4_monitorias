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
    
    /*@PodamExclude
    @ManyToOne 
    private EstudianteEntity estudiante;*/
    
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
    /*public EstudianteEntity getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteEntity estudiante) {
        this.estudiante = estudiante;
    }*/    
   
    
   /*
    *Retorna el horario de la monitoria
    */
    public List getHorario() {
        return horarios;
    }
    /*
    *Le asigna los horarios a la monitoria
    */
    public void setHorario(List horarios) {
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
    
    public IdiomaEntity getIdioma()
    {
        return this.idioma;
    }
    
    public void setIdioma(IdiomaEntity idioma)
    {
        this.idioma=idioma;
    }
    public String getTipo()
    {
        return tipo;
    }
    
    public String getNombreMonitor(){
        return monitor.getNombre();
    }
    
    
    
    public String getEstado(){
        return estado;
    }
    
    public void setTipo(String tipo)
    {
        this.tipo=tipo;
    }
    
    public void setEstado(String estado)
    {
        this.estado=estado;
    }
    

   
}
