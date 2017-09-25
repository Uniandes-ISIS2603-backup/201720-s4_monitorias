package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;
import co.edu.uniandes.csw.monitoria.entities.IdiomaEntity;
import java.util.List;
import co.edu.uniandes.csw.monitoria.entities.MonitorEntity;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mf.mena
 */
public class MonitorDetailDTO extends MonitorDTO{

 /*  private List<IdiomaDTO> idiomas;*/
    private List<HorarioDTO> horarios;
 
  
    /**
     * Constructor por defecto
     */
    public MonitorDetailDTO() {
    }
       
/**
     * Constructor por defecto
     */
    public MonitorDetailDTO(MonitorEntity monitor) {
        super(monitor);
        listEntity2listDTOHorario(monitor.getHorarios());
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public MonitorEntity toEntity() {
        MonitorEntity monitorE = super.toEntity();
        monitorE.setHorarios(listDTO2listEntityHorario(this.horarios));
        return monitorE;
    }
    
    //Cambios de lista Idiomas
    
    private List<IdiomaEntity> listDTO2listEntityIdioma(List<IdiomaDTO> entityList) {
        List<IdiomaEntity> list = new ArrayList<>();
        for (IdiomaDTO entity : entityList) {
            list.add(entity.toEntity());
        }
        return list;
    }
    private List<IdiomaDTO> listEntity2listDTOIdioma(List<IdiomaEntity> entityList) {
        List<IdiomaDTO> list = new ArrayList<>();
        for (IdiomaEntity entity : entityList) {
            list.add(new IdiomaDTO(entity));
        }
        return list;
    }
    
      //Cambios de lista Horarios
    
    private List<HorarioEntity> listDTO2listEntityHorario(List<HorarioDTO> dtoList) {
        
        List<HorarioEntity> list = new ArrayList<>();
        if(dtoList!=null){
        for (HorarioDTO dto : dtoList) {
            list.add(dto.toEntity());
        }
        }
        return list;
    }
    private List<HorarioDTO> listEntity2listDTOHorario(List<HorarioEntity> entityList) {
        List<HorarioDTO> list = new ArrayList<>();
        if(entityList!=null){
        for (HorarioEntity entity : entityList) {
            list.add(new HorarioDTO(entity));
        }
         }
        
        return list;
    }
 
}
