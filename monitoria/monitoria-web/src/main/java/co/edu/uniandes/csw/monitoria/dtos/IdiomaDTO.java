/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.IdiomaEntity;
import co.edu.uniandes.csw.monitoria.entities.RecursoEntity;
import java.util.List;

/**
 *
 * @author ca.mendoza
 */
public class IdiomaDTO {
    
    private Long id;
    private String idioma;
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
  
    public IdiomaDTO(IdiomaEntity entity)
    {
        if(entity!=null)
        {   this.id= entity.getId();
            this.idioma = entity.getIdioma();  
            this.recursos = entity.getRecursos();
        }
    }
    
    public IdiomaEntity toEntity()
    {
        IdiomaEntity entity = new IdiomaEntity();
        entity.setId(this.getId());
        entity.setIdioma(this.getIdioma());
        entity.setRecursos(this.getRecursos());
        return entity;
    }
    
    public IdiomaDTO(Long id, String idioma, List<RecursoEntity> recursos)
    {   super();
        this.id=id;
        this.idioma = idioma;
        this.recursos = recursos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    public IdiomaDTO(IdiomaEntity entity)
    {
        if(entity!=null)
        {   this.id= entity.getId();
            this.idioma= entity.getIdioma();  
            this.recursos = entity.getRecursos();
        }
    }
    
    public IdiomaEntity toEntity()
    {
        IdiomaEntity entity = new IdiomaEntity();
        entity.setId(this.getId());
        entity.setIdioma(this.getIdioma());
        entity.setRecursos(this.getRecursos());
        return entity;
    }
    
    public IdiomaDTO(Long id, String idioma, List<RecursoEntity> recursos)
    {   super();
        this.id=id;
        this.idioma = idioma;
        this.recursos = recursos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public String toString()
    {
        return "{id :"+getId()+", idioma: \""+getIdioma()+"\"}";
    }

    
}
