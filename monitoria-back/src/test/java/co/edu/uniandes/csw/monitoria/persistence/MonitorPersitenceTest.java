/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.persistence;

import co.edu.uniandes.csw.monitoria.entities.MonitorEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author mf.mena
 */
@RunWith(Arquillian.class)
public class MonitorPersitenceTest {
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Employee, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MonitorEntity.class.getPackage())
                .addPackage(MonitorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase MonitorPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private MonitorPersistence monitorPersistence;

    /**
     * Contexto de Persistencia que se va autilizar para acceder a la Base de
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
        em.createQuery("delete from MonitorEntity").executeUpdate();
    }

    /**
     *
     */
    private List<MonitorEntity> data = new ArrayList<MonitorEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            MonitorEntity entity = factory.manufacturePojo(MonitorEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Monitor.
     *
     *
     */
    @Test
    public void createMonitorTest() {
        PodamFactory factory = new PodamFactoryImpl();
        MonitorEntity newEntity = factory.manufacturePojo(MonitorEntity.class);
        MonitorEntity result = monitorPersistence.create(newEntity);

        Assert.assertNotNull(result);

        MonitorEntity entity = em.find(MonitorEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
        Assert.assertEquals(newEntity.getCodigo(), entity.getCodigo());
        Assert.assertEquals(newEntity.getValPromedio(), entity.getValPromedio());
    }

    /**
     * Prueba para consultar la lista de Monitores.
     * 
     */
    @Test
    public void getMonitoresTest() {
        List<MonitorEntity> list = monitorPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (MonitorEntity ent : list) {
            boolean found = false;
            for (MonitorEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para encontrar un Monitor por el cogigo con el cual se registro.
     */
    @Test
    public void getMonitorCodigoTest() {
         MonitorEntity entity = data.get(0);
        MonitorEntity newEntity = monitorPersistence.findByCodigo(entity.getCodigo());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        System.out.println(entity.getName()+" : "+newEntity.getName());
        Assert.assertEquals(entity.getTipo(), newEntity.getTipo());
        Assert.assertEquals(entity.getValPromedio(), newEntity.getValPromedio());
       
        //buscar un numero que no este endata
        Long posible =Long.valueOf(1);
            for(int i =0;i<data.size();i++){
                if(data.get(i).getCodigo()==posible){
                    posible++;
                    i= (-1);
            }
        }
            newEntity = monitorPersistence.findByCodigo(posible);
        Assert.assertNull(newEntity);
    }

    

    /**
     * Prueba para consultar un Monitor.
     */
    @Test
    public void getMonitorTest() {
        MonitorEntity entity = data.get(0);
        MonitorEntity newEntity = monitorPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getTipo(), newEntity.getTipo());
        Assert.assertEquals(entity.getValPromedio(), newEntity.getValPromedio());
    }
      /**
     * Prueba para eliminar un Monitor.
     */
    @Test
    public void deleteMonitorTest() {
        MonitorEntity entity = data.get(0);
        monitorPersistence.delete(entity.getId());
        MonitorEntity deleted = em.find(MonitorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Monitor.
     */
    @Test
    public void updateMOnitorest() {
        MonitorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MonitorEntity newEntity = factory.manufacturePojo(MonitorEntity.class);

        newEntity.setId(entity.getId());

        monitorPersistence.update(newEntity);

        MonitorEntity resp = em.find(MonitorEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getTipo(), resp.getTipo());
        Assert.assertEquals(newEntity.getValPromedio(), resp.getValPromedio());
    }
}
