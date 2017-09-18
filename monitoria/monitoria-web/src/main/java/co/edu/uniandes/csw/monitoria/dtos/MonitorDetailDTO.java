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
     * Constructor por defecto
     */
    public MonitorDetailDTO(MonitorEntity monitor) {
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
 
}
