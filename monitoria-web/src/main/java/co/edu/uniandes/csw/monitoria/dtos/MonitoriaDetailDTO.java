/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.MonitoriaEntity;
import java.util.ArrayList;

/**
 *
 * @author l.mejia
 */
public class MonitoriaDetailDTO extends MonitoriaDTO {
    
    IdiomaDTO idioma;
    ArrayList<ActividadDTO> actividades=new ArrayList<>();
    
    public ArrayList<ActividadDTO> getActividades()
    {
        return actividades;
    }
    public void setActividades(ArrayList<ActividadDTO> actividades)
    {
        this.actividades=actividades;
    }
    public IdiomaDTO getIdiomaDTO()
    {
        return this.idioma;
    }
    
    public void setIdioma(IdiomaDTO idioma)
    {
        this.idioma=idioma;
    }
    public MonitoriaDetailDTO()
    {
        super();
    }
    
    public MonitoriaDetailDTO(MonitoriaEntity entity)
    {
        this.idioma=new IdiomaDTO(entity.getIdioma());
        entity.getActividades().forEach((x) -> {
            this.actividades.add(new ActividadDTO(x));
        });
    }
    @Override
    public MonitoriaEntity toEntity()
    {   
        MonitoriaEntity entity=super.toEntity();
        entity.setIdioma(this.idioma.toEntity());
        this.actividades.forEach((x) -> {
            entity.getActividades().add(x.toEntity());
        });
        return entity;
    }
}
