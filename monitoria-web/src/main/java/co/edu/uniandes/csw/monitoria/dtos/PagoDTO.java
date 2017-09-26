/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;
import co.edu.uniandes.csw.monitoria.entities.PagoEntity;

/**
 *
 * @author mf.mena
 */
public class PagoDTO {
    private Integer valor;
    private boolean estado;
    private Long id;
    private MonitorDTO monitor;
     /**
     * Constructor por defecto
     */
    public PagoDTO() {
    }

    /**
     * Conviertir Entity a DTO
     * (Crea un nuevo DTO con los valores que recibe en la entidad que viene de argumento.
     * @para pago: Es la entidad que se va a convertir a DTO 
     */
    public PagoDTO(PagoEntity pago) {
        this.estado=pago.getEstado();
        this.valor=pago.getValor();
        this.id=pago.getId();
        MonitorDTO dtoMonitor=new MonitorDTO(pago.getMonitor());
        this.monitor =dtoMonitor;     
    }
  public boolean getEstado(){
      return estado;
  }
    public MonitorDTO getDTOMonitor(){
      return monitor;
  }
      public Integer getValor(){
      return valor;
  }
  public void setEstado(boolean estado){
      this.estado=estado;
  }
    public Long getId(){
      return id;
  }
  public void setId(Long id){
      this.id=id;
  }
  
    public void setDTOMonitor(MonitorDTO monitor){
      this.monitor=monitor;
  }
      public void setValor(Integer valor){
      this.valor=valor;
  }          
    
    
    
    public PagoEntity toEntity() {
        PagoEntity entity = new PagoEntity ();
        entity.setEstado(this.estado);
        entity.setValor(this.valor);
        if(monitor!=null){
        entity.setMonitor(monitor.toEntity());
          }
        return entity;
    }
    
}
