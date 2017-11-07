/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @author mf.mena
 */
@Entity
public class MonitorEntity implements Serializable {
    private Integer tipo;
    private Double valPromedio;
     @Id
    private Long codigo;
    private String nombre;
    private String foto;
    
    @PodamExclude
    @OneToMany(mappedBy="rMonitor",fetch=FetchType.LAZY)
    private List<HorarioEntity> horarios;
   
     @PodamExclude
    @OneToMany
    private List<IdiomaEntity> idiomas;
    
    @PodamExclude
    @OneToMany
    private List<ValoracionEntity> valoraciones;
    
       @PodamExclude
     @OneToMany
    private List<MonitoriaEntity> monitorias;
   
    
    public Integer getTipo(){
        return tipo;
    }   
    public void setTipo(Integer tipo){
        this.tipo=tipo;
    }
    public String getNombre(){
        return nombre;
    }   
    public void setNoombre(String nombre){
        this.nombre=nombre;
    }
    public String getFoto(){
        return foto;
    }   
    public void setFoto(String foto){
        this.foto=foto;
    }
    
     public Long getCodigo(){
        return codigo;
    }   
    public void setCodigo(Long codigo){
        this.codigo=codigo;
    }
    
    public Double getValPromedio(){
        return valPromedio;
    }
    
    public void setValorPromedio(Double valPromedio){
        this.valPromedio=valPromedio;
    }    
    public List<HorarioEntity> getHorarios(){
        return horarios;
    }   
    public void setHorarios(List<HorarioEntity> horarios){
        this.horarios=horarios;
    }
    
    public List<ValoracionEntity> getValoraciones(){
        return valoraciones;
    }   
    public void setValoraciones(List<ValoracionEntity> valoraciones){
        this.valoraciones=valoraciones;
    }
    
     public List<MonitoriaEntity> getMonitorias(){
        return monitorias;
    }   
    public void setMonitorias(List<MonitoriaEntity> monitorias){
        this.monitorias=monitorias;
    }  
    public List<IdiomaEntity> getIdioma(){
        return idiomas;
    }   
    public void setIdioma(List<IdiomaEntity> idiomas){
        this.idiomas=idiomas;
    }
}
