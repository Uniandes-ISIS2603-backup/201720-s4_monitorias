/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author s.guzman
 */
import co.edu.uniandes.csw.monitoria.entities.SedeEntity;
import co.edu.uniandes.csw.monitoria.persistence.SedePersistence;

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
public class SedePersistenceTest 
{
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Sede, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SedeEntity.class.getPackage())
                .addPackage(SedePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase SedePersistence cuyos métodos
     * se van a probar.
     */
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
        em.createQuery("delete from SedeEntity").executeUpdate();
    }

    /**
     *
     */
    private List<SedeEntity> data = new ArrayList<SedeEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            SedeEntity entity = factory.manufacturePojo(SedeEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Sede.
     *
     *
     */
    @Test
    public void createSedeTest() {
        PodamFactory factory = new PodamFactoryImpl();
        SedeEntity newEntity = factory.manufacturePojo(SedeEntity.class);
        SedeEntity result = sedePersistence.create(newEntity);

        Assert.assertNotNull(result);

        SedeEntity entity = em.find(SedeEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
     
    }

    /**
     * Prueba para consultar la lista de Sede.
     *
     *
     */
    @Test
    public void getSedeesTest() {
        List<SedeEntity> list = sedePersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (SedeEntity ent : list) {
            boolean found = false;
            for (SedeEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Sede.
     *
     *
     */
    @Test
    public void getSedeTest() {
        SedeEntity entity = data.get(0);
        SedeEntity newEntity = sedePersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
      
    }

    /**
     * Prueba para eliminar un Sede.
     *
     *
     */
    @Test
    public void deleteSedeTest() {
        SedeEntity entity = data.get(0);
        sedePersistence.delete(entity.getId());
        SedeEntity deleted = em.find(SedeEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Sede.
     *
     *
     */
    @Test
    public void updateSedeTest() {
        SedeEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        SedeEntity newEntity = factory.manufacturePojo(SedeEntity.class);

        newEntity.setId(entity.getId());

        sedePersistence.update(newEntity);

        SedeEntity resp = em.find(SedeEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
      
    }
    
    
}

