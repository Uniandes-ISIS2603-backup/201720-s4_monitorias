package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;



public class HorarioDetailDTO extends HorarioDTO{

public HorarioDetailDTO()
{
    super();
}
@Override
public HorarioEntity toEntity()
{
    HorarioEntity horario = super.toEntity();
    return horario;
}

public HorarioDetailDTO(){}

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     
    public EstudianteEntity toEntity() {
         EstudianteEntity estudiante = super.toEntity();
        estudiante.setPenalizacion(this.penalizacion);
        estudiante.setUltimaMonitoria(this.ultimaMonitoria);
        return estudiante;
    }
     */
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public HorarioDetailDTO( HorarioEntity entity) {
        super(entity);
      
        entity.setMonitor(entity.getMonitor());
        
    }
     

}
