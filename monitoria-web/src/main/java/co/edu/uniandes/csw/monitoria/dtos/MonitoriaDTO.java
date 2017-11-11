/*
 * Clase DTO del recurso monitoria
 */
package co.edu.uniandes.csw.monitoria.dtos;
import co.edu.uniandes.csw.monitoria.entities.MonitoriaEntity;
/**
 *
 * @author l.mejia
 */
public class MonitoriaDTO {
    
    private Long id;
    private String tipo;
    private String estado;
 
    /*
    *Constructor vacio para generar el JSon
    */
    public MonitoriaDTO(){}
    
    /*
    *Retorna el id de la monitoria
    */
    public Long getId()
    {
        return id;
    }
    /*
    *Asigna el id de la monitoria
    */
    public void setId(Long id)
    {
        this.id=id;
    }
    /*
    *Retorna el tipo de la monitoria
    */
    public String getTipo()
    {
        return tipo;
    }
    /*
    *Retorna el estado de la monitoria
    */
    public String getEstado(){
        return estado;
    }
    /*
    *Retorna el tipo de la monitoria
    */
    public void setTipo(String tipo)
    {
        this.tipo=tipo;
    }   
    /*
    *Asigna un estado a la monitoria
    */
    public void setEstado(String estado)
    {
        this.estado=estado;
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param monitoria: Es la entidad que se va a convertir a DTO
     */
    public MonitoriaDTO(MonitoriaEntity monitoria){
        this.id = monitoria.getId();
        this.tipo=monitoria.getTipo();        
        this.estado=monitoria.getEstado();      
    }
    
    /**
    * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public MonitoriaEntity toEntity(){
        MonitoriaEntity entity = new MonitoriaEntity();
        entity.setId(this.id);
        entity.setEstado(this.estado);
        entity.setTipo(this.tipo);         
        return entity;
    }
    
}
