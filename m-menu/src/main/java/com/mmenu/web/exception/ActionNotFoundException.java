/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmenu.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author hp i3
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ActionNotFoundException extends RuntimeException{

    public ActionNotFoundException(String message) {
        super(message);
    }
    
}
