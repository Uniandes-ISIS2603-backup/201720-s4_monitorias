/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.persistence;

import co.edu.uniandes.csw.monitoria.entities.ActividadEntity;
import co.edu.uniandes.csw.monitoria.entities.EstudianteEntity;
import co.edu.uniandes.csw.monitoria.entities.MonitoriaEntity;
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


@RunWith(Arquillian.class)
public class ActividadPersistenceTest {
      
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EstudianteEntity.class.getPackage())
                .addPackage(EstudiantePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    /**
     * Inyección de la dependencia a la clase XYZPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private ActividadPersistence persistence;
    
    @Inject
    private MonitoriaPersistence persistenceMonitoria;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

     /**
     *
     */
    private List<ActividadEntity> data = new ArrayList<ActividadEntity>();
    public ActividadPersistenceTest() {
    }
     @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
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
    
    @After
    public void tearDown() {
    }

   private void clearData() {
        em.createQuery("delete from ActividadEntity").executeUpdate();
    }


 private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ActividadEntity entity = factory.manufacturePojo(ActividadEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Test of create method, of class ActividadPersistence.
     */
    @Test
    public void testCreate() {
          PodamFactory factory = new PodamFactoryImpl();
        ActividadEntity newEntity = factory.manufacturePojo(ActividadEntity.class);
        ActividadEntity result = persistence.create(newEntity);
        System.out.println(""+result.getTareaAsignada());
        
        Assert.assertNotNull(result);
        ActividadEntity entity = em.find(ActividadEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
    }

    /**
     * Test of update method, of class ActividadPersistence.
     */
    @Test
    public void updateTest()  {
        PodamFactory factory = new PodamFactoryImpl();
        MonitoriaEntity monitoria = factory.manufacturePojo(MonitoriaEntity.class);
        ActividadEntity entity = data.get(0);
        entity.setMonitoria(monitoria);
        ActividadEntity newEntity = factory.manufacturePojo(ActividadEntity.class);
        newEntity.setMonitoria(monitoria);
        
        newEntity.setId(entity.getId());
        persistence.update(newEntity);
        Long id = newEntity.getId();
        
        ActividadEntity resp = em.find(ActividadEntity.class, id);
        Assert.assertEquals(newEntity.getTareaAsignada(), resp.getTareaAsignada());
    }

    /**
     * Test of delete method, of class ActividadPersistence.
     */
    @Test
    public void testDelete() {
        ActividadEntity entity = data.get(0);
        persistence.delete(entity.getId());
        ActividadEntity deleted = em.find(ActividadEntity.class,entity.getId());
        Assert.assertNull(deleted);
    }

    
    @Test
    public void getByTareaAsignadaTest(){
        PodamFactory factory = new PodamFactoryImpl();
        MonitoriaEntity monitoria = factory.manufacturePojo(MonitoriaEntity.class);
        
        ActividadEntity entity= data.get(0);
        monitoria.setActividades(data);
        persistenceMonitoria.create(monitoria);
        
        entity.setMonitoria(monitoria);
        persistence.update(entity);
        
        ActividadEntity newEntity = persistence.findByTareaAsginada(entity.getMonitoria().getId(),entity.getTareaAsignada());
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getTareaAsignada(), newEntity.getTareaAsignada());
    }
    
    @Test
    public void getOneTest(){
       PodamFactory factory = new PodamFactoryImpl();
        MonitoriaEntity monitoria = factory.manufacturePojo(MonitoriaEntity.class);
        
        
        ActividadEntity entity = data.get(0);
        monitoria.setActividades(data);
        persistenceMonitoria.create(monitoria);
        
        entity.setMonitoria(monitoria);
        persistence.update(entity);
        
        ActividadEntity newEntity = persistence.find(entity.getMonitoria().getId(),entity.getId());
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getTareaAsignada(), newEntity.getTareaAsignada());
        Assert.assertEquals(entity.getId(),newEntity.getId());
    }
}
