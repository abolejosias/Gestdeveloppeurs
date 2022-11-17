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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author hp i3
 */
@Controller
public class MenuController {

    @Autowired
    Userservice userservice;

    @Autowired
    PrivilegeService privilegeService;

    @Autowired
    MmenuProxy mmenuProxy;

    @Autowired
    MmoduleProxy mmoduleProxy;

    @GetMapping("/administration/menus")
    public String menus(Model model, HttpSession httpSession) {
        List<MenuBean> LstMenuBeans = (List<MenuBean>) httpSession.getAttribute("lstmenusbeans");
        List<MenuBean> LstMenusbean = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userservice.findByUsername(userDetails.getUsername());

        List<ActionsBean> LstActionbeans = new ArrayList<>();

        List<ActionsBean> LstActionBeansToolbar = new ArrayList<>();
        List<ActionsBean> LstActionBeansNotToolbar = new ArrayList<>();

        LstActionbeans = mmenuProxy.listActionsToolBarparSousmenu(commonparameter.SOUSMENU_menus);

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

        List<ActionsBean> LstactionsBeanNotToolBar = mmenuProxy.listActionsNotToolBarparSousmenu(commonparameter.SOUSMENU_menus);
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

        List<MenuBean> menuBeans = mmenuProxy.listMenus();
        menuBeans.forEach((omenuBeans) -> {
            ModuleBean moduleBean = mmenuProxy.findmodulebymenuid(omenuBeans.getId());
            if (moduleBean != null) {
                omenuBeans.setModuleBean(moduleBean);
                LstMenusbean.add(omenuBeans);
            }
        });

        model.addAttribute("menuBeans", LstMenusbean);
        model.addAttribute("lstmenusbeans", LstMenuBeans);
        model.addAttribute("lstactionsToolbar", LstActionBeansToolbar);
        model.addAttribute("lstactionsNotToolbar", LstActionBeansNotToolbar);

        return "modules/administration/menus/index";
    }

    @GetMapping(value = "/administration/listmodules", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getallModule() {
        String result = "";
        JSONArray arrayObj = new JSONArray();
        List<ModuleBean> lstModuleBeans = this.mmoduleProxy.listedesmodules();
        lstModuleBeans.forEach((omodulebean) -> {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", omodulebean.getId());
            jSONObject.put("name", omodulebean.getStrValue());
            arrayObj.put(jSONObject);
        });
        result = "{\"recordsTotal\":" + lstModuleBeans.size() + ",\"results\":" + arrayObj.toString() + "}";
        return result;
    }

    @PostMapping(value = "/administration/storemenu", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createmenu(@RequestParam String str_value, @RequestParam String str_description, @RequestParam Integer int_priority, @RequestParam Integer idmodule) {
        System.out.println("com.frontui.controller.MenuController.createmenu()**************************"+str_value);
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> lstObjects = this.mmenuProxy.createmenu(str_value, str_description, int_priority, idmodule);
        HashMap<String, String> Hmap = objectMapper.convertValue(lstObjects.get(0), HashMap.class);
        jSONObject.put("results", Hmap.get("code_statut"));
        jSONObject.put("msg", Hmap.get("desc_statut"));
        arrayObj.put(jSONObject);
        return arrayObj.toString();
    }
    
    
    @PostMapping(value = "/administration/deletemenu", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deletemenu(@RequestParam String idmenu) {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        List<Object> LstObjects = this.mmenuProxy.deletemenu(Integer.valueOf(idmenu));
        HashMap<String, String> Hmap = objectMapper.convertValue(LstObjects.get(0), HashMap.class);
        jSONObject.put("results", Hmap.get("code_statut"));
        jSONObject.put("msg", Hmap.get("desc_statut"));
        arrayObj.put(jSONObject);
        return arrayObj.toString();
    }
    

}
