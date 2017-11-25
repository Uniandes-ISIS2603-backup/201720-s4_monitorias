/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.ActividadEntity;

/**
 *
 * @author Carlos
 */
public class ActividadDTO {
    
    private Long id;
    private String tareaAsignada;
    private String descripcion;
    
     public ActividadDTO()
    {
      //vvacio
    }
    
    public ActividadDTO(ActividadEntity entity)
    {
        if(entity!=null)
        {   this.id= entity.getId();
            this.tareaAsignada= entity.getTareaAsignada();
            this.descripcion= entity.getDescripcion();            
        }
    }
   
    
    public ActividadEntity toEntity()
    {
        ActividadEntity entity = new ActividadEntity();
        entity.setId(this.getId());
        entity.setTareaAsignada(this.getTareaAsignada());
        entity.setDescripcion(this.getDescripcion());
        return entity;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTareaAsignada() {
        return tareaAsignada;
    }

    public void setTareaAsignada(String tareaAsignada) {
        this.tareaAsignada = tareaAsignada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString()
    {
        return "{id :"+getId()+", tareaAsignada: \""+getTareaAsignada() +"\" +\", descripcion: \""+getDescripcion() +"\"}";
    }
}
