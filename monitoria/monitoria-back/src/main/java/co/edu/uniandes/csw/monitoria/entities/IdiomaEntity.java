/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;


/**
 *
 * @author ca.mendoza
 */
import uk.co.jemos.podam.common.PodamStrategyValue;
@Entity
public class IdiomaEntity extends BaseEntity implements Serializable {
private String idioma;

@OneToMany
private List<RecursoEntity> recursos;

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
