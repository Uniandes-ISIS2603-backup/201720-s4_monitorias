package co.edu.uniandes.csw.monitoria.dtos;

import java.util.List;
import co.edu.uniandes.csw.monitoria.entities.MonitorEntity;
import co.edu.uniandes.csw.monitoria.entities.PagoEntity;
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
 private List<PagoDTO> pagos;
  
    /**
     * Constructor por defecto
     */
    public MonitorDetailDTO() {
    }
    
    
     /**
     * @return los pagos del monitor
     */
    public List<PagoDTO> getPagos() {
        return pagos;
    }
     /**
     * @param id poner los horario del monitor
     */
    public void setPagos(List<PagoDTO> pagos) {
        this.pagos = pagos;
    }
/**
     * Constructor por defecto
     */
    public MonitorDetailDTO(MonitorEntity monitor) {
        super(monitor);
        listEntity2listDTO(monitor.getPagos());
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public MonitorEntity toEntity() {
        MonitorEntity monitorE = super.toEntity();
        monitorE.setPagos(listDTO2listEntity(this.pagos));
        return monitorE;
    }
    private List<PagoEntity> listDTO2listEntity(List<PagoDTO> entityList) {
        List<PagoEntity> list = new ArrayList<>();
        for (PagoDTO entity : entityList) {
            list.add(entity.toEntity());
        }
        return list;
    }
    private List<PagoDTO> listEntity2listDTO(List<PagoEntity> entityList) {
        List<PagoDTO> list = new ArrayList<>();
        for (PagoEntity entity : entityList) {
            list.add(new PagoDTO(entity));
        }
        return list;
    }
 
}
