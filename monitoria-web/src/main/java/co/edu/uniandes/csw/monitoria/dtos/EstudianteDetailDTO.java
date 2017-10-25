package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.EstudianteEntity;
import co.edu.uniandes.csw.monitoria.entities.MonitoriaEntity;

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

private MonitoriaDTO monitoria;
  
    
  public EstudianteDetailDTO(){
       //Constructor por defecto
    }
  
    public MonitoriaDTO getMonitoria() {
        return monitoria;
    }

  
    public void setMonitoria(MonitoriaDTO pMonitoria) {
        this.monitoria=pMonitoria;
    }
     /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public EstudianteDetailDTO( EstudianteEntity entity) {
        super(entity);
        if(entity.getMonitoria()==null)
        {
            monitoria=null;
        }
        else{
        monitoria= new MonitoriaDTO(entity.getMonitoria());
        }
    }
    public EstudianteEntity toEntity(){
        EstudianteEntity entity=super.toEntity();
        if(monitoria==null)
        {
            entity.setMonitoria(null);
        }
        else
        entity.setMonitoria(this.monitoria.toEntity());
      return entity;
    }
}
