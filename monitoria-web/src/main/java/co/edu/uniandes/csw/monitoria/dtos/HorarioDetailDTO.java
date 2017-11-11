package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;
import co.edu.uniandes.csw.monitoria.entities.MonitorEntity;
import co.edu.uniandes.csw.monitoria.entities.SalonEntity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cc.cardenas
 */
public class HorarioDetailDTO extends HorarioDTO{


  /*
    atributo relacion salon
    */
    private SalonEntity salon;
    /*
    atributo relacion 
    */
    private MonitorEntity monitor;

  
public HorarioDetailDTO(){}
        

    
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
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public HorarioDetailDTO( HorarioEntity entity) {
        super(entity);
      
        entity.setMonitor(entity.getMonitor());
        
    }
     
}
