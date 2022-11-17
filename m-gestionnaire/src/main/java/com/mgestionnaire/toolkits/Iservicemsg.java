/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmenu.toolkits;

/**
 *
 * @author hp i3
 */
public interface Iservicemsg {

    public String getMessage();

    public void setMessage(String message);

    public String getDetailmessage();

    public void setDetailmessage(String Detailmessage);

    public void buildErrorTraceMessage(String DetailMessage, String ErrorSystem);

    public void buildSuccesTraceMessage(String DetailMessage);

    public void buildErrorTraceMessage(String DetailMessage);

    public void buildTraceMessage(String Message, String DetailMessage);

    public void prinntTraceInfo(Object message);

    public void prinntTraceDebug(Object message);

    public void prinntTraceError(Object message);

}
