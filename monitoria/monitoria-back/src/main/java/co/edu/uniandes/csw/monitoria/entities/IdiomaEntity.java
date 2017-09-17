/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;


/**
 *
 * @author Cristian
 */
import uk.co.jemos.podam.common.PodamStrategyValue;
@Entity
public class IdiomaEntity extends BaseEntity implements Serializable {
private String idioma;

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

}
