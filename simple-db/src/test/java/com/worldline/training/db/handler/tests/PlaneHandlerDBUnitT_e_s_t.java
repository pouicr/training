package com.worldline.training.db.handler.tests;

import com.worldline.training.db.handler.PlaneHandler;
import com.worldline.training.db.model.Plane;
import org.junit.*;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.dbunit.datasetloadstrategy.impl.InsertLoadStrategy;

import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 13/11/13
 * Time: 16:07
 * To change this template use File | Settings | File Templates.
 */
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class PlaneHandlerDBUnitT_e_s_t {

    static EJBContainer container;

    @Inject
    PlaneHandler planeHandler;

    @BeforeClass
    public static void init() throws NamingException {
        Properties p = new Properties();
        p.setProperty("planeDataSource", "new://Resource?type=DataSource");
        p.setProperty("planeDataSource.JdbcDriver","org.hsqldb.jdbcDriver");
        p.setProperty("planeDataSource.JdbcUrl","jdbc:hsqldb:mem:planedb");
        p.setProperty("planeDataSource.JtaManaged","true");

        container = EJBContainer.createEJBContainer();
        final Context context = container.getContext();
        PlaneHandler planeHandlerTemp = (PlaneHandler) context.lookup("java:global/simple-db/PlaneHandler");

        Plane plane = new Plane();
        plane.setType("747");
        planeHandlerTemp.createAPlane(plane);

    }

    @Before
    public void setInject() throws NamingException {
        container.getContext().bind("inject",this);
    }

    @Test
    @DataSet(value = "/plane_set.xml",loadStrategy = InsertLoadStrategy.class)
    @ExpectedDataSet("/plane_set_final.xml")
    public void createPlaneTest(){
        Plane p = new Plane();
        p.setType("picooz");
        planeHandler.createAPlane(p);

    }

    @Test
    @DataSet(value = "/plane_set.xml",loadStrategy = InsertLoadStrategy.class)
    public void readPlaneTest(){
        Plane p = planeHandler.findById(1);
        Assert.assertEquals(p.getType(),"747");
    }

    @After
    public void close() {
        container.close();
    }
}
