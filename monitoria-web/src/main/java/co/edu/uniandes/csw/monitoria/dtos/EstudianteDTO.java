/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;


import co.edu.uniandes.csw.monitoria.entities.EstudianteEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * EstudianteDTO Objeto de transferencia de datos de Monitores. Los DTO
 * contienen las represnetaciones de los JSON que se transfieren entre el cliente y el servidor
 * @author cc.cardenas
 */
public class EstudianteDTO {
  
    private Long id;
    private String name;
    private Long codigo;
    private String correo;
    private Boolean penalizacion;
     @Temporal(TemporalType.TIMESTAMP)
    private Date ultimamonitoria;
    
    /**
     * Constructor por defecto
     */
    public EstudianteDTO() {
       
    }

    /**
     * Conviertir Entity a DTO
     * (Crea un nuevo DTO con los valores que recibe en la entidad que viene de argumento.
     * @param editorial: Es la entidad que se va a convertir a DTO 
     */
    public EstudianteDTO(EstudianteEntity estudiante) {
        this.id = estudiante.getId();
        this.name = estudiante.getName();
        this.codigo=estudiante.getCodigo();
        this.correo=estudiante.getCorreo();
        this.ultimamonitoria=estudiante.getUltimaMonitoria();
        this.penalizacion=estudiante.getPenalizacion();
        
    
    }

    /**
     * @return el id del estudiante
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id poner el id del estudiante
     */
    public void setId(Long id) {
        this.id = id;
    }
       /**
     * @return el id del estudiante
     */
    public Date getUltimaMonitoria() {
        return ultimamonitoria;
    }

    /**
     * @param id poner el id del estudiante
     */
    public void setUltimaMonitoria(Date pFechaUltima) {
        this.ultimamonitoria=pFechaUltima;
    }
    public void setPenalizacion(Boolean pPenalizacion){
        this.penalizacion=pPenalizacion;
    }
    public Boolean getPenalizacion(){
        return this.penalizacion;
    }
    /**
     * @return el codigo del estudiante
     */
    public Long getCodigo() {
        return codigo;
    }
    public String getCorreo(){
        return correo;
    }
    public void setCorreo(String correo){
        this.correo=correo;
    }
    /**
     * @param id poner el codigo del estudiante
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    
    /**
     * @return el nombre del estudiante
     */
    public String getName() {
        return name;
    }

    /**
     * @param name poner el nombre del estudiante
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Convertir DTO a Entity
     * @return Un Entity con los valores del DTO 
     */
    public EstudianteEntity toEntity() {
       EstudianteEntity entity = new EstudianteEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setCodigo(this.codigo);
        entity.setCorreo(this.correo);
        
       
        
        entity.setPenalizacion(penalizacion);
        entity.setUltimaMonitoria(ultimamonitoria);
        
       
        return entity;
    }
    
  
    
}
