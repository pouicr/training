package com.worldline.training.db.handler.tests;

import com.worldline.training.db.handler.PlaneHandler;
import com.worldline.training.db.model.Plane;
import org.junit.*;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.dbunit.datasetloadstrategy.impl.InsertLoadStrategy;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 13/11/13
 * Time: 16:07
 * To change this template use File | Settings | File Templates.
 */
public class PlaneHandlerTest  {

    static EJBContainer container;

    @EJB
    PlaneHandler planeHandler;

    @Resource (name="planeDataSource")
    DataSource ds;

    @Before
    public void init() throws NamingException {
        Properties p = new Properties();
        p.setProperty("planeDataSource", "new://Resource?type=DataSource");
        p.setProperty("planeDataSource.JdbcDriver","org.hsqldb.jdbcDriver");
        p.setProperty("planeDataSource.JdbcUrl","jdbc:hsqldb:mem:planedb");
        p.setProperty("planeDataSource.JtaManaged","true");

        container = EJBContainer.createEJBContainer(p);
        container.getContext().bind("inject",this);
        PlaneHandler planeHandlerTemp = (PlaneHandler) container.getContext().lookup("java:global/simple-db/PlaneHandler");

        Plane plane = new Plane();
        plane.setType("747");
        planeHandlerTemp.createAPlane(plane);

    }

    @Test
    public void createPlaneTest(){
        Plane p = new Plane();
        p.setType("picooz");
        planeHandler.createAPlane(p);

    }

    @Test
    public void readPlaneTest(){
        Plane p = planeHandler.findById(1);
        Assert.assertEquals(p.getType(),"747");
    }

    @Test
    public void testInject() throws SQLException {
        final Connection connection = ds.getConnection();
        String url = connection.getMetaData().getURL();
        connection.close();
        Assert.assertEquals(url, "jdbc:hsqldb:mem:planedb");
    }

    @After
    public void close() {
        container.close();
    }
}
