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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import co.edu.uniandes.csw.monitoria.entities.ValoracionEntity;
import co.edu.uniandes.csw.monitoria.persistence.ValoracionPersistence;
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
public class ValoracionPersistenceTest {
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Valoracion, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ValoracionEntity.class.getPackage())
                .addPackage(ValoracionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase ValoracionPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private ValoracionPersistence valoracionPersistence;

    /**
     * Contexto de Persistencia que se va autilizar para acceder a la Base de
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
        em.createQuery("delete from ValoracionEntity").executeUpdate();
    }

    /**
     *
       */
    private List<ValoracionEntity> data = new ArrayList<ValoracionEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ValoracionEntity entity = factory.manufacturePojo(ValoracionEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear una Valoracion.
     *
     *
     */
    @Test
    public void createValoracionTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ValoracionEntity newEntity = factory.manufacturePojo(ValoracionEntity.class);
        ValoracionEntity result = valoracionPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ValoracionEntity entity = em.find(ValoracionEntity.class, result.getId());

//        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getCalificacion(), entity.getCalificacion());
        Assert.assertEquals(newEntity.getComentario(), entity.getComentario());
         Assert.assertEquals(newEntity.getFecha(), entity.getFecha());
    }

    /**
     * Prueba para consultar la lista de Valoraciones.
     *
     *
     */
    @Test
    public void getValoracionesTest() {
        List<ValoracionEntity> list = valoracionPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ValoracionEntity ent : list) {
            boolean found = false;
            for (ValoracionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Valoracion.
     *
     *
     */
    @Test
    public void getValoracionTest() {
        ValoracionEntity entity = data.get(0);
        ValoracionEntity newEntity = valoracionPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
//        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getCalificacion(), newEntity.getCalificacion());
        Assert.assertEquals(entity.getComentario(), newEntity.getComentario());
        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());
    }

    /**
     * Prueba para eliminar un Valoracion.
     *
     *
     */
    @Test
    public void deleteValoracionTest() {
        ValoracionEntity entity = data.get(0);
        valoracionPersistence.delete(entity.getId());
        ValoracionEntity deleted = em.find(ValoracionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Valoracion.
     *
     *
     */
    @Test
    public void updateValoracionTest() {
        ValoracionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
       ValoracionEntity newEntity = factory.manufacturePojo(ValoracionEntity.class);
        newEntity.setId(entity.getId());
        valoracionPersistence.update(newEntity);

        ValoracionEntity resp = em.find(ValoracionEntity.class, entity.getId());

//        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getCalificacion(), resp.getCalificacion());
        Assert.assertEquals(newEntity.getComentario(), resp.getComentario());
         Assert.assertEquals(newEntity.getFecha(), resp.getFecha());
    }
    
}
