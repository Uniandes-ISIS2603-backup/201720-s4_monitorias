/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.ActividadEntity;
import co.edu.uniandes.csw.monitoria.entities.RecursoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class ActividadDetailDTO extends ActividadDTO{
   
    private List<RecursoDTO> recursos;
    
    public ActividadDetailDTO(){
        super();
    }
     public ActividadDetailDTO(ActividadEntity entity)
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
    public ActividadEntity toEntity() {
        ActividadEntity ActividadEntity = super.toEntity();
        if(recursos != null){
            List<RecursoEntity> recursosEntity = new ArrayList<>();
            for(RecursoDTO dtoRecurso: recursos){
                RecursoEntity recursoEntity = dtoRecurso.toEntity();
                recursosEntity.add(recursoEntity);
            }
            ActividadEntity.setRecursos(recursosEntity);
        }
        return ActividadEntity;
    }
    
     public List<RecursoDTO> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<RecursoDTO> recursos) {
        this.recursos = recursos;
    }
    
}
