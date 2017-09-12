/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import co.edu.uniandes.csw.monitoria.entities.BibliotecaEntity;
import co.edu.uniandes.csw.monitoria.persistence.BibliotecaPersistence;
import co.edu.uniandes.csw.monitoria.persistence.RecursoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
 * @author ms.osorio
 */

@RunWith(Arquillian.class)
public class BibliotecaPersistenceTest {
    
    @Inject
    private BibliotecaPersistence persistence;
    
    @PersistenceContext 
    private EntityManager em;
    
    @Inject 
    UserTransaction utx;
    
    private List<BibliotecaEntity> data = new ArrayList<BibliotecaEntity>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BibliotecaEntity.class.getPackage())
                .addPackage(RecursoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    public BibliotecaPersistenceTest() {
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
        em.createQuery("delete from BibliotecaEntity").executeUpdate();
    }
    private void insertData(){
        PodamFactory factory = new PodamFactoryImpl();
        for(int i =0; i <3; i++){
            BibliotecaEntity entity = factory.manufacturePojo(BibliotecaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }

     @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        BibliotecaEntity newEntity = factory.manufacturePojo(BibliotecaEntity.class);
        BibliotecaEntity result = persistence.create(newEntity);
        
        Assert.assertNotNull(result);
        BibliotecaEntity entity = em.find(BibliotecaEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
        
    }
    
    @Test
    public void getTest(){
        List<BibliotecaEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for(BibliotecaEntity ent: list){
            boolean found = false;
            for(BibliotecaEntity entity: data){
                if(ent.getId().equals(entity.getId())){
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    @Test
    public void getOneTest(){
        BibliotecaEntity entity = data.get(0);
        BibliotecaEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    @Test
    public void getByNameTest(){
        BibliotecaEntity entity= data.get(0);
        BibliotecaEntity newEntity = persistence.findByName(entity.getName());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    @Test
    public void updateTest(){
        BibliotecaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        BibliotecaEntity newEntity = factory.manufacturePojo(BibliotecaEntity.class);
        newEntity.setId(entity.getId());
        persistence.update(newEntity);
        BibliotecaEntity resp = em.find(BibliotecaEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    
    @Test
    public void deleteTest(){
        BibliotecaEntity entity = data.get(0);
        persistence.delete(entity.getId());
        BibliotecaEntity deleted = em.find(BibliotecaEntity.class,entity.getId());
        Assert.assertNull(deleted);
    }
    
}
