/*******************************************************************************
 * Copyright (c) 2017 IBM Corporation and others.
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
if("undefined"!=typeof OpenAjax&&("undefined"==typeof jsf||null==typeof jsf)){OpenAjax.hub.registerLibrary("jsf","www.sun.com","1.0",null);}if(!window.jsf){var jsf=new function(){this.specversion=220000;this.implversion=0;this.separatorchar=A();this.getProjectStage=function(){var B=myfaces._impl.core._Runtime.getGlobalConfig("jsfAjaxImpl",myfaces._impl.core.Impl);return B.getProjectStage();};this.getViewState=function(C){var B=myfaces._impl.core._Runtime.getGlobalConfig("jsfAjaxImpl",myfaces._impl.core.Impl);return B.getViewState(C);};this.getClientWindow=function(){var B=myfaces._impl.core._Runtime.getGlobalConfig("jsfAjaxImpl",myfaces._impl.core.Impl);return(arguments.length)?B.getClientWindow(arguments[0]):B.getClientWindow();};function A(){var B=myfaces._impl.core._Runtime.getGlobalConfig("jsfAjaxImpl",myfaces._impl.core.Impl);return B.getSeparatorChar();}};window.jsf=jsf;}if(!jsf.ajax){jsf.ajax=new function(){this.request=function(C,D,B){if(!B){B={};}var A=myfaces._impl.core._Runtime.getGlobalConfig("jsfAjaxImpl",myfaces._impl.core.Impl);return A.request(C,D,B);};this.addOnError=function(B){var A=myfaces._impl.core._Runtime.getGlobalConfig("jsfAjaxImpl",myfaces._impl.core.Impl);return A.addOnError(B);};this.addOnEvent=function(B){var A=myfaces._impl.core._Runtime.getGlobalConfig("jsfAjaxImpl",myfaces._impl.core.Impl);return A.addOnEvent(B);};this.response=function(C,B){var A=myfaces._impl.core._Runtime.getGlobalConfig("jsfAjaxImpl",myfaces._impl.core.Impl);return A.response(C,B);};};}if(!jsf.util){jsf.util=new function(){this.chain=function(C,B){var A=myfaces._impl.core._Runtime.getGlobalConfig("jsfAjaxImpl",myfaces._impl.core.Impl);return A.chain.apply(A,arguments);};};}