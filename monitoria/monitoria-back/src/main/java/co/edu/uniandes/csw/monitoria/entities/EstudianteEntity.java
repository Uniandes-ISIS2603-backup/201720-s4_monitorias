/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;


/**
 *
 * @author Cristian
 */
import uk.co.jemos.podam.common.PodamStrategyValue;
@Entity
public class EstudianteEntity extends BaseEntity implements Serializable {
  String nombre;
  Boolean penalizacion;
  Long codigo;
 /*
  @Temporal(javax.persistence.TemporalType.DATE)
          
  Date ultimaMonitoria;
   */
  public void setNombre(String pNombre)
  {
      this.nombre=pNombre;
  }
  public String getNombre()
  {
      return this.nombre;
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
/* public void setUltimaMonitoria(Date lastMonitoria){
     this.ultimaMonitoria=lastMonitoria;
  }
 public Date getUltimaMonitoria(){
      return this.ultimaMonitoria;
  }
*/
}
