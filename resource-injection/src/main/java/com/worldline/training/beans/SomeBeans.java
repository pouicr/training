package com.worldline.training.beans;

import com.worldline.training.configuration.Configuration;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 12/11/13
 * Time: 15:45
 * To change this template use File | Settings | File Templates.
 */
public class SomeBeans {

    @Inject
    private Configuration configuration;

    public String sayHi(){
        return (configuration.getPrefix()+configuration.getName()+"! "+configuration.getQuestion());
    }
}
