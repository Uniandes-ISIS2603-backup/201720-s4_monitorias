/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.dtos;
import co.edu.uniandes.csw.monitoria.entities.MonitoriaEntity;
import java.util.ArrayList;


/**
 *
 * @author l.mejia
 */
public class MonitoriaDTO {
    
    private Long id;
    private String nombreMonitor;
    private String nombreEstudiante;
    private String tipo;
    private String estado;
    private Long idMonitor;
    private IdiomaDTO idioma;
    private ArrayList<ActividadDTO> actividades=new ArrayList<>();
    
    public ArrayList<ActividadDTO> getActividades()
    {
        return actividades;
    }
    public void setActividades(ArrayList<ActividadDTO> actividades)
    {
        this.actividades=actividades;
    }
    public MonitoriaDTO(){
        
    }
    public IdiomaDTO getIdiomaDTO()
    {
        return this.idioma;
    }
    
    public void setIdioma(IdiomaDTO idioma)
    {
        this.idioma=idioma;
    }
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id=id;
    }
    public String getTipo()
    {
        return tipo;
    }
    
    public String getNombreMonitor(){
        return nombreMonitor;
    }
    
    public String getNombreEstudiante(){
        return nombreEstudiante;
    }
    
    public String getEstado(){
        return estado;
    }
    
    public void setTipo(String tipo)
    {
        this.tipo=tipo;
    }
    
    public void setNombreMonitor(String nombreMonitor)
    {
        this.nombreMonitor=nombreMonitor;
    }
    
    public void setNombreEstudiante(String nombreEstudiante)
    {
        this.nombreEstudiante=nombreEstudiante;
    }
    
    public void setEstado(String estado)
    {
        this.estado=estado;
    }
    public Long getIdMonitor()
    {
        return this.idMonitor;
    }
    public void setIdMonitor(Long idMonitor)
    {
        this.idMonitor=idMonitor;
   }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param monitoria: Es la entidad que se va a convertir a DTO
     */
    public MonitoriaDTO(MonitoriaEntity monitoria){
        this.id = monitoria.getId();
        this.nombreMonitor = monitoria.getNombreMonitor();
        this.nombreEstudiante=monitoria.getNombreEstudiante();
        this.tipo=monitoria.getTipo();
        this.idMonitor=monitoria.getIdMonitor();
        this.estado=monitoria.getEstado();
        this.idioma=new IdiomaDTO(monitoria.getIdioma());
        monitoria.getActividades().forEach((x) -> {
            this.actividades.add(new ActividadDTO(x));
        });
    }
    
    /**
    * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public MonitoriaEntity toEntity(){
        MonitoriaEntity entity = new MonitoriaEntity();
        entity.setId(this.id);
        entity.setNombreEstudiante(this.nombreEstudiante);
        entity.setNombreMonitor(this.nombreMonitor);
        entity.setEstado(this.estado);
        entity.setTipo(this.tipo);
        entity.setIdMonitor(this.idMonitor);
        entity.setIdioma(this.idioma.toEntity());
        for(ActividadDTO x : this.actividades) entity.getActividades().add(x.toEntity());
        
        return entity;
    }
    
}
