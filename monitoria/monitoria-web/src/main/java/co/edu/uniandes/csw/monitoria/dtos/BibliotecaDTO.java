/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.BibliotecaEntity;
import co.edu.uniandes.csw.monitoria.entities.RecursoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ms.osorio
 */
public class BibliotecaDTO {
    
    /**
     * Identificador de la biblioteca
     */
    private Long id;
    
    /**
     * nombre de la bilbioteca
     */
    private String name;
    /**
     * ubicación de la biblioteca
     */
    private String ubicacion;
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
     /**
     * Constructor por defecto
     */
    public BibliotecaDTO(){
        
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param biblioteca: Es la entidad que se va a convertir a DTO
     */
    public BibliotecaDTO(BibliotecaEntity biblioteca){
        this.id = biblioteca.getId();
        this.name = biblioteca.getName();
        this.ubicacion = biblioteca.getUbicacion();
    }
    
    /**
    * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public BibliotecaEntity toEntity(){
        BibliotecaEntity entity = new BibliotecaEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setUbicacion(this.ubicacion);
        return entity;
    }
    
   
    
    
   
    
}
