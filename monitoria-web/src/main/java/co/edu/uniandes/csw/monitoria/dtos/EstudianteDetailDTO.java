package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.EstudianteEntity;
import co.edu.uniandes.csw.monitoria.entities.MonitoriaEntity;
import java.util.List;

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

private List<MonitoriaDTO> monitorias;
  
    
  public EstudianteDetailDTO(){
       //Constructor por defecto
    }
  
    public List<MonitoriaDTO> getMonitorias()
    {
        return monitorias;
    }
    public void setMonitorias(List<MonitoriaDTO> monitorias)
    {
        this.monitorias=monitorias;
    }
     /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public EstudianteDetailDTO( EstudianteEntity entity) {
        super(entity);
        if(entity.getMonitorias()==null)
        {
            monitorias=null;
        }
        else{
       entity.getMonitorias().forEach((x) -> {
            this.monitorias.add(new MonitoriaDTO(x));
        });
        }
    }
    public EstudianteEntity toEntity(){
        EstudianteEntity entity=super.toEntity();
   
       this.monitorias.forEach((x) -> {
            entity.getMonitorias().add(x.toEntity());
        });
      return entity;
    }
}
