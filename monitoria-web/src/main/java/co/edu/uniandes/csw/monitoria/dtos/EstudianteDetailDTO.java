package co.edu.uniandes.csw.monitoria.dtos;
/*
imports
*/
import co.edu.uniandes.csw.monitoria.entities.EstudianteEntity;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cc.cardenas
 */
public class EstudianteDetailDTO extends EstudianteDTO{

    /*
    relacion monitorias
    */
private List<MonitoriaDTO> monitorias;
  
    /*
constructor vacio
*/
  public EstudianteDetailDTO(){
       //Constructor por defecto
    }
  /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public EstudianteDetailDTO( EstudianteEntity entity) {
        super(entity);
        if(entity.getMonitorias()==null)
        {
            monitorias=null;
        }
        else{
       entity.getMonitorias().forEach(x -> 
            this.monitorias.add(new MonitoriaDTO(x)));
                }
    }
  /*
  get monitorias
  @return monitorias
  */
    public List<MonitoriaDTO> getMonitorias()
    {
        return monitorias;
    }
    /*
   *@param recibe por parametro  la lista de monitorias
    */
    public void setMonitorias(List<MonitoriaDTO> monitorias)
    {
        this.monitorias=monitorias;
    }
     
    /*
    metodo to entity
    */
@Override
    public EstudianteEntity toEntity(){
        EstudianteEntity entity=super.toEntity();
   
       this.monitorias.forEach(x -> 
            entity.getMonitorias().add(x.toEntity()));
     
      return entity;
    }
}
