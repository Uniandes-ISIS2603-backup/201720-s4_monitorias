/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.entities;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author Cristian
 */
import uk.co.jemos.podam.common.PodamStrategyValue;
@Entity
public class BibliotecaEntity extends BaseEntity implements Serializable {
    
 private String ubicacion;
 
 @PodamExclude
 @OneToMany(mappedBy ="biblioteca",orphanRemoval = true, cascade = CascadeType.ALL) 
 private List<RecursoEntity> recursos;
 
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public void setRecursos(List<RecursoEntity> recursos){
        this.recursos = recursos;
    }
    public List<RecursoEntity> getRecursos(){
        return this.recursos;
    }
    
}
