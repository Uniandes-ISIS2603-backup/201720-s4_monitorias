 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.guzman
 * 
 */
@Entity
public class SalonEntity implements Serializable
{
    /**
     * Atributo que hace referencia a la disponibilidad de un salon
     */
    private boolean disponibilidad;
    
    /**
     * Atributo que hace referencia a la localizacion de un salon
     */   
    private String localizacion;
    
    /**
     * Atributo que hace referencia a la sede del salon
     */
    @PodamExclude
    @ManyToOne (fetch = FetchType.LAZY)
    private SedeEntity sede;
    
    /**
     * Atributo que hace referencia a  los horarios del salon que ya se usan para dar monitorias
     */
    @PodamExclude
    @OneToMany (mappedBy = "salon")
    private List<HorarioEntity> horariosMonitoria;

    /**
     * Atributo que hace referencia a los horarios libres que tiene el salon para que se den monitorias
     */
   // @PodamExclude
    //@OneToMany (mappedBy = "salon1")
    //private List<HorarioEntity> horariosAtencion;
    
    /**
     * Atributo que hace referencia al identificador del salon
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
        /**
     * Metodo para recibir el id del salon
     * @return el id del salon 
     */
    public Long getId() 
    {
        return id;
    }
     /**
      * metodo para editar el id del salon
      * @param id nuevo id del salon
      */
    public void setId(Long id) 
    {
        this.id = id;
    }
      /**
      * metodo para editar la disponibiidad del salon
     * @param pDisponibilidad nueva disponibilidad
      */   
    public void setDisponibilidad(Boolean pDisponibilidad)
    {
        this.disponibilidad = pDisponibilidad;
    }
         /**
     * Metodo para recibir la disponibilidad del salon
     * @return la disponibilidad  del salon 
     */   
    public boolean isDisponibilidad ()
    {
        return disponibilidad;
    }
    
      /**
      * metodo para editar la localizacion del salon
     * @param newLoc
      */   
    public void setLocalizacion (String newLoc)
    {
        this.localizacion = newLoc;
    }
         /**
     * Metodo para recibir la localizacion del salon
     * @return lalocalizacion del salon 
     */   
    public String getLocalizacion ()
    {
        return localizacion;
    }
       /**
      * metodo para editar la sede del salon
     * @param pSede
      */   
   
    public void setSede (SedeEntity pSede)
    {
        this.sede = pSede;
    }
         /**
     * Metodo para recibir la sede del salon
     * @return la sede del salon 
     */   
    public SedeEntity getSede ()
    {
        return sede;
    }
      /**
      * metodo para editar la lista de horarios de monitoria del salon
     * @param pHorarios
      */   
  
    public void setHorariosMonitoria (List<HorarioEntity> pHorarios)
    {
       this.horariosMonitoria = pHorarios;
   }
        /**
     * Metodo para recibir la lista de horarios de monitoria del salon
     * @return la lista de horarios de monitoria del salon 
     */    
   public List<HorarioEntity> getHorariosMonitoria ()
   {
       return horariosMonitoria; 
   }    
 
   
}

