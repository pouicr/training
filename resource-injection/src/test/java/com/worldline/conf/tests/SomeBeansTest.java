package com.worldline.conf.tests;

import com.worldline.training.beans.SomeBeans;
import com.worldline.training.configuration.MyBean;
import com.worldline.training.configuration.MyFactory;
import com.worldline.training.configuration.SuperBan;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import javax.ejb.BeforeCompletion;
import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.naming.NamingException;
import java.io.File;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 12/11/13
 * Time: 17:00
 * To change this template use File | Settings | File Templates.
 */
public class SomeBeansTest {

    EJBContainer container;

    @Inject
    SomeBeans someBeans;

    @Resource(name = "ban")
    private SuperBan ban;


    @Resource(name = "bin")
    private SuperBan bin;

    @Resource(name = "bon")
    private SuperBan bon;

    @Resource(name = "pwet")
    private MyBean bean;

    @Before
    public void init() throws NamingException {
        container = EJBContainer.createEJBContainer(new Properties() {{
            setProperty("ban", "new://Resource?class-name=" + SuperBan.class.getName()+"&aliases=bin,bon");
            setProperty("ban.name", "pouet");
            setProperty("pwet", "new://Resource?class-name=" + MyFactory.class.getName()+"&factory-name=create");;
            setProperty("pwet.name", "pouet");
            setProperty("pwet.ignored", "unknown");
        }});

        container.getContext().bind("inject",this);
        assertNotNull(ban);
        assertEquals("pouet", ban.getName());
        assertEquals(bon, ban);
        assertEquals(bon, bin);
        assertEquals(bin, ban);
        assertEquals("pouet", ban.getName());
        assertEquals(bean.getName(),"pouet" );
    }

    @After
    public void close() {
        container.close();
    }


    @Test
    public void testSayHi() throws Exception {
        Assert.assertTrue(someBeans.sayHi().contains("Hi pouic"));
        Assert.assertTrue(someBeans.sayHi().contains("How are you"));
    }
}
