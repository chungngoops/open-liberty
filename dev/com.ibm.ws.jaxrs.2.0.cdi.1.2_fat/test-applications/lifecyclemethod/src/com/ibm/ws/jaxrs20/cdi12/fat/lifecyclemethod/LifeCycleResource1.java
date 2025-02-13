/*******************************************************************************
 * Copyright (c) 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.jaxrs20.cdi12.fat.lifecyclemethod;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/lifecycle1")
@RequestScoped
public class LifeCycleResource1 {

    /**
     * A static variable to hold a message. Note that for this sample, the field
     * is static because a new <code>HelloWorldResource</code> object is created
     * per request.
     */
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private @Inject
    LifeCycleSimpleBean simpleBean;

    @Context
    private UriInfo uriinfo;

    LifeCyclePerson person;

    LifeCyclePerson personForC;

    public LifeCycleSimpleBean getSimpleBean() {
        return simpleBean;
    }

    @Inject
    public void setPerson(LifeCyclePerson person) {
        this.person = person;
        System.out.println(type + " Injection successful...");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        String result = "LifeCycleResource1";
        return "Resource: " + result;
    }

    @PostConstruct
    public void method1()
    {
        System.out.println("postConstruct method is called on " + this.getClass().getName());
    }

    @PreDestroy
    public void method2()
    {
        System.out.println("preDestory method is called on " + this.getClass().getName());
    }
}