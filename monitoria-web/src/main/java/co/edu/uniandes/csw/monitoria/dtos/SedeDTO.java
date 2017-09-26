/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.SedeEntity;

/**
 *
 * @author s.guzman
 */
public class SedeDTO 
{
    /**
     * Identificador de la biblioteca
     */    
    private Long id;
    /**
     * nombre de la sede
     */    
    private String name;
    /**
     * direccion de la sede
     */    
    private String direccion;
     /**
     * Constructor por defecto
     */
   public SedeDTO ()
   {
       
   }
     /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param sedeE: Es la entidad que se va a convertir a DTO
     */   
   public SedeDTO (SedeEntity sedeE)
   {

           this.id = sedeE.getId();
           this.name = sedeE.getName();
           this.direccion =sedeE.getDireccion();
       
   }
    /**
    * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */   
   public SedeEntity toEntity ()
   {
       SedeEntity sedeE = new SedeEntity();
       sedeE.setId(this.id);
       sedeE.setName(this.name);
       sedeE.setDireccion(this.direccion);
       return sedeE;
   }
   /**
    * 
    * @return el id de la sede
    */
   public Long getId()
   {
       return id;
   }
   /**
    * cambia el id de la sede
    * @param pId nuevo id para la sede
    */
   public void setId (Long pId)
   {
       this.id=pId;
   }
   /**
    * 
    * @return nombre de la sede
    */
   public String getName ()
   {
       return name;
   }
   /**
    * cambia el nombre de la sede
    * @param pName nuevo nombre para la sede
    */
   public void setName (String pName)
   {
       this.name=pName;
   }
   /**
    * 
    * @return la direccion de la sede
    */
   public String getDireccion ()
   {
       return direccion;
   }
   /**
    * Cambia la direccion de la sede
    * @param pDireccion nueva direccion de la sede
    */
   public void setDireccion(String pDireccion)
   {
       this.direccion = pDireccion;
   }
    
}
