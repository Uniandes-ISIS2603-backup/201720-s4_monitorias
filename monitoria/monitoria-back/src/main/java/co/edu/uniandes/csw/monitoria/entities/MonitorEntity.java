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
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @author mf.mena
 */
@Entity
public class MonitorEntity extends BaseEntity implements Serializable {
    private Integer tipo;
    private Double valPromedio;
    private Long codigo;
    
   /* @PodamExclude
    @OneToMany(mappedBy="rMonitor",fetch=FetchType.LAZY)
    private List<HorarioEntity> horarios;
    */
    @PodamExclude
    @OneToMany
    private List<IdiomaEntity> idiomas;
   
   @PodamExclude
   @OneToMany(mappedBy ="monitor",fetch=FetchType.LAZY)
   private List<PagoEntity> pagos;
    
    public Integer getTipo(){
        return tipo;
    }   
    public void setTipo(Integer tipo){
        this.tipo=tipo;
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
    
    public List<PagoEntity> getPagos(){
        return pagos;
    }   
    public void setPagos(List<PagoEntity> pagos){
        this.pagos=pagos;
    }
    
    /* public List<HorarioEntity> getHorarios(){
        return horarios;
    }   
    public void setHorarios(List<HorarioEntity> horarios){
        this.horarios=horarios;
    }
    */
    public List<IdiomaEntity> getIdioma(){
        return idiomas;
    }   
    public void setIdioma(List<IdiomaEntity> idiomas){
        this.idiomas=idiomas;
    }
}
