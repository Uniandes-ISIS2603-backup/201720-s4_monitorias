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
    private List<SalonDTO> salones;
    
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
        
        if (sedeE.getSalones() != null) 
        {
            salones = new ArrayList<>();
            for (SalonEntity entitySalon : sedeE.getSalones())
            {
                salones.add(new SalonDTO(entitySalon));
            }
        } 
    }
    
    @Override
    public SedeEntity toEntity()
    {
        SedeEntity sedeE = super.toEntity();
        
        if (salones != null)
        {
            List<SalonEntity> salonEntity = new ArrayList<>();
            for (SalonDTO dtoSalon : salones) 
            {
                salonEntity.add(dtoSalon.toEntity());
            }
            sedeE.setSalones(salonEntity);
        }
        
        return sedeE;
    }
    public List<SalonDTO> getSalones()
    {
        return salones;
    }
    public void setSalones ( List<SalonDTO> pSalones)
    {
        this.salones = pSalones;
    }
    
    
}
