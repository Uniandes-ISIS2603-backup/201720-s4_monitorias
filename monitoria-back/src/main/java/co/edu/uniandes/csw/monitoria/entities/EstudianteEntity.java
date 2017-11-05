/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author Cristian
 */
import uk.co.jemos.podam.common.PodamStrategyValue;
@Entity
public class EstudianteEntity extends BaseEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private Boolean penalizacion;
    private Long codigo;
    private String correo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaMonitoria;
    @PodamExclude
    @OneToOne
    private MonitoriaEntity monitoria;
  public void setName(String pNombre)
  {
      this.name=pNombre;
  }
  public String getName()
  {
      return this.name;
  }
  public void setPenalizacion(Boolean pPen)
  {
      this.penalizacion=pPen;
  }
  public Boolean getPenalizacion()
  {
      return this.penalizacion;
  }
  public void setCodigo(Long pCodigo)
  {
      this.codigo=pCodigo;
  }
  public Long getCodigo(){
      return this.codigo;
  }
  public void setCorreo(String correo){
      this.correo=correo;
  }
  public String getCorreo(){
      return correo;
  }
 public void setUltimaMonitoria(Date lastMonitoria){
     this.ultimaMonitoria=lastMonitoria;
  }
 public Date getUltimaMonitoria(){
      return this.ultimaMonitoria;
  }
  public void setMonitoria(MonitoriaEntity lastMonitoria){
     this.monitoria=lastMonitoria;
  }
 public MonitoriaEntity getMonitoria(){
      return this.monitoria;
  }
  @Override
     public Long getId() {
        return id;
    }
     @Override
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj != null){
            if (this.getId() != null && ((BaseEntity) obj).getId() != null) {
                return this.getId().equals(((BaseEntity) obj).getId());
            }
        }
        return super.equals(obj);
    }
    
    @Override
     public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
}
