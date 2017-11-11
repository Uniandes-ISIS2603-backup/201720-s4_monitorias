/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.ValoracionEntity;

/**
 *
 * @author mf.mena
 */
public class ValoracionDetailDTO extends ValoracionDTO {

    private MonitorDTO monitor;

    public MonitorDTO getMonitor() {
        return monitor;
    }

    public void setMonitor(MonitorDTO monitor) {
        this.monitor = monitor;
    }
    public ValoracionDetailDTO(){super();}
    public ValoracionDetailDTO(ValoracionEntity valoracion) {

        super(valoracion);
        this.monitor = new MonitorDTO(valoracion.getMonitor());
    }

    public ValoracionEntity toEntity() {
        ValoracionEntity entity = super.toEntity();
        entity.setMonitor(this.monitor.toEntity());
        return entity;
    }

}
