package com.worldline.training.db.handler;

import com.worldline.training.db.model.Plane;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 13/11/13
 * Time: 15:59
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class PlaneHandler {

    @PersistenceContext
    private EntityManager em;


    public void createAPlane(Plane plane){
        em.persist(plane);
    }

    public Plane findById(long id){
        return em.find(Plane.class,id);
    }
}
