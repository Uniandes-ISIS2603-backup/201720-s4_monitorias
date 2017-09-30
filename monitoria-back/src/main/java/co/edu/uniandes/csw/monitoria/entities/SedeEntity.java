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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author s.guzman
 */
@Entity
public class SedeEntity implements Serializable 
{
    /**
     * Atributo que hace referencia a la direccion de la sede
     */
    private String direccion;
    
    /**
     * Atributo que hace referencia al identificador unico de la sede
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    /**
     * Atributo que hace referencia al nombre de la sede
     */
    private String name;

    /**
     * Lista de salones que tiene la sede
     */
    @PodamExclude
    @OneToMany (mappedBy = "sede", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
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
    /**
     * Metodo para recibr el nombre de la sede
     * @return el nombre de la sede
     */
    public String getName()
    {
       return this.name;
    }
    /**
     * Metodo para editar el nombre de la sede
     * @param name nuevo nombre para la sede
     */
    public void setName(String name)
    {
        this.name = name;
    }
    /**
     * Metodo para recibir el id de la sede
     * @return el id de la sede
     */
     public Long getId() 
    {
        return id;
    }
     /**
      * metodo para editar el id de la sede
      * @param id 
      */
    public void setId(Long id) 
    {
        this.id = id;
    }
    /**
     * Metodo para comparar si dos sedes sn iguales
     * @param pSede La sede a comparar
     * @return 
     */
    public boolean equals(SedeEntity pSede) 
    {
        if (this.getId() != null && pSede.getId() != null) 
        {
            return this.getId().equals(pSede.getId());
        }
        return super.equals(pSede);
    }
    
    @Override
     public int hashCode() 
     {
        if (this.getId() != null) 
        {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
    

}
