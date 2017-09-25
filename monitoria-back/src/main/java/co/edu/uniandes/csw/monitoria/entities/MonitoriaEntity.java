/*
 * Clase que modela la entidad del recurso Monitoria.
 */
package co.edu.uniandes.csw.monitoria.entities;

/**
 *
 * @author l.mejia
 */
import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class MonitoriaEntity extends BaseEntity implements Serializable{
    
    private Long idMonitor;
    private String nombreMonitor;    
    private String nombreEstudiante;
    private String tipo;
    private String estado;
    
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
