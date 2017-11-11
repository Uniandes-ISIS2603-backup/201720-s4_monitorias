package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;
import co.edu.uniandes.csw.monitoria.entities.IdiomaEntity;
import java.util.List;
import co.edu.uniandes.csw.monitoria.entities.MonitorEntity;
import co.edu.uniandes.csw.monitoria.entities.MonitoriaEntity;
import co.edu.uniandes.csw.monitoria.entities.ValoracionEntity;
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

 private List<IdiomaDTO> idiomas;

 private List<ValoracionDTO> valoraciones;
 private List<MonitoriaDTO> monitorias;
 
  
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
       
        idiomas=listEntity2listDTOIdioma(monitor.getIdioma());
        monitorias=listEntity2listDTOMonitoria(monitor.getMonitorias());
        valoraciones=listEntity2listDTOValoracion(monitor.getValoraciones());
    }
    
     /**
     * @return los horarios del monitor
     */
    public List<MonitoriaDTO> getMonitorias() {
        return monitorias;
    }
   
    
    public List<ValoracionDTO> getValoraciones() {
        return valoraciones;
    }
     /**
     * @param id poner los horario del monitor
     */
    public void setValoraciones(List<ValoracionDTO> valoraciones) {
        this.valoraciones= valoraciones;
    }
    

     /**
     * @param id poner los horario del monitor
     */
    public void setMonitorias(List<MonitoriaDTO> monitorias) {
        this.monitorias = monitorias;
    }
    /**
     * @return los Idiomas del monitor
     */
    public List<IdiomaDTO> getIdiomas() {
        return idiomas;
    }
     /**
     * @param idiomas poner los horario del monitor
     */
    public void setIdiomas(List<IdiomaDTO>idiomas) {
        this.idiomas =idiomas;
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public MonitorEntity toEntity() {
        MonitorEntity monitorE = super.toEntity();
       
        monitorE.setIdioma(listDTO2listEntityIdioma(this.idiomas));
        monitorE.setMonitorias(listDTO2listEntityMonitoria(this.monitorias));
        monitorE.setValoraciones(listDTO2listEntityValoracion(this.valoraciones));
        return monitorE;
    }    
    //Cambios de lista Idiomas
    
    private List<IdiomaEntity> listDTO2listEntityIdioma(List<IdiomaDTO> entityList) {
        List<IdiomaEntity> list = new ArrayList<>();
        if(entityList!=null){
        for (IdiomaDTO entity : entityList) {
            list.add(entity.toEntity());
        }
        }
        return list;
    }
    private List<IdiomaDTO> listEntity2listDTOIdioma(List<IdiomaEntity> entityList) {
        List<IdiomaDTO> list = new ArrayList<>();
        if(entityList!=null){
        for (IdiomaEntity entity : entityList) {
            list.add(new IdiomaDTO(entity));
        }
        }
        return list;
    }
    
  
 
    
    
    
    
    
    
    
    
       private List<ValoracionEntity> listDTO2listEntityValoracion(List<ValoracionDTO> dtoList) {
        
        List<ValoracionEntity> list = new ArrayList<>();
        if(dtoList!=null){
        for (ValoracionDTO dto : dtoList) {
            list.add(dto.toEntity());
        }
        }
        return list;
    }
    private List<ValoracionDTO> listEntity2listDTOValoracion(List<ValoracionEntity> entityList) {
        List<ValoracionDTO> list = new ArrayList<>();
        if(entityList!=null){
        for (ValoracionEntity entity : entityList) {
            list.add(new ValoracionDTO(entity));
        }
         }
        
        return list;
    }
    
    
       private List<MonitoriaEntity> listDTO2listEntityMonitoria(List<MonitoriaDTO> dtoList) {
        
        List<MonitoriaEntity> list = new ArrayList<>();
        if(dtoList!=null){
        for (MonitoriaDTO dto : dtoList) {
            list.add(dto.toEntity());
        }
        }
        return list;
    }
    private List<MonitoriaDTO> listEntity2listDTOMonitoria(List<MonitoriaEntity> entityList) {
        List<MonitoriaDTO> list = new ArrayList<>();
        if(entityList!=null){
        for (MonitoriaEntity entity : entityList) {
            list.add(new MonitoriaDTO(entity));
        }
         }
        
        return list;
    }
    
    
    
    
}
