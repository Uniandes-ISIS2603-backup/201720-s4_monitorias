package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.EstudianteEntity;
import java.util.List;
import co.edu.uniandes.csw.monitoria.entities.MonitoriaEntity;
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

private MonitoriaEntity monitoria;
  
    
  public EstudianteDetailDTO(){
       //Constructor por defecto
    }
    
     /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public EstudianteDetailDTO( EstudianteEntity entity) {
        super(entity);
        monitoria=entity.getMonitoria();
    }
}
