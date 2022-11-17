/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontui.beans.ActionsBean;
import com.frontui.beans.MenuBean;
import com.frontui.beans.ModuleBean;
import com.frontui.model.User;
import com.frontui.proxies.MmenuProxy;
import com.frontui.proxies.MmoduleProxy;
import com.frontui.service.PrivilegeService;
import com.frontui.service.Userservice;
import com.frontui.toolkits.commonparameter;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author hp i3
 */
@Controller
public class ModuleController {

    @Autowired
    Userservice userservice;

    @Autowired
    PrivilegeService privilegeService;

    @Autowired
    MmenuProxy mmenuProxy;

    @Autowired
    MmoduleProxy mmoduleProxy;

    @GetMapping("/administration/modules")
    public String modules(Model model, HttpSession httpSession) {
        List<MenuBean> LstMenuBeans = (List<MenuBean>) httpSession.getAttribute("lstmenusbeans");
        List<ModuleBean> LstModuleBean = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userservice.findByUsername(userDetails.getUsername());
        LstModuleBean = mmoduleProxy.listedesmodules();
        List<ActionsBean> LstActionbeans = new ArrayList<>();

        List<ActionsBean> LstActionBeansToolbar = new ArrayList<>();
        List<ActionsBean> LstActionBeansNotToolbar = new ArrayList<>();

        LstActionbeans = mmenuProxy.listActionsToolBarparSousmenu(commonparameter.SOUSMENU_module);

        if (!LstActionbeans.isEmpty()) {
            LstActionbeans.forEach((actionsBean) -> {
                if (privilegeService.isAvalaible(actionsBean.getPKey(), user)) {
                    System.out.println("message ---- privilege accorde " + actionsBean.getPKey());
                    LstActionBeansToolbar.add(actionsBean);
                } else {
                    System.out.println("message ---- privilege non accorde " + actionsBean.getPKey());
                }
            });
        }

        List<ActionsBean> LstactionsBeanNotToolBar = mmenuProxy.listActionsNotToolBarparSousmenu(commonparameter.SOUSMENU_module);
        if (!LstactionsBeanNotToolBar.isEmpty()) {
            LstactionsBeanNotToolBar.forEach((actionsBean) -> {
                if (privilegeService.isAvalaible(actionsBean.getPKey(), user)) {
                    System.out.println("message ---- privilege  accorde " + actionsBean.getPKey());
                    LstActionBeansNotToolbar.add(actionsBean);
                } else {
                    System.out.println("message ---- privilege non accorde " + actionsBean.getPKey());
                }
            });
        }

        model.addAttribute("lstmodules", LstModuleBean);
        model.addAttribute("lstmenusbeans", LstMenuBeans);
        model.addAttribute("lstactionsToolbar", LstActionBeansToolbar);
        model.addAttribute("lstactionsNotToolbar", LstActionBeansNotToolbar);

        return "modules/administration/modules/index";
    }

    @PostMapping(value = "/administration/storemodules", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String creermodule(@RequestParam String str_name, @RequestParam String str_description, @RequestParam String int_priority) {
        ObjectMapper objectMapper = new ObjectMapper();
        ModuleBean moduleBean = new ModuleBean();
        String str_link="";
        JSONObject jSONObject = new JSONObject();
        JSONArray arrayObj = new JSONArray();
        String[] tabname = str_name.split(" ");
        for(String tabname1 : tabname) {
            str_link = str_link + tabname1;
        }
        moduleBean.setStrValue(str_name);
        moduleBean.setStrDescription(str_description);
        moduleBean.setPKey(commonparameter.DefaultKEYMODULE + str_link.toUpperCase());
        moduleBean.setIntPriority(Integer.valueOf(int_priority));
        moduleBean.setStrStatut(commonparameter.statut_enable);
        moduleBean.setStrLinkDefault(str_link.toLowerCase() + commonparameter.DefaultModule_LINK);
        List<Object> LstObjects = this.mmoduleProxy.ajoutermodule(moduleBean);
        HashMap<String, String> Hmap = objectMapper.convertValue(LstObjects.get(0), HashMap.class);
        jSONObject.put("results", Hmap.get("code_statut"));
        jSONObject.put("msg", Hmap.get("desc_statut"));
        arrayObj.put(jSONObject);
        return arrayObj.toString();
    }

    //findmodule
    @GetMapping(value = "/administration/findmodule/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String findmodulebyId(@PathVariable String id) {
        JSONObject jSONObject = new JSONObject();
        JSONArray arrayObj = new JSONArray();
        ModuleBean moduleBean = this.mmoduleProxy.recupererunmodule(Integer.valueOf(id));
        jSONObject.put("id", moduleBean.getId());
        jSONObject.put("str_value", moduleBean.getStrValue());
        jSONObject.put("str_description", moduleBean.getStrDescription());
        jSONObject.put("int_priority", moduleBean.getIntPriority());
        jSONObject.put("p_key", moduleBean.getPKey());
        arrayObj.put(jSONObject);
        //System.out.println("*******************" + arrayObj.toString() + "*****************");
        String result = "{\"recordsTotal\":1,\"results\":" + arrayObj.toString() + "}";
        return result;
    }

    @PutMapping(value = "/administration/updatemodules", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updatemodule(@RequestParam String idmodule, @RequestParam String str_name, @RequestParam String str_description, @RequestParam String int_priority) {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        ModuleBean moduleBean = this.mmoduleProxy.recupererunmodule(Integer.valueOf(idmodule));
        moduleBean.setStrValue(str_name);
        moduleBean.setStrDescription(str_description);
        moduleBean.setIntPriority(Integer.valueOf(int_priority));
        List<Object> lstObjects = this.mmoduleProxy.updatemodule(moduleBean);
        HashMap<String, String> Hmap = objectMapper.convertValue(lstObjects.get(0), HashMap.class);
        jSONObject.put("results", Hmap.get("code_statut"));
        jSONObject.put("msg", Hmap.get("desc_statut"));
        arrayObj.put(jSONObject);
        return arrayObj.toString();
    }

    @PostMapping(value = "/administration/deletemodules", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deletemodule(@RequestParam String idmodule) {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        List<Object> LstObjects = this.mmoduleProxy.deletemodule(Integer.valueOf(idmodule));
        HashMap<String, String> Hmap = objectMapper.convertValue(LstObjects.get(0), HashMap.class);
        jSONObject.put("results", Hmap.get("code_statut"));
        jSONObject.put("msg", Hmap.get("desc_statut"));
        arrayObj.put(jSONObject);
        return arrayObj.toString();
    }

}
