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
    private Long tipo;
    private Long id;
    private String name;
    private Long codigo;
    private String valPromedio;
   /* private List<IdiomaDTO> idiomas;
    private List<HorarioDTO> horarios;*/
    
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
        this.name = monitor.getName();
        this.codigo=monitor.getCodigo();
        this.valPromedio=monitor.getValPromedio()+"";
        this.tipo=monitor.getTipo().longValue();
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
    public Long getTipo() {
        return tipo;
    }

    /**
     * @param tipo poner el id del monitor
     */
    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }
    /**
     * @return los horarios del monitor
     */
   /* public List<HorarioDTO> getHorario() {
        return horarios;
    }*/
     /**
     * @param id poner los horario del monitor
     */
    /*public void setHorarios(List<HorarioDTO> horarios) {
        this.horarios = horarios;
    }*/
    /**
     * @return los Idiomas del monitor
     */
    /*public List<IdiomaDTO> getIdiomas() {
        return idiomas;
    }*/
     /**
     * @param idiomas poner los horario del monitor
     */
   /* public void setIdiomas(List<IdiomaDTO>idiomas) {
        this.idiomas =idiomas;
    }*/
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
    public void setValPromedio(String valPromedio) {
        this.valPromedio = valPromedio;
    }

    /**
     * @return el nombre del monitor
     */
    public String getValPromedio() {
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
        entity.setTipo(this.tipo.intValue());
        entity.setValorPromedio(Double.parseDouble(this.valPromedio));
       // entity.setIdioma(listDTO2listEntityIdioma(this.idiomas));
        //entity.setHorarios(listDTO2listEntityHorario(this.horarios));
        return entity;
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
        for (HorarioDTO dto : dtoList) {
            list.add(dto.toEntity());
        }
        return list;
    }
    private List<HorarioDTO> listEntity2listDTOHorario(List<HorarioEntity> entityList) {
        List<HorarioDTO> list = new ArrayList<>();
        for (HorarioEntity entity : entityList) {
            list.add(new HorarioDTO(entity));
        }
        return list;
    }
    
}
