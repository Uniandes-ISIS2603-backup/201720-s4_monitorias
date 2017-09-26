/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.SalonEntity;
import co.edu.uniandes.csw.monitoria.entities.SedeEntity;

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
    //private List<HorarioDTO> horarios;
    
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
        
//        if (salonE.getHorarios != null) 
//        {
 //           horarios = new ArrayList<>();
   //         for (HorarioEntity entityHorario : salonE.getHorarios())
     //       {
       //         horarios.add(new HorarioDTO(entityHorario));
         //   }
       // }

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
     * Vuelve un detail en una entity
     * @return 
     */
    @Override
    public SalonEntity toEntity()
    {
        SalonEntity salonE = super.toEntity();
        
 //       if (this.horarios != null)
  //      {
 //           List<HorarioEntity> horarioEntity = new ArrayList<>();
 //           for (HorarioDTO dtoHorario : horarios) 
 //           {
 //               horarioEntity.add(dtoHorario.toEntity());
 //           }
 //           salonE.SetHorarios(horarioEntity);
 //       }

        if(this.sede!=null)
        {
            SedeEntity sede = this.sede.toEntity();
            salonE.setSede(sede);
        }
        
        return salonE;
    }
 //   public List<HorarioDTO> getHorarioes()
 //   {
 //       return horarios;
 //   }
 //   public void setHorarioes ( List<HorarioDTO> pHorarioes)
 //   {
 //       this.horarios = pHorarioes;
 //   }
    
}
