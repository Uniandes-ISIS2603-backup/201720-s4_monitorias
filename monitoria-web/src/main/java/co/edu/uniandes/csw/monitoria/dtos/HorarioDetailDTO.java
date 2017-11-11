package co.edu.uniandes.csw.monitoria.dtos;
import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;


public class HorarioDetailDTO extends HorarioDTO{

public HorarioDetailDTO()
{
   
}

public HorarioDetailDTO(HorarioEntity horario){
     super(horario);
}
public HorarioEntity toEntity()
{
    HorarioEntity horario = super.toEntity();
    return horario;
}

    
}
