/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.persistence;


import co.edu.uniandes.csw.monitoria.entities.PagoEntity;
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
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author martha
 */

@RunWith(Arquillian.class)
public class PagoPersistenceTest {
    
    /**
     * Inyección de la dependencia a la clase XYZPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private PagoPersistence persistence;
    
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
    
     private List<PagoEntity> data = new ArrayList<PagoEntity>();
     /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Valoracion, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PagoEntity.class.getPackage())
                .addPackage(PagoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Elimina los datos dela base de Datos
     */
    private void clearData() {
        em.createQuery("delete from PagoEntity").executeUpdate();
    }
    
    /**
     * crea datos aleatorios de tipo ValoracionEntity
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PagoEntity entity = factory.manufacturePojo(PagoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    public PagoPersistenceTest() {
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
     * Test of create method, of class ValoracionPersistence.
     */
    @Test
    public void testCreate() throws Exception 
    {
        PodamFactory pf=new PodamFactoryImpl();
        PagoEntity nuevaEntity=pf.manufacturePojo(PagoEntity.class);
        PagoEntity respuestaEntity=persistence.create(nuevaEntity);
        Assert.assertNotNull(respuestaEntity);
        PagoEntity entity= em.find(PagoEntity.class, respuestaEntity.getId());
        Assert.assertEquals(nuevaEntity,entity);
    }

    /**
     * Test of update method, of class ValoracionPersistence.
     */
    @Test
    public void testUpdate() throws Exception 
    {
        PagoEntity entity = data.get(0);
        PodamFactory pf = new PodamFactoryImpl();
        PagoEntity nuevaEntity = pf.manufacturePojo(PagoEntity.class);
        nuevaEntity.setId(entity.getId());
        persistence.update(nuevaEntity);
        PagoEntity resp = em.find(PagoEntity.class, entity.getId());
        Assert.assertEquals(nuevaEntity.getName(), resp.getName());
    }

    /**
     * Test of find method, of class ValoracionPersistence.
     */
    @Test
    public void testFind() throws Exception 
    {
        PagoEntity entity = data.get(0);
        PagoEntity nuevaEntity = persistence.find(entity.getId());
        Assert.assertNotNull(nuevaEntity);
        Assert.assertEquals(entity, nuevaEntity);
    }

    /**
     * Test of findAll method, of class ValoracionPersistence.
     */
    @Test
    public void testFindAll() throws Exception 
    {
        List<PagoEntity> totalEntidades = persistence.findAll();
        System.out.println(totalEntidades.size()+"**************************CANTIDAD ENTIDADES *************************");
        Assert.assertEquals(data.size(), totalEntidades.size());
        for(PagoEntity ent: totalEntidades){
            boolean encontro = false;
            for(PagoEntity entity: data){
                if(ent.equals(entity)){
                    encontro = true;
                }
            }
            Assert.assertTrue(encontro);
        }
    }
    
}