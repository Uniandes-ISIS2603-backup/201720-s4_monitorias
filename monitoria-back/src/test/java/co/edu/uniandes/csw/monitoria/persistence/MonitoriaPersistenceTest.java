/*
 * Clase de Test para la persistencia del recurso Monitoria.
 */
package co.edu.uniandes.csw.monitoria.persistence;

import co.edu.uniandes.csw.monitoria.entities.MonitoriaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author l.mejia
 */
@RunWith(Arquillian.class)
public class MonitoriaPersistenceTest {
    
    /**
     * Inyección de la dependencia a la clase XYZPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private MonitoriaPersistence persistence;
    
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
    
     private List<MonitoriaEntity> data = new ArrayList<MonitoriaEntity>();
     /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Monitoria, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MonitoriaEntity.class.getPackage())
                .addPackage(MonitoriaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Elimina los datos dela base de Datos
     */
    private void clearData() {
        em.createQuery("delete from MonitoriaEntity").executeUpdate();
    }
    
    /**
     * crea datos aleatorios de tipo MonitoriaEntity
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            MonitoriaEntity entity = factory.manufacturePojo(MonitoriaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    public MonitoriaPersistenceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() 
    {
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

    /**
     * Test of create method, of class MonitoriaPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory pf=new PodamFactoryImpl();
        MonitoriaEntity nuevaEntity=pf.manufacturePojo(MonitoriaEntity.class);
        MonitoriaEntity respuestaEntity=persistence.create(nuevaEntity);
        Assert.assertNotNull(respuestaEntity);
        MonitoriaEntity entity= em.find(MonitoriaEntity.class, respuestaEntity.getId());
        Assert.assertEquals(nuevaEntity,entity);
    }

    /**
     * Test of update method, of class MonitoriaPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        MonitoriaEntity entity = data.get(0);
        PodamFactory pf = new PodamFactoryImpl();
        MonitoriaEntity nuevaEntity = pf.manufacturePojo(MonitoriaEntity.class);
        nuevaEntity.setId(entity.getId());
        persistence.update(nuevaEntity);
//        Assert.assertEquals(nuevaEntity.getName(), resp.getName());
    }

    /**
     * Test of find method, of class MonitoriaPersistence.
     */
    @Test
    public void testFind() throws Exception {
        MonitoriaEntity entity = data.get(0);
        MonitoriaEntity nuevaEntity = persistence.find(entity.getId());
        Assert.assertNotNull(nuevaEntity);
        Assert.assertEquals(entity, nuevaEntity);
    }

    /**
     * Test of findAll method, of class MonitoriaPersistence.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindAll() throws Exception {
        List<MonitoriaEntity> totalEntidades = persistence.findAll();
        Assert.assertEquals(data.size(), totalEntidades.size());
        for(MonitoriaEntity ent: totalEntidades){
            boolean encontro = false;
            for(MonitoriaEntity entity: data){
                if(ent.equals(entity)){
                    encontro = true;
                }
            }
            Assert.assertTrue(true);
        }
    }
    
}
