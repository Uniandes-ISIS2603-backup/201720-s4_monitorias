/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author s.guzman
 */
import uk.co.jemos.podam.common.PodamStrategyValue;
@Entity
public class SedeEntity extends BaseEntity implements Serializable 
{
    /**
     * Atributo que hace referencia a la direccion de la sede
     */
    private String direccion;
        /**
     * Atributo que hace referencia al nombre de la sede
     */
    private String nombre;
    
    @PodamExclude
    @OneToMany (mappedBy = "sede", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalonEntity> salones;
    
    /**
     * Metodo para cambiar la direccion de la sede
     * @param newDireccion la nueva direccion de la sede
     */
    public void setDireccion(String newDireccion)
    {
        this.direccion = newDireccion;
    }
    /**
     * Metodo para obtener la direccion de la sede
     * @return la direccion de la sede
     */
    public String getDireccion ()
    {
        return direccion;
    }
            /**
     * Metodo para cambiar la direccion de la sede
     * @param pNombre nuevo nombre de la sede
     */
    public void setNombre(String pNombre)
    {
        this.nombre = pNombre;
    }
    /**
     * Metodo para obtener el nombre de la sede
     * @return el nombre de la sede
     */
    public String getNombre ()
    {
        return nombre;
    }
    /**
     * Metodo para recibir los salones de una sede
     * @param pSalones lista de salones de una sede
     */
    public void setSalones (List<SalonEntity> pSalones)
    {
        this.salones=pSalones;
    }
    /**
     * Metodo para editar toda la lista de salones de una sede
     * @return nueva lista de salones
     */
    public List<SalonEntity> getSalones()
    {
        return salones;
    }
    

}
