/*
 * Clase que modela la entidad correspondiente al recurso Valoracion.
 */
package co.edu.uniandes.csw.monitoria.entities;

/**
 *
 * @author l.mejia
 */

import java.io.Serializable;
import javax.persistence.Entity;


@Entity
public class PagoEntity extends BaseEntity implements Serializable{
    private Integer valor;
    private boolean estado;
    
    public Integer getValor()
    {
        return valor;
    }
    public boolean getEstado()
    {
        return estado;
    }
    
    public void setValor(Integer valor)
    {
        this.valor=valor;
    }
    public void setEstado(Boolean estado)
    {
        this.estado=estado;
    }
    
}
