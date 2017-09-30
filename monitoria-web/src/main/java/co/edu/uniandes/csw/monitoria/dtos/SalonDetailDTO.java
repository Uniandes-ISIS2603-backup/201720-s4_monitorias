/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;
import co.edu.uniandes.csw.monitoria.entities.SalonEntity;
import co.edu.uniandes.csw.monitoria.entities.SedeEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author s.guzman
 */
public class SalonDetailDTO  extends SalonDTO
{
    /**
     * Sede del salon
     */
    private SedeDTO sede;
    
    /**
     * Lista de horarios de la sede
     */
    private List<HorarioDTO> horariosMonitoria;
    
    
     /**
     * Lista de horarios de la sede
     */
    private List<HorarioDTO> horariosAtencion;
    
    /**
     * Constructor por defecto
     */
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
        
        if (salonE.getHorariosMonitoria()!= null) 
        {
            horariosMonitoria = new ArrayList<>();
            for (HorarioEntity entityHorario : salonE.getHorariosMonitoria())
            {
                horariosMonitoria.add(new HorarioDTO(entityHorario));
            }
        }
        
        if (salonE.getHorariosAtencion()!= null) 
        {
            horariosAtencion = new ArrayList<>();
            for (HorarioEntity entityHorario : salonE.getHorariosAtencion())
            {
                horariosAtencion.add(new HorarioDTO(entityHorario));
            }
        }

        if (salonE.getSede()!=null)
        {
            this.sede = new SedeDTO(salonE.getSede());
        }
        else 
        {
            salonE.setSede(null);
        }
        
    }
    
    public SedeDTO getSede()
    {
        return sede;
    }
    
    public void setSede (SedeDTO pSede)
    {
        this.sede = pSede;
    }
    /**
     * Vuelve un detail en una entity
     * @return 
     */
    @Override
    public SalonEntity toEntity()
    {
        SalonEntity salonE = super.toEntity();
        
        if (this.horariosMonitoria != null)
        {
            List<HorarioEntity> horarioEntity = new ArrayList<>();
            for (HorarioDTO dtoHorario : horariosMonitoria) 
            {
                horarioEntity.add(dtoHorario.toEntity());
            }
            salonE.setHorariosMonitoria(horarioEntity);
        }
        
        if (this.horariosAtencion != null)
        {
            List<HorarioEntity> horarioEntity = new ArrayList<>();
            for (HorarioDTO dtoHorario : horariosAtencion) 
            {
                horarioEntity.add(dtoHorario.toEntity());
            }
            salonE.setHorariosAtencion(horarioEntity);
        }

        if(this.sede!=null)
        {
            SedeEntity sede = this.sede.toEntity();
            salonE.setSede(sede);
        }
        
        return salonE;
    }
    public List<HorarioDTO> getHorariosMonitoria()
    {
        return horariosMonitoria;
    }
    public void setHorariosMonitoria ( List<HorarioDTO> pHorarioes)
    {
        this.horariosMonitoria = pHorarioes;
    }
    
    
        public List<HorarioDTO> getHorariosAtencion()
    {
        return horariosAtencion;
    }
    public void setHorariosAtencion ( List<HorarioDTO> pHorarioes)
    {
        this.horariosAtencion = pHorarioes;
    }
}
