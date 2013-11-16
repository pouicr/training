package com.worldline.training.configuration;

import javax.annotation.Resource;
import javax.ejb.Singleton;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 13/11/13
 * Time: 20:37
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class Configuration {

    @Resource
    private String name;

    @Resource(name = "prefix")
    private String prefix;

    @Resource(name = "question")
    private String question;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
