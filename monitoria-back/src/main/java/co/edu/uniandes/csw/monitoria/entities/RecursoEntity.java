/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author ms.osorio
 */

@Entity
public class RecursoEntity extends BaseEntity implements Serializable {
  
    private Boolean disponibilidad;
    private String editorial;
    
    @PodamExclude
    @ManyToOne
    private BibliotecaEntity biblioteca;
    
   @PodamExclude
    @ManyToOne
    private IdiomaEntity idioma;

    public IdiomaEntity getIdioma() {
        return idioma;
    }

    public void setIdioma(IdiomaEntity idioma) {
        this.idioma = idioma;
    }
    

    public BibliotecaEntity getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(BibliotecaEntity biblioteca) {
        this.biblioteca = biblioteca;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
}
