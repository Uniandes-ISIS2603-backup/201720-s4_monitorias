/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.IdiomaEntity;

/**
 *
 * @author ca.mendoza
 */
public class IdiomaDTO {
    
    private Long id;
    private String idioma;
   
    public IdiomaDTO()
    {
        //constructor por defecto
    }
    
    public IdiomaDTO(IdiomaEntity entity){
        this.id = entity.getId();
        this.idioma = entity.getIdioma();
    }
    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
  
    
    
    public IdiomaEntity toEntity()
    {
        IdiomaEntity entity = new IdiomaEntity();
        entity.setId(this.getId());
        entity.setIdioma(this.getIdioma());
        return entity;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

}
