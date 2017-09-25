/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.RecursoEntity;

/**
 * Un recurso representa un libro, una guia, o algún elemento que elemento que puede ser consultado en la biblioteca
 * @author ms.osorio
 */

public class RecursoDTO {
    /**
     * Nombre del recurso
     */
    private String name;
    /**
     * editorial del recurso
     */
    private String editorial;
    /**
     * numero identificador del recurso
     */
    private Long id;
    /**
     * representa si el recurso está disponible o no
     */
    private Boolean disponibilidad;
   

    

    /**
     * Constructor por defecto
     */
     public RecursoDTO(){
        //Constructor por defecto
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param recurso: Es la entidad que se va a convertir a DTO
     */
    public RecursoDTO(RecursoEntity recurso){
        this.id = recurso.getId();
        this.name = recurso.getName();
        this.editorial = recurso.getEditorial();
        this.disponibilidad = recurso.isDisponibilidad();
    }
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
   
    
     /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public RecursoEntity toEntity(){
        RecursoEntity entity = new RecursoEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setEditorial(this.editorial);
        entity.setDisponibilidad(this.disponibilidad);
        return entity;
    }
    
}
