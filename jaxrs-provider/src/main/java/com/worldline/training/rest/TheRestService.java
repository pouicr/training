package com.worldline.training.rest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 13/11/13
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
@Path("/rest")
public class TheRestService {

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping(){
        return "pong";
    }

    @GET
    @Path("/hi/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public HelloMessage sayHi(@PathParam("name") String name){
        return new HelloMessage(name);
    }


}
