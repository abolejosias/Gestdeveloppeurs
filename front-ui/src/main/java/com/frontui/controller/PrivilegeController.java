/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontui.beans.ActionsBean;
import com.frontui.beans.MenuBean;
import com.frontui.model.Privilegelevel;
import com.frontui.model.Privileges;
import com.frontui.model.User;
import com.frontui.proxies.MmenuProxy;
import com.frontui.service.PrivilegeService;
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
public class PrivilegeController {

    @Autowired
    Userservice userservice;

    @Autowired
    PrivilegeService privilegeService;
    
   

    @Autowired
    MmenuProxy mmenuProxy;

    @GetMapping("/administration/privilege")
    public String privileges(Model model, HttpSession httpSession) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userservice.findByUsername(userDetails.getUsername());
        List<ActionsBean> LstActionBeansToolbar = new ArrayList<>();
        List<ActionsBean> LstActionBeansNotToolbar = new ArrayList<>();
        List<MenuBean> LstMenuBeans = (List<MenuBean>) httpSession.getAttribute("lstmenusbeans");
        List<Privileges> LstPrivilege = privilegeService.getAllPrivilege();

        List<ActionsBean> LstactionsBean = mmenuProxy.listActionsToolBarparSousmenu(commonparameter.SOUSMENU_privilege);
        LstactionsBean.forEach((actionsBean) -> {
            if (privilegeService.isAvalaible(actionsBean.getPKey(), user)) {
                System.out.println("message ---- privilege accorde " + actionsBean.getPKey());
                LstActionBeansToolbar.add(actionsBean);
            }else{
                 System.out.println("message ---- privilege non accorde " + actionsBean.getPKey());
            }
        });

        List<ActionsBean> LstactionsNotToolBar = mmenuProxy.listActionsNotToolBarparSousmenu(commonparameter.SOUSMENU_privilege);
        LstactionsNotToolBar.forEach((actionsBean) -> {
            if (privilegeService.isAvalaible(actionsBean.getPKey(), user)) {
                System.out.println("message ---- privilege  accorde " + actionsBean.getPKey());
                LstActionBeansNotToolbar.add(actionsBean);
            }else{
                System.out.println("message ---- privilege non accorde " + actionsBean.getPKey());
            }
        });

        model.addAttribute("LstPrivilege", LstPrivilege);
        model.addAttribute("lstmenusbeans", LstMenuBeans);
        model.addAttribute("lstactionsToolbar", LstActionBeansToolbar);
        model.addAttribute("lstactionsNotToolbar", LstActionBeansNotToolbar);

        return "modules/administration/privilege/index";
    }

    @GetMapping(value = "/administration/privilegelevel", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getallprivilegelevel() {
        String result = "";
        JSONArray arrayObj = new JSONArray();
        List<Privilegelevel> LstPrivilegelevels = privilegeService.getAllPrivilegeLevel();
        LstPrivilegelevels.forEach((privilegelevel) -> {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", privilegelevel.getId());
            jSONObject.put("name", privilegelevel.getName());
            arrayObj.put(jSONObject);
        });
        result = "{\"recordsTotal\":" + LstPrivilegelevels.size() + ",\"results\":" + arrayObj.toString() + "}";
        return result;
    }

    @PostMapping(value = "/administration/storeprivilege", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String creerprivilege(@RequestParam String name, @RequestParam String description, @RequestParam String idprivilegelevel) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userservice.findByUsername(userDetails.getUsername());

        privilegeService.createPrivilege(name, description, idprivilegelevel, user.getId().toString());
        //System.out.println("******************************* code :  " + privilegeService.getMessage());
        JSONObject jSONObject = new JSONObject();
        JSONArray arrayObj = new JSONArray();
        jSONObject.put("results", privilegeService.getMessage());
        jSONObject.put("msg", privilegeService.getDetailmessage());
        arrayObj.put(jSONObject);
        return arrayObj.toString();
    }

    @GetMapping(value = "/administration/findprivilege/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String findprivilegeById(@PathVariable String Id) {
        JSONObject jSONObject = new JSONObject();
        JSONArray arrayObj = new JSONArray();
        Privileges privileges = privilegeService.findbyIdprivilege(Id);
        jSONObject.put("id", privileges.getId());
        jSONObject.put("name", privileges.getName());
        jSONObject.put("description", privileges.getDescription());
        jSONObject.put("idprivilegelevel", privileges.getIdprivilegelevel().getId());
        arrayObj.put(jSONObject);
        //System.out.println("*******************" + arrayObj.toString() + "*****************");
        String result = "{\"recordsTotal\":1,\"results\":" + arrayObj.toString() + "}";
        return result;
    }

    @PatchMapping(value = "/administration/updateprivilege", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateprivilege(@RequestParam String name, @RequestParam String description, @RequestParam String idprivilegelevel, @RequestParam String idprivilege) {
        privilegeService.updatePrivilege(idprivilege, name, description, idprivilegelevel);
        JSONObject jSONObject = new JSONObject();
        JSONArray arrayObj = new JSONArray();
        jSONObject.put("results", privilegeService.getMessage());
        jSONObject.put("msg", privilegeService.getDetailmessage());
        arrayObj.put(jSONObject);
        System.out.println(" -------- " + arrayObj.toString());
        return arrayObj.toString();
    }
    
    
    @PostMapping(value= "/administration/deleteprivilege",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteprivilege(@RequestParam String idprivilege){
        System.out.println(" --------idprivilege " + idprivilege);
        privilegeService.deletePrivilege(Integer.valueOf(idprivilege));
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("results", privilegeService.getMessage());
        jSONObject.put("msg", privilegeService.getDetailmessage());
        arrayObj.put(jSONObject);
        System.out.println(" -------- " + arrayObj.toString());
        return arrayObj.toString();
    }

}
