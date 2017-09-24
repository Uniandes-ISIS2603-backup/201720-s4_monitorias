/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;
import co.edu.uniandes.csw.monitoria.entities.SalonEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author s.guzman
 */
public class SalonDetailDTO  extends SalonDTO
{
    private SedeDTO sede;
    
    private List<HorarioDTO> horarios;
    
    public SalonDetailDTO ()
    {
        super();
    }
        /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param salonE
     */
    public SalonDetailDTO (SalonEntity salonE)
    {
        super(salonE);
        
        if (salonE.getHorarios() != null) 
        {
            horarios = new ArrayList<>();
            for (HorarioEntity entityHorario : salonE.getHorarios())
            {
                horarios.add(new HorarioDTO(entityHorario));
            }
        }

        if (salonE.getSede()!=null)
        {
            this.sede = new SedeDTO(salonE.getSede());
        }
        
    }
    
    @Override
    public SalonEntity toEntity()
    {
        SalonEntity salonE = super.toEntity();
        
        if (horarios != null)
        {
            List<HorarioEntity> horarioEntity = new ArrayList<>();
            for (HorarioDTO dtoHorario : horarios) 
            {
                horarioEntity.add(dtoHorario.toEntity());
            }
            salonE.SetHorarios(horarioEntity);
        }

        if(sede!=null)
        {
            salonE.setSede(sede.toEntity());
        }
        
        return salonE;
    }
    public List<HorarioDTO> getHorarioes()
    {
        return horarios;
    }
    public void setHorarioes ( List<HorarioDTO> pHorarioes)
    {
        this.horarios = pHorarioes;
    }
    
}
