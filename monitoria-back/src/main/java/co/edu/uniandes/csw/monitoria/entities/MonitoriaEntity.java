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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
public class MonitoriaEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    
    private Long idMonitor;
    
    private String tipo;
    private String estado;
    @PodamExclude
    @OneToMany
    private List<HorarioEntity> horarios= new ArrayList<>();

    @PodamExclude
    @OneToOne
    private IdiomaEntity idioma;

    @PodamExclude
    @OneToOne
    private SalonEntity salon;
    
    @PodamExclude
    @ManyToOne
    private MonitorEntity monitor;
    

    @PodamExclude
    @ManyToMany(mappedBy="monitorias") 
    private List<EstudianteEntity> estudiantes= new ArrayList<>();
    @PodamExclude
    @OneToMany(mappedBy = "monitoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActividadEntity> actividades = new ArrayList<>();

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public MonitorEntity getMonitor() {
        return monitor;
    }

    public void setMonitor(MonitorEntity monitor) {
        this.monitor = monitor;
    }

   public List<EstudianteEntity> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<EstudianteEntity >estudiantes) {
        this.estudiantes = estudiantes;
    }
    public List<HorarioEntity> getHorario() {
        return horarios;
    }
    public void agregarEstudiante(EstudianteEntity estudiante)
    {
        estudiantes.add(estudiante);
    }

    public void setHorario(List<HorarioEntity> horario) {
        this.horarios = horario;
    }

    public List<ActividadEntity> getActividades() {
        return actividades;
    }

    public void setActividades(List<ActividadEntity> actividades) {
        this.actividades = actividades;
    }

    public IdiomaEntity getIdioma() {
        return this.idioma;
    }

    public void setIdioma(IdiomaEntity idioma) {
        this.idioma = idioma;
    }
    
   public SalonEntity getSalon() {
        return this.salon;
    }

    public void setSalon(SalonEntity pSalon) {
        this.salon = pSalon;
    }
    

    public String getTipo() {
        return tipo;
    } 

    public String getEstado() {
        return estado;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdMonitor() {
        return this.idMonitor;
    }

    public void setIdMonitor(Long idMonitor) {
        this.idMonitor = idMonitor;
    }

}
