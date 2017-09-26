/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.SalonEntity;
import co.edu.uniandes.csw.monitoria.entities.SedeEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author s.guzman
 */
public class SedeDetailDTO extends SedeDTO
{
    /**
     * Salones de la sede
     */
    private List<SalonDTO> salones;
    
    /**
     * Constructor por defecto
     */    
    public SedeDetailDTO ()
    {
        super();
    }
        /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param sedeE
     */
    public SedeDetailDTO (SedeEntity sedeE)
    {
        super(sedeE);
        
        if (sedeE != null)
        {
            if (sedeE.getSalones() != null) 
            {
                salones = new ArrayList<>();
                for (SalonEntity entitySalon : sedeE.getSalones())
                {
                    salones.add(new SalonDTO(entitySalon));
                }
            }             
        }    

    }
    /**
     * Pasa de DTO a entity
     * @return la entidad de sede
     */
    @Override
    public SedeEntity toEntity()
    {
        SedeEntity sedeE = super.toEntity();
        
        if (salones != null)
        {
            List<SalonEntity> salonEntity = new ArrayList<>();
            for (SalonDTO dtoSalon : salones) 
            {
                SalonEntity salonEnt = dtoSalon.toEntity();
                salonEnt.setSede(sedeE);
                salonEntity.add(salonEnt);
            }
            sedeE.setSalones(salonEntity);
        }
        
        return sedeE;
    }
    
    /**
     * Entrega los salones de la sede
     * @return 
     */
    public List<SalonDTO> getSalones()
    {
        return salones;
    }
    
    /**
     * Edita los salones de la sede
     * @param pSalones la nueva lista de salones de la sede
     */
    public void setSalones ( List<SalonDTO> pSalones)
    {
        this.salones = pSalones;
    }
    
    
}
