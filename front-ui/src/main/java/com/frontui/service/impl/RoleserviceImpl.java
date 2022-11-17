/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.service.impl;

import com.frontui.model.Role;
import com.frontui.repository.RoleRepository;
import com.frontui.service.RoleService;
import com.frontui.toolkits.commonparameter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp i3
 */
@Service
public class RoleserviceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;
    private String str_message;
    private String str_Detailmessage;

    @Override
    public List<Role> findall() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Integer Id) {
        return roleRepository.getOne(Id);
    }

    @Override
    public Boolean deleteRole(Integer Id) {
        Boolean result = false;
        try {
            Role orole = this.findById(Id);
            if (orole == null) {
                this.setMessage(commonparameter.PROCESS_FAILED);
                this.setDetailmessage(commonparameter.EMPTY_DATA);
            } else {
                if (orole.getRolePrivilegesCollection().isEmpty()) {
                    roleRepository.delete(orole);
                    this.setMessage(commonparameter.PROCESS_SUCCESS);
                    this.setDetailmessage(commonparameter.SUCCES_DELETE);
                } else {
                    this.setMessage(commonparameter.PROCESS_FAILED);
                    this.setDetailmessage(commonparameter.MESSAGE_PERSONNALISE_SUPP_ROLE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Boolean createRole(String strName) {
        Boolean result = false;
        try {
            Role role = roleRepository.findbyRoleName(strName);
            if (role != null) {
                this.setMessage(commonparameter.PROCESS_FAILED);
                this.setDetailmessage(commonparameter.EXIST_DATA);
            } else {
                role = new Role();
                role.setRoleName(strName);
                roleRepository.save(role);
                this.setMessage(commonparameter.PROCESS_SUCCESS);
                this.setDetailmessage(commonparameter.SUCCES_CREATION);
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getMessage() {
        return str_message;
    }

    @Override
    public void setMessage(String message) {
        this.str_message = message;
    }

    @Override
    public String getDetailmessage() {
        return str_Detailmessage;
    }

    @Override
    public void setDetailmessage(String Detailmessage) {
        this.str_Detailmessage = Detailmessage;
    }

    @Override
    public Boolean updateRole(Integer Id, String strName) {
        Boolean result = false;
        try {
            Role role = this.findById(Id);
            role.setRoleName(strName);
            roleRepository.save(role);
            this.setMessage(commonparameter.PROCESS_SUCCESS);
            this.setDetailmessage(commonparameter.SUCCES_UPDATE);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
