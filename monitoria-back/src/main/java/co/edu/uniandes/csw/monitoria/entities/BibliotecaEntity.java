/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.entities;

import java.io.Serializable;

import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;


/**
 * 
 * @author ms.osorio
 */
@Entity
public class BibliotecaEntity implements Serializable {
    
 private String ubicacion;
 
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    
    private String name;
 
 @PodamExclude
 @OneToMany(mappedBy = "biblioteca",orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
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
    
    public String getName(){
       return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    
     public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
   
}
