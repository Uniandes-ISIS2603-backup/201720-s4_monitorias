/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.IdiomaEntity;
import co.edu.uniandes.csw.monitoria.entities.RecursoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ca.mendoza
 */
public class IdiomaDetailDTO extends IdiomaDTO {
    
    private List<RecursoDTO> recursos;
    
    public IdiomaDetailDTO(){
        super();
    }
     public IdiomaDetailDTO(IdiomaEntity entity)
    {
        super(entity);
        
        if(entity != null){
                if(entity.getRecursos() != null){
            
                    recursos = new ArrayList<>();
                    for(RecursoEntity entityRecurso: entity.getRecursos()){
                    recursos.add(new RecursoDTO(entityRecurso));
                     }
                }
        }
    }
    @Override
    public IdiomaEntity toEntity() {
        IdiomaEntity idiomaEntity = super.toEntity();
        if(recursos != null){
            List<RecursoEntity> recursosEntity = new ArrayList<>();
            for(RecursoDTO dtoRecurso: recursos){
                RecursoEntity recursoEntity = dtoRecurso.toEntity();
                recursoEntity.setIdioma(idiomaEntity);
                recursosEntity.add(recursoEntity);
            }
            idiomaEntity.setRecursos(recursosEntity);
        }
        return idiomaEntity;
    }
    
     public List<RecursoDTO> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<RecursoDTO> recursos) {
        this.recursos = recursos;
    }
    
}
