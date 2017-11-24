
package co.edu.uniandes.csw.monitoria.dtos;
import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;


public class HorarioDetailDTO extends HorarioDTO{
/*
    consttructor
    */
public HorarioDetailDTO()
{
   // constructor de la clase vacio
}
/*
constructor del detail a partir de un horario  entity.
*/
public HorarioDetailDTO(HorarioEntity horario){
     super(horario);
}
/*
convierte el detail a un entity
*/
public HorarioEntity toEntity()
{
    HorarioEntity horario = super.toEntity();
    return horario;
}

    
}

