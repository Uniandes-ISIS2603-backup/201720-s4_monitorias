/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.ValoracionEntity;
import java.util.Date;

/**
 *
 * @author l.mejia
 */
public class ValoracionDTO {
    private Long id;
    private String comentario;
    private Integer calificacion;
    private Date fecha;
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id=id;
    }
     public String getComentario(){
        return comentario;
    }
    
    public void setComentario(String comentario){
        this.comentario=comentario;
    }
     public Integer getCalificacion(){
        return calificacion;
    }
    
    public void setCalificacion(Integer calificacion){
        this.calificacion=calificacion;
    }
    
    public Date getFecha(){
        return fecha;
    }
    
    public void setFecha(Date fecha){
        this.fecha=fecha;
    }
    
     /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param valoracion: Es la entidad que se va a convertir a DTO
     */
    public ValoracionDTO(ValoracionEntity valoracion){
        this.id = valoracion.getId();
        this.comentario = valoracion.getComentario();
        this.calificacion=valoracion.getCalificacion();
        this.fecha=valoracion.getFecha();
    }
    
    /**
    * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public ValoracionEntity toEntity(){
        ValoracionEntity entity = new ValoracionEntity();
        entity.setId(this.id);
        entity.setComentario(this.comentario);
        entity.setCalificacion(this.calificacion);
        entity.setFecha(this.fecha);
        return entity;
    }
}
