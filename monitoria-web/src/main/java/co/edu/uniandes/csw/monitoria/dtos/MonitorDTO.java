/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;
import co.edu.uniandes.csw.monitoria.entities.IdiomaEntity;
import co.edu.uniandes.csw.monitoria.entities.MonitorEntity;
import co.edu.uniandes.csw.monitoria.entities.PagoEntity;
import java.util.ArrayList;
import java.util.List;


/**
 * MonitorDTO Objeto de transferencia de datos de Monitores. Los DTO
 * contienen las represnetaciones de los JSON que se transfieren entre el cliente y el servidor
 * @author mf.mena
 */
public class MonitorDTO {
    private Integer tipo;
    private Long id;
    private String nombre;
    private Long codigo;
    private Double valPromedio;
   
    
    /**
     * Constructor por defecto
     */
    public MonitorDTO() {
    }

    /**
     * Conviertir Entity a DTO
     * (Crea un nuevo DTO con los valores que recibe en la entidad que viene de argumento.
     * @param monitor: Es la entidad que se va a convertir a DTO 
     */
    public MonitorDTO(MonitorEntity monitor) {
        this.id = monitor.getId();
        this.nombre = monitor.getNombre();
        this.codigo=monitor.getCodigo();
        this.valPromedio=monitor.getValPromedio();
        this.tipo=monitor.getTipo();
    }

    /**
     * @return el id del monitor
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id poner el id del monitor
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @return el id del monitor
     */
    public Integer getTipo() {
        return tipo;
    }

    /**
     * @param tipo poner el id del monitor
     */
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    /**
     * @return el codigo del monitor
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * @param codigo poner el codigo del monitor
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * @param valPromedio poner el codigo del monitor
     */
    public void setValPromedio(Double valPromedio) {
        this.valPromedio = valPromedio;
    }
    /**
     * @return el nombre del monitor
     */
    public Double getValPromedio() {
        return valPromedio;
    }
      /**
     * @param nombre del monitor
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * @return el nombre del monitor
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Convertir DTO a Entity
     * @return Un Entity con los valores del DTO 
     */
    public MonitorEntity toEntity() {
        MonitorEntity entity = new MonitorEntity();
        entity.setId(this.id);
        entity.setNoombre(this.nombre);
        entity.setCodigo(this.codigo);
        entity.setTipo(this.tipo);
        entity.setValorPromedio(this.valPromedio);
        //entity.setIdioma(listDTO2listEntityIdioma(this.idiomas));
        //entity.setHorarios(listDTO2listEntityHorario(this.horarios));
        return entity;
    }
    
    
    
  
    
}
