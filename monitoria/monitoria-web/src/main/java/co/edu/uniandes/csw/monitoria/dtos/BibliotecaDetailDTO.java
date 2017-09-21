/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.BibliotecaEntity;
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
    private List<RecursoDTO> recursos;
    

    
    
    
    /**
     * Constructor por defecto
     */
    public BibliotecaDetailDTO(){
        
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public BibliotecaDetailDTO(BibliotecaEntity entity){
        
        super(entity);
    }
    
    public List<RecursoDTO> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<RecursoDTO> recursos) {
        this.recursos = recursos;
    }
    
}
