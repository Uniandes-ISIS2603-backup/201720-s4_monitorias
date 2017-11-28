/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.ejb;

import co.edu.uniandes.csw.monitoria.entities.EstudianteEntity;
import co.edu.uniandes.csw.monitoria.entities.MonitoriaEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.EstudiantePersistence;
import co.edu.uniandes.csw.monitoria.persistence.MonitoriaPersistence;
import javax.inject.Inject;

/**
 *
 * @author l.mejia
 */
public class MonitoriaEstudianteLogic {
    
    @Inject
    MonitoriaPersistence persistenceMonitoria;
    
    @Inject
    EstudiantePersistence persistenceEstudiante;
    
    @Inject
    EstudianteLogic logicEstudiante;
    
    @Inject
    MonitoriaLogic logicMonitoria;
    
    
    public void agregarRelacion(EstudianteEntity estudiante, MonitoriaEntity monitoria) throws BusinessLogicException
    {
        EstudianteEntity estudianteAgregar=logicEstudiante.findById(estudiante.getId());
        MonitoriaEntity monitoriaAgregar=logicMonitoria.findById(monitoria.getId());
        if(estudianteAgregar!=null&&monitoriaAgregar!=null)
        {
            monitoriaAgregar.agregarEstudiante(estudianteAgregar);
            estudianteAgregar.agregarMonitoria(monitoriaAgregar);
            persistenceMonitoria.update(monitoriaAgregar);
            persistenceEstudiante.update(estudianteAgregar);
        }
    }
}
