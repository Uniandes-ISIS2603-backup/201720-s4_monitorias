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
public class BibliotecaDetailDTO extends BibliotecaDTO{
    
    /**
     * Recursos de la biblioteca
     * 
     */
    private List<RecursoDetailDTO> recursos;
    
    /**
     * Constructor por defecto
     */
    public BibliotecaDetailDTO(){
        super();
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public BibliotecaDetailDTO(BibliotecaEntity entity){
        
        super(entity);
        
        if(entity != null){
                if(entity.getRecursos() != null){
            
                    recursos = new ArrayList<>();
                    for(RecursoEntity entityRecurso: entity.getRecursos()){
                    recursos.add(new RecursoDetailDTO(entityRecurso));
                     }
                }
        }
    }
    
    @Override
    public BibliotecaEntity toEntity(){
        BibliotecaEntity bibliotecaE = super.toEntity();
        
        if(recursos != null){
            List<RecursoEntity> recursosEntity = new ArrayList<>();
            for(RecursoDetailDTO dtoRecurso: recursos){
                RecursoEntity recursoEntity = dtoRecurso.toEntity();
                recursoEntity.setBiblioteca(bibliotecaE);
                recursosEntity.add(recursoEntity);
            }
            bibliotecaE.setRecursos(recursosEntity);
        }
        return bibliotecaE;
    }
    
    public List<RecursoDetailDTO> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<RecursoDetailDTO> recursos) {
        this.recursos = recursos;
    }
    
}
