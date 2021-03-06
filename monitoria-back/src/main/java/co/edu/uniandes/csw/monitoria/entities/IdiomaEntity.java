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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;



/**
 *
 * @author ca.mendoza
 */

@Entity
public class IdiomaEntity implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String idioma;

@PodamExclude
@OneToMany(mappedBy = "idioma", cascade = CascadeType.PERSIST)
private List<RecursoEntity> recursos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
  


    public List<RecursoEntity> getRecursos() {
        return recursos;
    }
    public void setRecursos(List<RecursoEntity> recursos) {
        this.recursos = recursos;
    }
     public String getIdioma() {
        return idioma;
    }
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    
    

}
