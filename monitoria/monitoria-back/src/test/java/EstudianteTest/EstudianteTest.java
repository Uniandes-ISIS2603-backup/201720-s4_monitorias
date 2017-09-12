/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

*/
import co.edu.uniandes.csw.monitoria.entities.EstudianteEntity;
import co.edu.uniandes.csw.monitoria.persistence.EstudiantePersistence;
import co.edu.uniandes.csw.monitoria.persistence.HorarioPersistence;
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
 * @author Cristian
 */

@RunWith(Arquillian.class)
public class EstudianteTest {
    
    
    /**
     * Representa la persistencia de estudiante
     */
    @Inject
    private EstudiantePersistence persistence;
    
    /**
     * Entity manager para comunicarse con la base de datos
     */
    @PersistenceContext 
    private EntityManager em;
    
    
    @Inject 
    UserTransaction utx;
    /**
     * Lista en donde se almacenaran los objetos EstudianteEntity que se crearán para las pruebas.
     */
    private List<EstudianteEntity> data = new ArrayList<EstudianteEntity>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EstudianteEntity.class.getPackage())
                .addPackage(EstudiantePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    public EstudianteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     * Método que se ejecuta antes que cuarquier prueba.
     * Aquí se crean los objetos de prueba y se insertan en la lista llamada data
     */
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
    /**
     * Método auxiliar para limpiar los datos que hayan en la base de datos.
     */
    private void clearData(){
        em.createQuery("delete from EstudianteEntiyy").executeUpdate();
    }
    /**
     * Método auxiliar que se usa para insertar datos en la base de datos
     */
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
     * Método encargado de las pruebas del metodo create de la clase EstudiantePersistence
     */
     @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        EstudianteEntity newEntity = factory.manufacturePojo(EstudianteEntity.class);
        EstudianteEntity result = persistence.create(newEntity);
        
        Assert.assertNotNull(result);
        EstudianteEntity entity = em.find(EstudianteEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
        
    }
    
    /**
     * Método encargado de las pruebas del metodo findAll de la clase EstudiantePersistence
     */
    @Test
    public void getTest(){
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
     * Método encargado de las pruebas del find create de la clase EstudiantePersistence
     */
    @Test
    public void getOneTest(){
        EstudianteEntity entity = data.get(0);
        EstudianteEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    /**
     * Método encargado de las pruebas del metodo findByName de la clase EstudiantePersistence
     */
    
    @Test
    public void getByNameTest(){
        EstudianteEntity entity= data.get(0);
        EstudianteEntity newEntity = persistence.findByName(entity.getName());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    /**
     * Método encargado de las pruebas del metodo update de la clase EstudiantePersistence
     */
    @Test
    public void updateTest(){
        EstudianteEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EstudianteEntity newEntity = factory.manufacturePojo(EstudianteEntity.class);
        newEntity.setId(entity.getId());
        persistence.update(newEntity);
        EstudianteEntity resp = em.find(EstudianteEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    
    /**
     * Método encargado de las pruebas del metodo delete de la clase EstudiantePersistence
     */
    @Test
    public void deleteTest(){
        EstudianteEntity entity = data.get(0);
        persistence.delete(entity.getId());
        EstudianteEntity deleted = em.find(EstudianteEntity.class,entity.getId());
        Assert.assertNull(deleted);
    }
    
}
