package co.edu.uniandes.csw.monitoria.dtos;

import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;

public class HorarioDetailDTO extends HorarioDTO {

    public HorarioDetailDTO() {
        super();
    }

    @Override
    public HorarioEntity toEntity() {
        HorarioEntity horario = super.toEntity();
        return horario;
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public HorarioDetailDTO(HorarioEntity entity) {
        super(entity);
        entity.setSalon(entity.getSalon());       

    }

}
