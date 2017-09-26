/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.persistence;

import co.edu.uniandes.csw.monitoria.entities.BibliotecaEntity;
import co.edu.uniandes.csw.monitoria.entities.EstudianteEntity;
import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;
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
 * @author Cristian
 */
@RunWith(Arquillian.class)
public class EstudiantePersistenceTest {
     @Inject
    private EstudiantePersistence persistence;
    
    @PersistenceContext 
    private EntityManager em;
    
    @Inject 
    UserTransaction utx;
    
    private List<EstudianteEntity> data = new ArrayList<EstudianteEntity>();
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EstudianteEntity.class.getPackage())
                .addPackage(EstudiantePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    public EstudiantePersistenceTest() {
    }
@BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try{
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            try{
                utx.rollback();
            
            }catch(Exception e1){
            e1.printStackTrace();
            }
        }
    }
    private void clearData(){
        em.createQuery("delete from EstudianteEntity").executeUpdate();
    }
    private void insertData(){
        PodamFactory factory = new PodamFactoryImpl();
        for(int i =0; i <3; i++){
            EstudianteEntity entity = factory.manufacturePojo(EstudianteEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of create method, of class EstudiantePersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
       EstudianteEntity newEntity = factory.manufacturePojo(EstudianteEntity.class);
        EstudianteEntity result = persistence.create(newEntity);
        
        Assert.assertNotNull(result);
        EstudianteEntity entity = em.find(EstudianteEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(),entity.getName());
    }

    /**
     * Test of update method, of class EstudiantePersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        EstudianteEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
       EstudianteEntity newEntity = factory.manufacturePojo(EstudianteEntity.class);
        newEntity.setId(entity.getId());
        persistence.update(newEntity);
        EstudianteEntity resp = em.find(EstudianteEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Test of delete method, of class EstudiantePersistence.
     */
    @Test
    public void testDelete() throws Exception {
               EstudianteEntity entity = data.get(0);
        persistence.delete(entity.getId());
        EstudianteEntity deleted = em.find(EstudianteEntity.class,entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Test of findById method, of class EstudiantePersistence.
     */
    @Test
    public void testFindById() throws Exception {
        EstudianteEntity entity= data.get(1);
        EstudianteEntity entityA= persistence.findById(entity.getId());
        assertEquals(entityA.getName(), entity.getName());
    }

    /**
     * Test of findByCodigo method, of class EstudiantePersistence.
     */
    @Test
    public void testFindByCodigo() throws Exception {
        EstudianteEntity entity= data.get(0);
        EstudianteEntity entidad=persistence.findByCodigo(entity.getCodigo());
        assertEquals(entidad.getName(), entidad.getName());
    }

    /**
     * Test of findAll method, of class EstudiantePersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<EstudianteEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for(EstudianteEntity ent: list){
            boolean found = false;
            for(EstudianteEntity entity: data){
                if(ent.getId().equals(entity.getId())){
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Test of findByName method, of class EstudiantePersistence.
     */
    @Test
    public void testFindByName() throws Exception {
        EstudianteEntity entidad= data.get(0);
        EstudianteEntity entity= persistence.findByName(entidad.getName());
        
        assertEquals(entity.getName(),entidad.getName());
    }
    
}
