/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.service;

import com.frontui.model.Role;
import com.frontui.toolkits.Iservicemsg;
import java.util.List;


/**
 *
 * @author hp i3
 */

public interface RoleService extends Iservicemsg{
    List<Role> findall();
    Role findById(Integer Id);
    Boolean deleteRole(Integer Id);
    Boolean createRole(String strName);
    Boolean updateRole(Integer Id,String strName);
    
}
