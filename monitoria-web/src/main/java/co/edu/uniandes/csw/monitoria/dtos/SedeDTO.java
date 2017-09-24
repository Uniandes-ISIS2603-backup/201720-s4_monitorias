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
    private Long id;
    
    private String name;
    
    private String direccion;
    
   public SedeDTO ()
   {
       
   }
   
   public SedeDTO (SedeEntity sedeE)
   {
       if (sedeE!= null)
       {
           this.id = sedeE.getId();
           this.name = sedeE.getName();
           this.direccion =sedeE.getDireccion();
       }
   }
   
   public SedeEntity toEntity ()
   {
       SedeEntity sedeE = new SedeEntity();
       sedeE.setId(id);
       sedeE.setName(name);
       sedeE.setDireccion(direccion);
       return sedeE;
   }
   
   public Long getId()
   {
       return id;
   }
   public void setId (Long pId)
   {
       this.id=pId;
   }
   public String getName ()
   {
       return name;
   }
   public void setName (String pName)
   {
       this.name=pName;
   }
   public String getDireccion ()
   {
       return direccion;
   }
   public void setDireccion(String pDireccion)
   {
       this.direccion = pDireccion;
   }
    
}
