/*******************************************************************************
 * Copyright (c) 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 *******************************************************************************/


package com.ibm.ws.jaxws.ejbinwar.ejb;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "SayHelloStatelessService", targetNamespace = "http://ejb.ejbinwar.jaxws.ws.ibm.com/",
                  wsdlLocation = "META-INF/resources/wsdl/SayHelloStatelessService.wsdl")
public class SayHelloStatelessService
                extends Service
{

    private final static URL SAYHELLOSTATELESSSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(com.ibm.ws.jaxws.ejbinwar.ejb.SayHelloStatelessService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.ibm.ws.jaxws.ejbinwar.ejb.SayHelloStatelessService.class.getResource(".");
            url = new URL(baseUrl, "SayHelloSingletonService.wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'SayHelloSingletonService.wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        SAYHELLOSTATELESSSERVICE_WSDL_LOCATION = url;
    }

    public SayHelloStatelessService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SayHelloStatelessService() {
        super(SAYHELLOSTATELESSSERVICE_WSDL_LOCATION, new QName("http://ejb.ejbinwar.jaxws.ws.ibm.com/", "SayHelloStatelessService"));
    }

    /**
     * 
     * @return
     *         returns SayHello
     */
    @WebEndpoint(name = "SayHelloStalelessPort")
    public SayHello getSayHelloStalelessPort() {
        return super.getPort(new QName("http://ejb.ejbinwar.jaxws.ws.ibm.com/", "SayHelloStalelessPort"), SayHello.class);
    }

    /**
     * 
     * @param features
     *            A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy. Supported features not in the <code>features</code> parameter will have their default
     *            values.
     * @return
     *         returns SayHello
     */
    @WebEndpoint(name = "SayHelloStalelessPort")
    public SayHello getSayHelloStalelessPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ejb.ejbinwar.jaxws.ws.ibm.com/", "SayHelloStalelessPort"), SayHello.class, features);
    }

}
