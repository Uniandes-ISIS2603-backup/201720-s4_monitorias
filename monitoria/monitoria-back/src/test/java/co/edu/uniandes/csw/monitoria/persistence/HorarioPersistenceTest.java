/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.persistence;

import co.edu.uniandes.csw.monitoria.entities.HorarioEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Cristian
 */
public class HorarioPersistenceTest {
    
    public HorarioPersistenceTest() {
    }
    
  
      

    
    
    /**
     * Representa la persistencia de Horario
     */
    @Inject
    private HorarioPersistence persistence;
    
    /**
     * Entity manager para comunicarse con la base de datos
     */
    @PersistenceContext 
    private EntityManager em;
    
    
    @Inject 
    UserTransaction utx;
    /**
     * Lista en donde se almacenaran los objetos HorarioEntity que se crearán para las pruebas.
     */
    private List<HorarioEntity> data = new ArrayList<HorarioEntity>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HorarioEntity.class.getPackage())
                .addPackage(HorarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
   
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     * Método que se ejecuta antes que cualquier prueba.
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
        em.createQuery("delete from HorarioEntity").executeUpdate();
    }
    /**
     * Método auxiliar que se usa para insertar datos en la base de datos
     */
    private void insertData(){
        PodamFactory factory = new PodamFactoryImpl();
        for(int i =0; i <3; i++){
            HorarioEntity entity = factory.manufacturePojo(HorarioEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Método encargado de las pruebas del metodo create de la clase HorarioPersistence
     */
     @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        HorarioEntity newEntity = factory.manufacturePojo(HorarioEntity.class);
       HorarioEntity result = persistence.create(newEntity);
        
        Assert.assertNotNull(result);
        HorarioEntity entity = em.find(HorarioEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getHoraFin(), entity.getHoraFin());
        
    }
    
    /**
     * Método encargado de las pruebas del metodo findAll de la clase HorarioPersistence
     */
    @Test
    public void getTest(){
        List<HorarioEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for(HorarioEntity ent: list){
            boolean found = false;
            for(HorarioEntity entity: data){
                if(ent.getId().equals(entity.getId())){
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Método encargado de las pruebas del find create de la clase HorarioPersistence
     */
    @Test
    public void getOneTest(){
        HorarioEntity entity = data.get(0);
        HorarioEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getHoraFin(), newEntity.getHoraFin());
    }
    
    /**
     * Método encargado de las pruebas del metodo findHorarioInicio de la clase HorarioPersistence
     */
    
    @Test
    public void getByStartTest(){
        HorarioEntity entity= data.get(0);
        HorarioEntity newEntity = persistence.findHorarioInicio(entity.getHoraInicio());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getHoraInicio(), newEntity.getHoraInicio());
    }
     /**
     * Método encargado de las pruebas del metodo findHorarioFin de la clase HorarioPersistence
     */
    
    @Test
    public void getByEndTest(){
        HorarioEntity entity= data.get(0);
        HorarioEntity newEntity = persistence.findHorarioFin(entity.getHoraFin());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getHoraFin(), newEntity.getHoraFin());
    }
    
    /**
     * Método encargado de las pruebas del metodo update de la clase HorarioPersistence
     */
    @Test
    public void updateTest(){
        HorarioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        HorarioEntity newEntity = factory.manufacturePojo(HorarioEntity.class);
        newEntity.setId(entity.getId());
        persistence.update(newEntity);
        HorarioEntity resp = em.find(HorarioEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getHoraFin(), resp.getHoraFin());
    }
    
    /**
     * Método encargado de las pruebas del metodo delete de la clase HorarioPersistence
     */
    @Test
    public void deleteTest(){
        HorarioEntity entity = data.get(0);
        persistence.delete(entity.getId());
        HorarioEntity deleted = em.find(HorarioEntity.class,entity.getId());
        Assert.assertNull(deleted);
    }
    
    
}
