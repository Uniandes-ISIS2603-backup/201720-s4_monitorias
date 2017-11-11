
package co.edu.uniandes.csw.monitoria.dtos;
import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class HorarioDTO {
  
    private long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicio;
   
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaFin;

    private boolean disponibilidad;

    public HorarioDTO() {
    }
   
    public Long getId() {
        return id;
    }
  
    public void setId(Long id) {
        this.id = id;
    }

    public Date getHoraInicio() {

        return horaInicio;
    }
 
    public void setHoraInicio(Date fecha) {
        this.horaInicio = fecha;
    }
 
    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date fecha) {
        this.horaFin = fecha;
    }

    public boolean getDisponibilidad() {
        return disponibilidad;
    }
    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }


    public HorarioDTO(HorarioEntity horario) {
        this.id = horario.getId();
        this.horaInicio = horario.getHoraInicio();
        this.horaFin = horario.getHoraFin();
        this.disponibilidad= horario.getDisponibilidad();
    }

    public HorarioEntity toEntity() {
        HorarioEntity entity = new HorarioEntity();
        entity.setId(this.id);
        entity.setHoraInicio(horaInicio);
       // entity.setHoraFin(horaFin);
        entity.setDisponibilidad(this.disponibilidad);
        return entity;
    }

}
