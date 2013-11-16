package com.worldline.training.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 13/11/13
 * Time: 22:11
 * To change this template use File | Settings | File Templates.
 */

class HelloMessage {

    @JsonProperty
    private String prefix = "hi";
    @JsonProperty
    private String name;

    HelloMessage() {
    }

    public HelloMessage(String name) {
        this.name = name;
    }

    String getPrefix() {
        return prefix;
    }

    void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }
}
