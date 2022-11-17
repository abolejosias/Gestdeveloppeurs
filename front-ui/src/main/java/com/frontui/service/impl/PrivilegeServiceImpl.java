/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.service.impl;

import com.frontui.beans.PrivilegeInOutRolePrivilege;
import com.frontui.model.Privilegelevel;
import com.frontui.model.Privileges;
import com.frontui.model.Role;
import com.frontui.model.RolePrivileges;
import com.frontui.model.User;
import com.frontui.repository.PrivilegelevelRepository;
import com.frontui.repository.PrivilegesRepository;
import com.frontui.repository.RolePrivilegesRepository;
import com.frontui.repository.RoleRepository;
import com.frontui.service.PrivilegeService;
import com.frontui.toolkits.commonparameter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hp i3
 */
@Service
public class PrivilegeServiceImpl implements PrivilegeService {
    
    @Autowired
    PrivilegesRepository OprivilegesRepository;
    
    @Autowired
    RolePrivilegesRepository OrolePrivilegesRepository;
    
    @Autowired
    PrivilegelevelRepository OprivilegelevelRepository;
    
    @Autowired
    RoleRepository ORoleRepository;
    
    private String str_message;
    private String str_Detailmessage;
    
    @Override
    public List<Privileges> getAllPrivilege(String search_value) {
        
        if ((search_value.equals("")) || (search_value == null)) {
            search_value = "%%";
        } else {
            search_value = "%" + search_value + "%";
        }
        
        return OprivilegesRepository.getallprivilege();
    }
    
