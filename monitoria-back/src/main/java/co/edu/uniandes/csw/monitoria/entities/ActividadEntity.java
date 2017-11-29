/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.entities;

import javax.persistence.Entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ca.mendoza
 */
@Entity
public class ActividadEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private String tareaAsignada;
   
    @PodamExclude
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<RecursoEntity> recursos;
   
    @PodamExclude
    @ManyToOne
    private MonitoriaEntity monitoria;

    public MonitoriaEntity getMonitoria() {
        return monitoria;
    }

    public void setMonitoria(MonitoriaEntity monitoria) {
        this.monitoria = monitoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTareaAsignada() {
        return tareaAsignada;
    }

    public void setDescripcion(String pDescripcion) {
        this.descripcion = pDescripcion;
    }

    public void setTareaAsignada(String pTareaAsignada) {
        this.tareaAsignada = pTareaAsignada;
    }

    public List<RecursoEntity> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<RecursoEntity> recursos) {
        this.recursos = recursos;
    }

}