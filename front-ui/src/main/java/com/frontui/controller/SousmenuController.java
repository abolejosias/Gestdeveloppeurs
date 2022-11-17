/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontui.beans.ActionsBean;
import com.frontui.beans.MenuBean;
import com.frontui.beans.SousmenuBean;
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
public class SousmenuController {

    @Autowired
    Userservice userservice;

    @Autowired
    PrivilegeService privilegeService;

    @Autowired
    MmenuProxy mmenuProxy;

    @Autowired
    MmoduleProxy mmoduleProxy;

    @GetMapping(value = "/administration/sousmenus")
    public String sousmenus(Model model, HttpSession httpSession) {
        List<MenuBean> LstMenuBeans = (List<MenuBean>) httpSession.getAttribute("lstmenusbeans");
        List<SousmenuBean> LstSousmenuBean = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userservice.findByUsername(userDetails.getUsername());

        List<ActionsBean> LstActionbeans = new ArrayList<>();

        List<ActionsBean> LstActionBeansToolbar = new ArrayList<>();
        List<ActionsBean> LstActionBeansNotToolbar = new ArrayList<>();

        LstActionbeans = mmenuProxy.listActionsToolBarparSousmenu(commonparameter.SOUSMENU_sousmenus);

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

        List<ActionsBean> LstactionsBeanNotToolBar = mmenuProxy.listActionsNotToolBarparSousmenu(commonparameter.SOUSMENU_sousmenus);
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

        List<SousmenuBean> sousmenuBeans = mmenuProxy.listsousmenus();
        sousmenuBeans.forEach((osousmenus) -> {
            MenuBean menuBean = mmenuProxy.findmenubyid(osousmenus.getIdmenu());
            if (menuBean != null) {
                osousmenus.setMenuBean(menuBean);
                LstSousmenuBean.add(osousmenus);
            }
        });

        model.addAttribute("sousmenuBeans", LstSousmenuBean);
        model.addAttribute("lstmenusbeans", LstMenuBeans);
        model.addAttribute("lstactionsToolbar", LstActionBeansToolbar);
        model.addAttribute("lstactionsNotToolbar", LstActionBeansNotToolbar);
        return "modules/administration/sousmenus/index";
    }

    @PostMapping(value = "/administration/storesousmenu", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createsousmenu(@RequestParam String str_value, @RequestParam String str_description, @RequestParam Integer int_priority, @RequestParam Integer idmenu) {
        System.out.println("com.frontui.controller.SousMenuController.creatsousemenu()**************************" + str_value);
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> lstObjects = this.mmenuProxy.createsousmenu(str_value, str_description, int_priority, idmenu);
        HashMap<String, String> Hmap = objectMapper.convertValue(lstObjects.get(0), HashMap.class);
        jSONObject.put("results", Hmap.get("code_statut"));
        jSONObject.put("msg", Hmap.get("desc_statut"));
        arrayObj.put(jSONObject);
        return arrayObj.toString();
    }

    @PostMapping(value = "/administration/deletesousmenu", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deletesousmenu(@RequestParam String idsousmenu) {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        List<Object> LstObjects = this.mmenuProxy.deletesousmenu(Integer.valueOf(idsousmenu));
        HashMap<String, String> Hmap = objectMapper.convertValue(LstObjects.get(0), HashMap.class);
        jSONObject.put("results", Hmap.get("code_statut"));
        jSONObject.put("msg", Hmap.get("desc_statut"));
        arrayObj.put(jSONObject);
        return arrayObj.toString();
    }
    
    
    @GetMapping(value = "/administration/listdesmenus", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getallMenus() {
        String result = "";
        JSONArray arrayObj = new JSONArray();
        List<MenuBean> lstMenuBeans = this.mmenuProxy.listMenus();
        lstMenuBeans.forEach((omenubean) -> {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", omenubean.getId());
            jSONObject.put("name", omenubean.getStrDescription());
            arrayObj.put(jSONObject);
        });
        result = "{\"recordsTotal\":" + lstMenuBeans.size() + ",\"results\":" + arrayObj.toString() + "}";
        return result;
    }
}
