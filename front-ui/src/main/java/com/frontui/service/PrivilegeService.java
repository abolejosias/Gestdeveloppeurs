/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.service;

import com.frontui.beans.PrivilegeInOutRolePrivilege;
import com.frontui.model.Privilegelevel;
import com.frontui.model.Privileges;
import com.frontui.model.User;
import java.util.List;
import com.frontui.toolkits.Iservicemsg;

/**
 *
 * @author hp i3
 */

public interface PrivilegeService  extends Iservicemsg{
    
    Boolean createPrivilege(String name,String description,String idprivilegelevel,String iduser);
    Boolean updatePrivilege(String idprivilege,String name,String description,String idprivilegelevel);
    void deletePrivilege(Integer idprivilege);
    List<Privileges> getAllPrivilege(String search_value);
    Boolean isAvalaible(String privilegename,User user);
    List<Privileges> getAllPrivilege(User user);
    List<PrivilegeInOutRolePrivilege> getAllPrivilegeAutho_UnAutho_To_Role(String idrole,Integer privilegelevel);
    List<Privileges> getAllPrivilege();
    List<Privilegelevel> getAllPrivilegeLevel();
    Privilegelevel findbyId(String Id);
    Privileges findbyIdprivilege(String Id);
    Boolean isExistwithRoleprivilege(Integer idprivilege,Integer idroles);
    void createroleprivilege(Integer idrole ,Integer idprivilege);
    void deleteroleprivilege(Integer idrole ,Integer idprivilege);
    
}
