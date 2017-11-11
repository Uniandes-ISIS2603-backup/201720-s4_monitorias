package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.EstudianteEntity;
import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;
import java.util.List;
import co.edu.uniandes.csw.monitoria.entities.MonitorEntity;
import co.edu.uniandes.csw.monitoria.entities.PagoEntity;
import co.edu.uniandes.csw.monitoria.entities.SalonEntity;
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
public class HorarioDetailDTO extends HorarioDTO{


  
    private SalonEntity salon;
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
        entity.setSalon(entity.getSalon());       
    }
     
}
