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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/

import java.io.Serializable;
import java.util.List;
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
public class ActividadEntity  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public List<RecursoEntity> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<RecursoEntity> recursos) {
        this.recursos = recursos;
    }
   
   @PodamExclude
   @OneToMany(mappedBy = "actividad")
   private List<RecursoEntity> recursos;
   private String descripcion;
   private String tareaAsignada;

   
   @Override
    public boolean equals(Object obj) {
        if (this.getId() != null && ((BaseEntity) obj).getId() != null) {
            return this.getId().equals(((BaseEntity) obj).getId());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDescripcion()
    {
    return descripcion;
    }
    public String getTareaAsignada()
    {
        return tareaAsignada;
    }
    
    public void setDescripcion(String pDescripcion)
    {
        this.descripcion=pDescripcion;
    }
    public void setTareaAsignada(String pTareaAsignada)
    {
        this.tareaAsignada= pTareaAsignada;
    }
    
    
}
