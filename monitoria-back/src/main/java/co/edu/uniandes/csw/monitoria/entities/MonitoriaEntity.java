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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
public class MonitoriaEntity extends BaseEntity implements Serializable{
    
    private Long idMonitor;
    private String nombreMonitor;    
    private String nombreEstudiante;
    private String tipo;
    private String estado;
    @PodamExclude
    @OneToOne
    private HorarioEntity horario;

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

    public MonitorEntity getMonitor() {
        return monitor;
    }

    public void setMonitor(MonitorEntity monitor) {
        this.monitor = monitor;
    }

    /*public EstudianteEntity getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteEntity estudiante) {
        this.estudiante = estudiante;
    }*/    
   
    
   
    public HorarioEntity getHorario() {
        return horario;
    }

    public void setHorario(HorarioEntity horario) {
        this.horario = horario;
    }
    public List<ActividadEntity> getActividades()
    {
        return actividades;
    }
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
        return nombreMonitor;
    }
    
    public String getNombreEstudiante(){
        return nombreEstudiante;
    }
    
    public String getEstado(){
        return estado;
    }
    
    public void setTipo(String tipo)
    {
        this.tipo=tipo;
    }
    
    public void setNombreMonitor(String nombreMonitor)
    {
        this.nombreMonitor=nombreMonitor;
    }
    
    public void setNombreEstudiante(String nombreEstudiante)
    {
        this.nombreEstudiante=nombreEstudiante;
    }
    
    public void setEstado(String estado)
    {
        this.estado=estado;
    }
    
    public Long getIdMonitor(){
        return this.idMonitor;
   }
   public void setIdMonitor(Long idMonitor)
   {
       this.idMonitor=idMonitor;
   }
   
}
