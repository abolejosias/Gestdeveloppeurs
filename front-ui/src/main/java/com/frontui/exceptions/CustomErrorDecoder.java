/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.exceptions;

import com.frontui.toolkits.commonparameter;
import feign.Response;
import feign.codec.ErrorDecoder;

/**
 *
 * @author hp i3
 */
public class CustomErrorDecoder implements ErrorDecoder{
    
    private final ErrorDecoder OErrorDecoder=new Default();

    @Override
    public Exception decode(String invoquer, Response response) {
        System.out.println("dans error decode************************************************"+response.status());
        if(response.status()== commonparameter.ErrorCode_404){
            return new ActionNotFoundException("la liste des actions est vide");
        }
       
        return OErrorDecoder.decode(invoquer, response);
    }
    
}
