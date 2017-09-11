/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author Cristian
 */
@Entity
public class EstudianteEntity extends BaseEntity implements Serializable {
  String nombre;
  Boolean penalizacion;
  Long codigo;
  Date ultimaMonitoria;
   
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
  public void setUltimaMonitoria(Date lastMonitoria){
      this.ultimaMonitoria=lastMonitoria;
  }
  public Date getUltimaMonitoria(){
      return this.ultimaMonitoria;
  }

}
