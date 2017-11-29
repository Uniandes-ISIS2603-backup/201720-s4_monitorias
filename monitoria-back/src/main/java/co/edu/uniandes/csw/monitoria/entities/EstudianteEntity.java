/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Cristian
 */

@Entity
public class EstudianteEntity implements Serializable {

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
    
    @ManyToMany
    private List<MonitoriaEntity> monitorias=new ArrayList<>();

    public void setName(String pNombre) {
        this.name = pNombre;
    }

    public String getName() {
        return this.name;
    }

    public void setPenalizacion(Boolean pPen) {
        this.penalizacion = pPen;
    }

    public Boolean getPenalizacion() {
        return this.penalizacion;
    }

    public void setCodigo(Long pCodigo) {
        this.codigo = pCodigo;
    }

    public Long getCodigo() {
        return this.codigo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public List<MonitoriaEntity> getMonitorias() {
        return monitorias;
    }
    
    public void setMonitorias(List<MonitoriaEntity> monitorias) {
        this.monitorias = monitorias;
    }
    public void agregarMonitoria(MonitoriaEntity monitoria)
    {
        monitorias.add(monitoria);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
