/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmodules.web.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author hp i3
 */
public class MessageNotFoundException extends Exception{

    public MessageNotFoundException() {
    }
    
    public static List<Object> getMessageException(String codestatut, String descstatut) {
        List<Object> lstobjects = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        map.put("code_statut", codestatut);
        map.put("desc_statut", descstatut);
        lstobjects.add(map);
        return lstobjects;
    }

}

