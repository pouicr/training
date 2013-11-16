package com.worldline.training.configuration;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 14/11/13
 * Time: 04:02
 * To change this template use File | Settings | File Templates.
 */
public class MyFactory {

    private String name;
    private Properties properties;

    public MyBean create(){
        System.out.println(properties);
        MyBean b = new MyBean();
        b.setName(name);
        return b;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
