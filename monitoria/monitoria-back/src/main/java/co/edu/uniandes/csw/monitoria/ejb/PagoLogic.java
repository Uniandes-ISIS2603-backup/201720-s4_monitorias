/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;
import co.edu.uniandes.csw.monitoria.entities.PagoEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.PagoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
/**
 *
 * @author Mafe
 */
@Stateless
public class PagoLogic {
     private static final Logger LOGGER = Logger.getLogger(PagoLogic.class.getName());

    @Inject
    private PagoPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

     /**
     * Crea un nuevo pago de un monitor 
     * @param PagoEntity: la entity que se va a crear y persistir
     * @return entity
     */
    public PagoEntity createPago(PagoEntity entity) {
        LOGGER.info("Inicia proceso de creación de un pago");
        // Invoca la persistencia para crear la editorial
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de pago");
        return entity;
    }
    /**
     * 
     * Obtener todas los los pagos existentes en la base de datos.
     *
     * @return una lista de editoriales.
     */
    public List<PagoEntity> getPagos() {
        LOGGER.info("Inicia proceso de consultar todos los pagos");
        List<PagoEntity> editorials = persistence.findAll();
        LOGGER.info("Termina proceso de consulta de todos los pagos");
        return editorials;
    }
     /**
     * Obtener un pago por medio de su id.
     * @param id: id de la editorial para ser buscada.
     * @return la editorial solicitada por medio de su id.
     */
    public PagoEntity getPago(Long id) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de consultar editorial con id={0}", id);
        PagoEntity pago = persistence.find(id);
        if (pago == null) {
            LOGGER.log(Level.SEVERE, "El pago con el id {0} no existe", id);
            throw new BusinessLogicException("No existe un objeto Pago con el CODIGO solicitado");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar pago con id={0}", id);
        return pago;
    }
    
     /**
     *
     * Actualizar el estado de un pago.
     * @param id: id del pago para buscarlo en la base de datos.
     * @param entity: Pago con los cambios para ser actualizada (estado)
     * @return el pago con los cambios actualizados en la base de datos.
     */
    public PagoEntity updatePago(Long id, PagoEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar pago con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        PagoEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar pago con id={0}", entity.getId());
        return newEntity;
    }

    /**
     * Borrar un pago del sistema si este ya fue pagado
     * @param id: id del pago a borrar
     */
    public void deleteEditorial(Long id)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar pago con id={0}", id);
        getPago(id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar pago con id={0}", id);
    }

}
