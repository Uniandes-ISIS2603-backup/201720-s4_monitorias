/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.ActividadEntity;
import co.edu.uniandes.csw.monitoria.entities.RecursoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ca.mendoza
 */
public class ActividadDetailDTO extends ActividadDTO{
    
     private List<RecursoDTO> recursos;
    
     public List<RecursoDTO> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<RecursoDTO> recursos) {
        this.recursos = recursos;
    }
    
     public ActividadDetailDTO(ActividadEntity entity)
    {
        super(entity);
        listEntity2listDTO(entity.getRecursos());
    }
     
    @Override
    public ActividadEntity toEntity() {
        ActividadEntity ActividadEntity = super.toEntity();
        ActividadEntity.setRecursos(listDTO2listEntity(this.recursos));
        return ActividadEntity;
    }
    private List<RecursoEntity> listDTO2listEntity(List<RecursoDTO> entityList) {
        List<RecursoEntity> list = new ArrayList<>();
        for (RecursoDTO entity : entityList) {
            list.add(entity.toEntity());
        }
        return list;
    }
    private List<RecursoDTO> listEntity2listDTO(List<RecursoEntity> entityList) {
        List<RecursoDTO> list = new ArrayList<>();
        for (RecursoEntity entity : entityList) {
            list.add(new RecursoDTO(entity));
        }
        return list;
    }
    
}
