package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.EstudianteEntity;
import java.util.List;
import co.edu.uniandes.csw.monitoria.entities.MonitorEntity;
import co.edu.uniandes.csw.monitoria.entities.PagoEntity;
import java.util.ArrayList;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cc.cardenas
 */
public class EstudianteDetailDTO extends EstudianteDTO{

private Boolean penalizacion;
  
    

    public EstudianteDetailDTO(EstudianteEntity nuevoEstudiante) {
       super(nuevoEstudiante);
       if (nuevoEstudiante != null) {
          penalizacion=nuevoEstudiante.getPenalizacion();
            }
    }
       
  

        

    
    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     
    public EstudianteEntity toEntity() {
         EstudianteEntity estudiante = super.toEntity();
        estudiante.setPenalizacion(this.penalizacion);
        estudiante.setUltimaMonitoria(this.ultimaMonitoria);
        return estudiante;
    }
     */
/**
     * Convierte un objeto AuthorDetailDTO a AuthorEntity incluyendo los
     * atributos de AuthorDTO.
     *
     * @return Nueva objeto AuthorEntity.
     *
     */
    @Override
    public EstudianteEntity toEntity() {
        EstudianteEntity entity = super.toEntity();
        entity.setPenalizacion(false);
        

        return entity;
    }
 
    public void setPenalizacion(Boolean pPenalizacion)
    {
        this.penalizacion=pPenalizacion;
    }
    public Boolean getPenalizacion(){
        return this.penalizacion;
    }
}
