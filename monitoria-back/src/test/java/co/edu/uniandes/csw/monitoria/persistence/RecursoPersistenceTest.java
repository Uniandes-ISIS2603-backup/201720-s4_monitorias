/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.persistence;

import co.edu.uniandes.csw.monitoria.entities.BibliotecaEntity;
import co.edu.uniandes.csw.monitoria.entities.RecursoEntity;
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
 * @author ms.osorio
 */
@RunWith(Arquillian.class)

public class RecursoPersistenceTest {
    @Inject
    private RecursoPersistence persistence;
    
    @Inject
    private BibliotecaPersistence persistenceBiblioteca;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<RecursoEntity> data = new ArrayList<RecursoEntity>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RecursoEntity.class.getPackage())
                .addPackage(RecursoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    public RecursoPersistenceTest() {
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
        }catch(Exception e){
            e.printStackTrace();
            try{
                utx.rollback();
            }catch(Exception e1){
                e1.printStackTrace();
            }
        }
    }
    
    private void clearData(){
        em.createQuery("delete from RecursoEntity").executeUpdate();
    }
    
    private void insertData(){
        PodamFactory factory = new PodamFactoryImpl();
       
        
        for(int i =0; i<3;i++){
           RecursoEntity entity = factory.manufacturePojo(RecursoEntity.class);
         // BibliotecaEntity biblioEntity = factory.manufacturePojo(BibliotecaEntity.class);
           //entity.setBiblioteca(biblioEntity);
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
        RecursoEntity newEntity = factory.manufacturePojo(RecursoEntity.class);
        RecursoEntity result = persistence.createRecurso(newEntity);
        
        Assert.assertNotNull(result);
        RecursoEntity entity = em.find(RecursoEntity.class, result.getId());
        
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getEditorial(),entity.getEditorial());
        
    }
   
    
    @Test
    public void getOneTest(){
        PodamFactory factory = new PodamFactoryImpl();
        BibliotecaEntity biblioteca = factory.manufacturePojo(BibliotecaEntity.class);
        
        
        RecursoEntity entity = data.get(0);
        biblioteca.setRecursos(data);
        persistenceBiblioteca.createBiblioteca(biblioteca);
        
        entity.setBiblioteca(biblioteca);
        persistence.updateRecurso(entity);
        
        RecursoEntity newEntity = persistence.getRecurso(entity.getBiblioteca().getId(),entity.getId());
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getId(),newEntity.getId());
    }
    
    @Test
    public void getByNameTest(){
        PodamFactory factory = new PodamFactoryImpl();
        BibliotecaEntity biblioteca = factory.manufacturePojo(BibliotecaEntity.class);
        
        RecursoEntity entity= data.get(0);
        biblioteca.setRecursos(data);
        persistenceBiblioteca.createBiblioteca(biblioteca);
        
        entity.setBiblioteca(biblioteca);
        persistence.updateRecurso(entity);
        
        RecursoEntity newEntity = persistence.findByName(entity.getBiblioteca().getId(),entity.getName());
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }
    
    @Test
    public void updateTest(){
        PodamFactory factory = new PodamFactoryImpl();
        BibliotecaEntity biblioteca = factory.manufacturePojo(BibliotecaEntity.class);
        RecursoEntity entity = data.get(0);
        entity.setBiblioteca(biblioteca);
        
        RecursoEntity newEntity = factory.manufacturePojo(RecursoEntity.class);
        newEntity.setBiblioteca(biblioteca);
        
        newEntity.setId(entity.getId());
        
        persistence.updateRecurso(newEntity);
        Long id = newEntity.getId();
        
        RecursoEntity resp = em.find(RecursoEntity.class, id);
        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    
    @Test
    public void deleteTest(){
        RecursoEntity entity = data.get(0);
        persistence.deleteRecurso(entity.getId());
        RecursoEntity deleted = em.find(RecursoEntity.class,entity.getId());
        Assert.assertNull(deleted);
    }
    
}