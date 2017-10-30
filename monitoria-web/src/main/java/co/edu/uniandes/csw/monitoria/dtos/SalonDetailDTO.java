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
     * Lista de horarios en los que hay monitoria 
     */
    private List<HorarioDTO> horariosMonitoria;
    
    
     /**
     * Lista de horarios dispobibles
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
                    horariosMonitoria = new ArrayList<>();
                                horariosAtencion = new ArrayList<>();


        
        if (salonE.getHorariosMonitoria()!= null) 
        {
            //horariosMonitoria = new ArrayList<>();
            for (HorarioEntity entityHorario : salonE.getHorariosMonitoria())
            {
                horariosMonitoria.add(new HorarioDTO(entityHorario));
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
    /**
     * Metodo para consutar la sede del salon detail
     * @return 
     */
    public SedeDTO getSede()
    {
        return sede;
    }
    /**
     * metodo para editar la sede del salon
     * @param pSede 
     */
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
        }

        if(this.sede!=null)
        {
            SedeEntity sede = this.sede.toEntity();
            salonE.setSede(sede);
        }

        return salonE;
    }
    /**
     * Metodo para consutlar los horarios en los que se dan monitorias
     * @return 
     */
    public List<HorarioDTO> getHorariosMonitoria()
    {
        return horariosMonitoria;
    }
    /**
     * metodo para remplazar la lista que contiene los horarios en los cuales se da monitoria
     * @param pHorarioes 
     */
    public void setHorariosMonitoria ( List<HorarioDTO> pHorarioes)
    {
        this.horariosMonitoria = pHorarioes;
    }
    
    /**
     * Metodo para consultar los horarios de atencion de un salon
     * @return 
     */
        public List<HorarioDTO> getHorariosAtencion()
    {
        return horariosAtencion;
    }
        /**
         * Metodo para rempazar la lista que contiene los horarios disponibles de atencion de un salon
         * @param pHorarioes 
         */
    public void setHorariosAtencion ( List<HorarioDTO> pHorarioes)
    {
        this.horariosAtencion = pHorarioes;
    }
}
