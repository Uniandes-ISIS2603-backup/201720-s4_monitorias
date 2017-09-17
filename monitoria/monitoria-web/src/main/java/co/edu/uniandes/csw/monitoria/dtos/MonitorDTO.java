/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.MonitorEntity;
import java.util.List;


/**
 * MonitorDTO Objeto de transferencia de datos de Monitores. Los DTO
 * contienen las represnetaciones de los JSON que se transfieren entre el cliente y el servidor
 * @author mf.mena
 */
public class MonitorDTO {
    private Long id;
    private String name;
    private Long codigo;
     private Double valPromedio;
    private List<IdiomaDTO> idiomas;
    
    /**
     * Constructor por defecto
     */
    public MonitorDTO() {
    }

    /**
     * Conviertir Entity a DTO
     * (Crea un nuevo DTO con los valores que recibe en la entidad que viene de argumento.
     * @param editorial: Es la entidad que se va a convertir a DTO 
     */
    public MonitorDTO(MonitorEntity monitor) {
        this.id = monitor.getId();
        this.name = monitor.getName();
        this.codigo=monitor.getCodigo();
        this.valPromedio=monitor.getValPromedio();
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
     * @return el codigo del monitor
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * @param id poner el codigo del monitor
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * @param id poner el codigo del monitor
     */
    public void setValPromedio(Double valPromedio) {
        this.valPromedio = valPromedio;
    }

    /**
     * @return el nombre del monitor
     */
    public Double getVAlPromedio() {
        return valPromedio;
    }
    /**
     * @return el nombre del monitor
     */
    public String getName() {
        return name;
    }

    /**
     * @param name poner el nombre del monitor
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Convertir DTO a Entity
     * @return Un Entity con los valores del DTO 
     */
    public MonitorEntity toEntity() {
        MonitorEntity entity = new MonitorEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setCodigo(this.codigo);
        entity.setValorPromedio(this.valPromedio);
        return entity;
    }
    
}
