/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.RecursoEntity;

/**
 *
 * @author ms.osorio
 */
public class RecursoDetailDTO extends RecursoDTO{
    /**
     * Representa la biblioteca a la cual pertenece el recurso
     */
    
    private BibliotecaDTO biblioteca;
    
    /**
     * Representa el idioma al cual está asociado este recurso
     */
    private IdiomaDTO idioma;
    

    public RecursoDetailDTO() {
        
    }
     
    public RecursoDetailDTO(RecursoEntity entity) {
     super(entity);   
    }
    
    
    public BibliotecaDTO getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(BibliotecaDTO biblioteca) {
        this.biblioteca = biblioteca;
    }

    public IdiomaDTO getIdioma() {
        return idioma;
    }

    public void setIdioma(IdiomaDTO idioma) {
        this.idioma = idioma;
    }
    
    
}
