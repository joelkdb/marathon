/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.rs;

import com.webapp.commons.core.service.UserServiceBeanRemote;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author komilo
 */
@Stateless
@Path("security")
public class SecurityResource implements Serializable {

//    @EJB
//    private RestSecurityServiceRemote securityService;
    @EJB
    private UserServiceBeanRemote userService;

    @GET
    @Path("hello")
    @Produces("text/plain")
    public String hello() {
        return "Hello";
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("username") String username,
            @FormParam("password") String password) {
        System.out.println("nom : " + username + " prenoms : " + password);
        if (this.userService.login(username, password)) {
            return this.getNoCacheResponseBuilder(Response.Status.OK).build();
        } else {
            return this.getNoCacheResponseBuilder(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("logout")
    public Response logout(@Context HttpHeaders httpHeaders) {
        this.userService.logout();
        return this.getNoCacheResponseBuilder(Response.Status.NO_CONTENT).build();
    }

    private Response.ResponseBuilder getNoCacheResponseBuilder(Response.Status status) {
        CacheControl cc = new CacheControl();
        cc.setNoCache(true);
        cc.setMaxAge(-1);
        cc.setMustRevalidate(true);

        return Response.status(status).cacheControl(cc);
    }
}