    @Override
    public Boolean isAvalaible(String privilegename, User user) {
        Boolean result = false;
        try {
            Privileges Oprivileges = OprivilegesRepository.findByName(privilegename);
            if (Oprivileges != null) {
                List<RolePrivileges> LstrolePrivileges = OrolePrivilegesRepository.findByidroles(user.getRoleId());
                if (!LstrolePrivileges.isEmpty()) {
                    for (RolePrivileges OrolePrivilege : LstrolePrivileges) {
                        if (OrolePrivilege.getIdprivileges().equals(Oprivileges)) {
                            result = true;
                            this.setMessage(commonparameter.PROCESS_SUCCESS);
                            this.setDetailmessage(commonparameter.PRIVILEGE_AVAILABLE);
                            System.out.println("Le privilege accorde");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public List<Privileges> getAllPrivilege(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Boolean createPrivilege(String name, String description, String idprivilegelevel, String iduser) {
        Boolean result = false;
        try {
            Privileges Oprivileges = OprivilegesRepository.findByName(name);
            if (Oprivileges != null) {
                this.setMessage(commonparameter.PROCESS_FAILED);
                this.setDetailmessage(commonparameter.EXIST_DATA);
            } else {
                Privilegelevel privilegelevel = OprivilegelevelRepository.getOne(Integer.valueOf(idprivilegelevel));
                Privileges privileges = new Privileges();
                privileges.setName(name);
                privileges.setDescription(description);
                privileges.setIdprivilegelevel(privilegelevel);
                privileges.setCreatedAt(new Date());
                privileges.setStrStatut(commonparameter.statut_enable);
                privileges.setCreatedBy(iduser);
                OprivilegesRepository.save(privileges);
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
    public Boolean updatePrivilege(String idprivilege, String name, String description, String idprivilegelevel) {
        Boolean result = false;
        try {
            Privileges privileges = OprivilegesRepository.getOne(Integer.valueOf(idprivilege));
            Privilegelevel privilegelevel = OprivilegelevelRepository.getOne(Integer.valueOf(idprivilegelevel));
            privileges.setUpdatedAt(new Date());
            privileges.setName(name);
            privileges.setDescription(description);
            privileges.setIdprivilegelevel(privilegelevel);
            OprivilegesRepository.save(privileges);
            this.setMessage(commonparameter.PROCESS_SUCCESS);
            this.setDetailmessage(commonparameter.SUCCES_UPDATE);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public void deletePrivilege(Integer idprivilege) {
        OprivilegesRepository.deleteById(idprivilege);
        this.setMessage(commonparameter.PROCESS_SUCCESS);
        this.setDetailmessage(commonparameter.SUCCES_DELETE);
    }
    
    @Override
    public List<Privileges> getAllPrivilege() {
        return OprivilegesRepository.getallprivilege();
    }
    
    @Override
    public List<Privilegelevel> getAllPrivilegeLevel() {
        return OprivilegelevelRepository.findAll();
    }
    
    @Override
    public Privilegelevel findbyId(String Id) {
        return OprivilegelevelRepository.getOne(Integer.valueOf(Id));
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
    public Privileges findbyIdprivilege(String Id) {
        return OprivilegesRepository.getOne(Integer.valueOf(Id));
    }
    
    @Override
    public Boolean isExistwithRoleprivilege(Integer idprivilege, Integer idroles) {
        Boolean result = false;
        try {
            List<RolePrivileges> LstRolePrivilege = OrolePrivilegesRepository.findByidprivilege(idprivilege, idroles);
            if (!LstRolePrivilege.isEmpty()) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public List<PrivilegeInOutRolePrivilege> getAllPrivilegeAutho_UnAutho_To_Role(String idrole,Integer privilegelevel) {
        List<Privileges> LstPrivileges = new ArrayList<>();
        List<PrivilegeInOutRolePrivilege> LstPrivilegeInOutRolePrivileges = new ArrayList<>();
        try {
            LstPrivileges = OprivilegesRepository.getallprivilegeByPrivilegeLevel(privilegelevel);
            for (Privileges OPrivilege : LstPrivileges) {
                PrivilegeInOutRolePrivilege privilegeInOutRolePrivilege =new PrivilegeInOutRolePrivilege();
                privilegeInOutRolePrivilege.setId(OPrivilege.getId());
                privilegeInOutRolePrivilege.setName(OPrivilege.getName());
                privilegeInOutRolePrivilege.setDescription(OPrivilege.getDescription());
                if(isExistwithRoleprivilege(OPrivilege.getId(), Integer.valueOf(idrole))){
                    privilegeInOutRolePrivilege.setStrStatut(commonparameter.statut_Authorize);
                }else{
                     privilegeInOutRolePrivilege.setStrStatut(commonparameter.statut_UnAuthorize);
                }
                System.out.println("Statut privilegeInOutRolePrivilege --------->"+privilegeInOutRolePrivilege.getName()+"  ----- "+privilegeInOutRolePrivilege.getStrStatut());
                LstPrivilegeInOutRolePrivileges.add(privilegeInOutRolePrivilege);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LstPrivilegeInOutRolePrivileges;
    }

    @Override
    public void createroleprivilege(Integer idrole, Integer idprivilege) {
       try{
           RolePrivileges roleprivileges = new RolePrivileges();
           Role role= ORoleRepository.getOne(idrole);
           Privileges privileges =OprivilegesRepository.getOne(idprivilege);
           roleprivileges.setIdroles(role);
           roleprivileges.setIdprivileges(privileges);
           roleprivileges.setCreatedAt(new Date());
           roleprivileges.setStrStatut(commonparameter.statut_enable);
           OrolePrivilegesRepository.save(roleprivileges);
           System.out.println("Role privilege creer avec succes!!");
           this.setMessage(commonparameter.PROCESS_SUCCESS);
           this.setDetailmessage(commonparameter.SUCCES_CREATION);
       }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteroleprivilege(Integer idrole, Integer idprivilege) {
       try{
           List<RolePrivileges> LstRolePrivileges = OrolePrivilegesRepository.findByidprivilege(idprivilege, idrole);
           if(!LstRolePrivileges.isEmpty()){
               RolePrivileges ORolePrivilege =LstRolePrivileges.get(0);
               OrolePrivilegesRepository.delete(ORolePrivilege);
               System.out.println("Role privilege supprime avec succes!!");
                this.setMessage(commonparameter.PROCESS_SUCCESS);
               this.setDetailmessage(commonparameter.SUCCES_DELETE);
           }
       }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   
}
