/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author stive
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(co.edu.uniandes.csw.monitoria.mappers.BusinessLogicExceptionMapper.class);
        resources.add(co.edu.uniandes.csw.monitoria.mappers.UnsupportedOperationExceptionMapper.class);
        resources.add(co.edu.uniandes.csw.monitoria.mappers.WebApplicationExceptionMapper.class);
        resources.add(co.edu.uniandes.csw.monitoria.resources.ActividadResource.class);
        resources.add(co.edu.uniandes.csw.monitoria.resources.BibliotecaResource.class);
        resources.add(co.edu.uniandes.csw.monitoria.resources.IdiomaResource.class);
        resources.add(co.edu.uniandes.csw.monitoria.resources.MonitorResource.class);
        resources.add(co.edu.uniandes.csw.monitoria.resources.MonitoriaResource.class);
        resources.add(co.edu.uniandes.csw.monitoria.resources.SalonResource.class);
        resources.add(co.edu.uniandes.csw.monitoria.resources.SedeResource.class);
        resources.add(co.edu.uniandes.csw.monitoria.resources.ValoracionResource.class);
        resources.add(org.eclipse.persistence.jpa.rs.exceptions.JPARSExceptionMapper.class);
        resources.add(org.eclipse.persistence.jpa.rs.resources.EntityResource.class);
        resources.add(org.eclipse.persistence.jpa.rs.resources.MetadataResource.class);
        resources.add(org.eclipse.persistence.jpa.rs.resources.PersistenceResource.class);
        resources.add(org.eclipse.persistence.jpa.rs.resources.PersistenceUnitResource.class);
        resources.add(org.eclipse.persistence.jpa.rs.resources.QueryResource.class);
        resources.add(org.eclipse.persistence.jpa.rs.resources.SingleResultQueryResource.class);
        resources.add(org.eclipse.persistence.jpa.rs.resources.unversioned.EntityResource.class);
        resources.add(org.eclipse.persistence.jpa.rs.resources.unversioned.PersistenceResource.class);
        resources.add(org.eclipse.persistence.jpa.rs.resources.unversioned.PersistenceUnitResource.class);
        resources.add(org.eclipse.persistence.jpa.rs.resources.unversioned.QueryResource.class);
        resources.add(org.eclipse.persistence.jpa.rs.resources.unversioned.SingleResultQueryResource.class);
    }
    
}
