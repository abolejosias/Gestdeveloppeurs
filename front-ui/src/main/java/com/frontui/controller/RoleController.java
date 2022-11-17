/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontui.beans.ActionsBean;
import com.frontui.beans.MenuBean;
import com.frontui.beans.PrivilegeInOutRolePrivilege;
import com.frontui.model.Role;
import com.frontui.model.User;
import com.frontui.proxies.MmenuProxy;
import com.frontui.service.PrivilegeService;
import com.frontui.service.RoleService;
import com.frontui.service.Userservice;
import com.frontui.toolkits.commonparameter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author hp i3
 */
@Controller
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    Userservice userservice;

    @Autowired
    PrivilegeService privilegeService;

    @Autowired
    MmenuProxy mmenuProxy;

    @GetMapping(value = "/administration/roles")
    public String Roles(Model model, HttpSession httpSession) {
        List<MenuBean> LstMenuBeans = (List<MenuBean>) httpSession.getAttribute("lstmenusbeans");
        ObjectMapper objectMapper = new ObjectMapper();
        List<ActionsBean> LstActionBeansToolbar = new ArrayList<>();
        List<ActionsBean> LstActionBeansNotToolbar = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userservice.findByUsername(userDetails.getUsername());

        List<ActionsBean> LstactionsBeanToolBar = mmenuProxy.listActionsToolBarparSousmenu(commonparameter.SOUSMENU_role);
        LstactionsBeanToolBar.forEach((actionsBean) -> {
            if (privilegeService.isAvalaible(actionsBean.getPKey(), user)) {
                System.out.println("message ---- privilege accorde " + actionsBean.getPKey());
                LstActionBeansToolbar.add(actionsBean);
            } else {
                System.out.println("message ---- privilege non accorde " + actionsBean.getPKey());
            }
        });

        List<ActionsBean> LstactionsBeanNotToolBar = mmenuProxy.listActionsNotToolBarparSousmenu(commonparameter.SOUSMENU_role);
        LstactionsBeanNotToolBar.forEach((actionsBean) -> {
            if (privilegeService.isAvalaible(actionsBean.getPKey(), user)) {
                System.out.println("message ---- privilege  accorde " + actionsBean.getPKey());
                LstActionBeansNotToolbar.add(actionsBean);
            } else {
                System.out.println("message ---- privilege non accorde " + actionsBean.getPKey());
            }
        });

        List<Role> lstroles = roleService.findall();
        model.addAttribute("lstroles", lstroles);
        model.addAttribute("lstmenusbeans", LstMenuBeans);
        model.addAttribute("lstactionsToolbar", LstActionBeansToolbar);
        model.addAttribute("lstactionsNotToolbar", LstActionBeansNotToolbar);

        return "modules/administration/roles/index";
    }

    @PostMapping(value = "/administration/storerole", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String Creerrole(@RequestParam String name) {
        JSONObject jSONObject = new JSONObject();
        JSONArray arrayObj = new JSONArray();
        roleService.createRole(name);
        jSONObject.put("results", roleService.getMessage());
        jSONObject.put("msg", roleService.getDetailmessage());
        arrayObj.put(jSONObject);
        System.err.println("arrayObj **********" + arrayObj.toString());
        return arrayObj.toString();
    }

    @GetMapping(value = "/administration/findrole/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String findRolebyId(@PathVariable String Id) {
        JSONObject jSONObject = new JSONObject();
        JSONArray arrayObj = new JSONArray();
        Role role = roleService.findById(Integer.valueOf(Id));
        jSONObject.put("id", role.getId());
        jSONObject.put("name", role.getRoleName());
        arrayObj.put(jSONObject);
        System.out.println("*******************" + arrayObj.toString() + "*****************");
        String result = "{\"recordsTotal\":1,\"results\":" + arrayObj.toString() + "}";
        return result;
    }

    @PatchMapping(value = "/administration/updaterole", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateRole(@RequestParam String name, @RequestParam String idrole) {
        roleService.updateRole(Integer.valueOf(idrole), name);
        JSONObject jSONObject = new JSONObject();
        JSONArray arrayObj = new JSONArray();
        jSONObject.put("results", roleService.getMessage());
        jSONObject.put("msg", roleService.getDetailmessage());
        arrayObj.put(jSONObject);
        System.out.println(" -------- " + arrayObj.toString());
        return arrayObj.toString();
    }

    @PostMapping(value = "/administration/deleterole", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleterole(@RequestParam String idrole) {
        System.out.println(" --------idrole " + idrole);
        roleService.deleteRole(Integer.valueOf(idrole));
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("results", roleService.getMessage());
        jSONObject.put("msg", roleService.getDetailmessage());
        arrayObj.put(jSONObject);
        System.out.println(" -------- " + arrayObj.toString());
        return arrayObj.toString();
    }

    @GetMapping(value = "/administration/afficherprivilege")
    public String afficherprivileges(Model model, HttpSession httpSession, @RequestParam String idrole) {
        List<MenuBean> LstMenuBeans = (List<MenuBean>) httpSession.getAttribute("lstmenusbeans");
        List<PrivilegeInOutRolePrivilege> MOD_privilegeInOutRolePrivileges = privilegeService.getAllPrivilegeAutho_UnAutho_To_Role(idrole, commonparameter.PrivilegelevelModule);
        List<PrivilegeInOutRolePrivilege> M_privilegeInOutRolePrivileges = privilegeService.getAllPrivilegeAutho_UnAutho_To_Role(idrole, commonparameter.PrivilegelevelMenu);
        List<PrivilegeInOutRolePrivilege> SM_privilegeInOutRolePrivileges = privilegeService.getAllPrivilegeAutho_UnAutho_To_Role(idrole, commonparameter.PrivilegelevelSousMenu);
        List<PrivilegeInOutRolePrivilege> A_privilegeInOutRolePrivileges = privilegeService.getAllPrivilegeAutho_UnAutho_To_Role(idrole, commonparameter.PrivilegelevelAction);
        Role role = roleService.findById(Integer.valueOf(idrole));
         
        model.addAttribute("lstmenusbeans", LstMenuBeans);
        model.addAttribute("lstprivilegesModule", MOD_privilegeInOutRolePrivileges);
        model.addAttribute("lstprivilegesMenu", M_privilegeInOutRolePrivileges);
        model.addAttribute("lstprivilegesSousMenu", SM_privilegeInOutRolePrivileges);
        model.addAttribute("lstprivilegesAction", A_privilegeInOutRolePrivileges);
        model.addAttribute("role", role);
        return "modules/administration/roles/afficherprivilege";
    }
    
    @PostMapping(value = "/administration/createroleprivilege", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createroleprivilege(@RequestParam String idrole,@RequestParam String idprivilege) {
        System.out.println(" --------idrole " + idrole);
        System.out.println(" --------idprivilege " + idprivilege);
        privilegeService.createroleprivilege(Integer.valueOf(idrole),Integer.valueOf(idprivilege));
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("results", privilegeService.getMessage());
        jSONObject.put("msg", privilegeService.getDetailmessage());
        arrayObj.put(jSONObject);
        System.out.println(" -------- " + arrayObj.toString());
        return arrayObj.toString();
    }
    
    @PostMapping(value = "/administration/deleteroleprivilege", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteroleprivilege(@RequestParam String idrole,@RequestParam String idprivilege) {
        System.out.println(" --------idrole " + idrole);
        System.out.println(" --------idprivilege " + idprivilege);
        privilegeService.deleteroleprivilege(Integer.valueOf(idrole),Integer.valueOf(idprivilege));
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("results", privilegeService.getMessage());
        jSONObject.put("msg", privilegeService.getDetailmessage());
        arrayObj.put(jSONObject);
        System.out.println(" -------- " + arrayObj.toString());
        return arrayObj.toString();
    }


}
