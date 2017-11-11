/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.persistence;

/**
 *
 * @author s.guzman
 */

import co.edu.uniandes.csw.monitoria.entities.SalonEntity;
import co.edu.uniandes.csw.monitoria.entities.SedeEntity;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(Arquillian.class)
public class SalonPersistenceTest 
{
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Salon, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SalonEntity.class.getPackage())
                .addPackage(SalonPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase SalonPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private SalonPersistence salonPersistence;
    
    
    @Inject
    private SedePersistence sedePersistence;

     /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    /**
     * Configuración inicial de la prueba.
     *
     *
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from SalonEntity").executeUpdate();
    }

    /**
     *
     */
    private List<SalonEntity> data = new ArrayList<SalonEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            SalonEntity entity = factory.manufacturePojo(SalonEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Salon.
     *
     *
     */
    @Test
    public void createSalonTest() {
        PodamFactory factory = new PodamFactoryImpl();
        SalonEntity newEntity = factory.manufacturePojo(SalonEntity.class);
        SalonEntity result = salonPersistence.create(newEntity);

        Assert.assertNotNull(result);

        SalonEntity entity = em.find(SalonEntity.class, result.getId());

     
    }

    /**
     * Prueba para consultar la lista de Salon.
     *
     *
     */
    @Test
    public void getSalonesTest() {
        List<SalonEntity> list = salonPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (SalonEntity ent : list) {
            boolean found = false;
            for (SalonEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Salon.
     *
     *
     */
    @Test
    public void getSalonTest() {
       // SalonEntity entity = data.get(0);
        //SalonEntity newEntity = salonPersistence.find(entity.getId());
        //Assert.assertNotNull(newEntity);
        
         PodamFactory factory = new PodamFactoryImpl();
         SedeEntity sede = factory.manufacturePojo(SedeEntity.class);
        //SedeEntity sede = factory.manufacturePojo(SedeEntity.class);
        
        
        SalonEntity entity = data.get(0);
        sedePersistence.create(sede);
        
        entity.setSede(sede);
        salonPersistence.update(entity);
        
        SalonEntity newEntity = salonPersistence.getSalon(entity.getSede().getId(),entity.getId());
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getLocalizacion(), newEntity.getLocalizacion());
        Assert.assertEquals(entity.getId(),newEntity.getId());
    
      
    }

    /**
     * Prueba para eliminar un Salon.
     *
     *
     */
    @Test
    public void deleteSalonTest() {
        SalonEntity entity = data.get(0);
        salonPersistence.delete(entity.getId());
        SalonEntity deleted = em.find(SalonEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Salon.
     *
     *
     */
    @Test
    public void updateSalonTest() {
        SalonEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        SalonEntity newEntity = factory.manufacturePojo(SalonEntity.class);

        newEntity.setId(entity.getId());

        salonPersistence.update(newEntity);

        SalonEntity resp = em.find(SalonEntity.class, entity.getId());

      
    }
    
    
}

