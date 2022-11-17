/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontui.beans.ActionsBean;
import com.frontui.beans.DeveloperBean;
import com.frontui.beans.DeveloperInOutProjectsBean;
import com.frontui.beans.MenuBean;
import com.frontui.beans.ProjectsBean;
import com.frontui.beans.RequesttoparticipateBean;
import com.frontui.model.User;
import com.frontui.proxies.MgestionnaireProxy;
import com.frontui.proxies.MmenuProxy;
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
public class ProjectsController {
 
    
    @Autowired
    Userservice userservice;

    @Autowired
    PrivilegeService privilegeService;
    
    @Autowired
    MgestionnaireProxy mgestionnaireProxy;
    
    @Autowired
    MmenuProxy mmenuProxy;
    
    
    @GetMapping("/gestionnaire/projets")
    public String projets(Model model, HttpSession httpSession) {
        List<MenuBean> LstMenuBeans = (List<MenuBean>) httpSession.getAttribute("lstmenusbeans");
        List<ProjectsBean> LstprojetBean = new ArrayList<>();
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userservice.findByUsername(userDetails.getUsername());
        LstprojetBean = mgestionnaireProxy.listProjects();
        List<ActionsBean> LstActionbeans = new ArrayList<>();

        List<ActionsBean> LstActionBeansToolbar = new ArrayList<>();
        List<ActionsBean> LstActionBeansNotToolbar = new ArrayList<>();

        LstActionbeans = mmenuProxy.listActionsToolBarparSousmenu(commonparameter.SOUSMENU_projets);

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

        List<ActionsBean> LstactionsBeanNotToolBar = mmenuProxy.listActionsNotToolBarparSousmenu(commonparameter.SOUSMENU_projets);
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

        model.addAttribute("LstprojetBean", LstprojetBean);
        model.addAttribute("lstmenusbeans", LstMenuBeans);
        model.addAttribute("lstactionsToolbar", LstActionBeansToolbar);
        model.addAttribute("lstactionsNotToolbar", LstActionBeansNotToolbar);

        return "modules/gestionnaire/projets/index";
    }
    
    
    
    @PostMapping(value = "/gestionnaire/storeprojets", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createprojets(@RequestParam String name, @RequestParam String description) {
        System.out.println("com.frontui.controller.ProjetsController.createprojets()**************************"+description);
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> lstObjects = this.mgestionnaireProxy.createprojects(name, description,commonparameter.created_by);
        HashMap<String, String> Hmap = objectMapper.convertValue(lstObjects.get(0), HashMap.class);
        jSONObject.put("results", Hmap.get("code_statut"));
        jSONObject.put("msg", Hmap.get("desc_statut"));
        arrayObj.put(jSONObject);
        return arrayObj.toString();
    }
    
    
     //findmodule
    @GetMapping(value = "/gestionnaire/findprojets/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String findprojetsbyId(@PathVariable String id) {
        JSONObject jSONObject = new JSONObject();
        JSONArray arrayObj = new JSONArray();
        ProjectsBean projectsBean = this.mgestionnaireProxy.findprojectsbyid(Integer.valueOf(id));
        jSONObject.put("id", projectsBean.getId());
        jSONObject.put("name", projectsBean.getName());
        jSONObject.put("description", projectsBean.getDescription());
        arrayObj.put(jSONObject);
        String result = "{\"recordsTotal\":1,\"results\":" + arrayObj.toString() + "}";
        return result;
    }
    
    
    @PutMapping(value = "/gestionnaire/updateprojets", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateprojets(@RequestParam String idprojets, @RequestParam String description) {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        List<Object> lstObjects = this.mgestionnaireProxy.udpateprojects(Integer.valueOf(idprojets),description,commonparameter.created_by);
        HashMap<String, String> Hmap = objectMapper.convertValue(lstObjects.get(0), HashMap.class);
        jSONObject.put("results", Hmap.get("code_statut"));
        jSONObject.put("msg", Hmap.get("desc_statut"));
        arrayObj.put(jSONObject);
        return arrayObj.toString();
    }
    
     @PostMapping(value = "/gestionnaire/deleteprojets", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteprojets(@RequestParam String idprojets) {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        List<Object> LstObjects = this.mgestionnaireProxy.deleteprojects(Integer.valueOf(idprojets));
        HashMap<String, String> Hmap = objectMapper.convertValue(LstObjects.get(0), HashMap.class);
        jSONObject.put("results", Hmap.get("code_statut"));
        jSONObject.put("msg", Hmap.get("desc_statut"));
        arrayObj.put(jSONObject);
        return arrayObj.toString();
    }
    
     @GetMapping(value = "/gestionnaire/affecterdeveloppeurs")
    public String affecterdeveloppeurs(Model model, HttpSession httpSession, @RequestParam String idprojets) {
        List<MenuBean> LstMenuBeans = (List<MenuBean>) httpSession.getAttribute("lstmenusbeans");
        List<DeveloperInOutProjectsBean> LstDeveloperInOutProjectsBean = mgestionnaireProxy.listDeveloperAutho_UnAutho_To_Projects(Integer.valueOf(idprojets));
        ProjectsBean projectsBean = mgestionnaireProxy.findprojectsbyid(Integer.valueOf(idprojets));
        model.addAttribute("lstmenusbeans", LstMenuBeans);
        model.addAttribute("lstDeveloperInOutProjectsBean", LstDeveloperInOutProjectsBean);
        model.addAttribute("projectsBean", projectsBean);
        return "modules/gestionnaire/projets/affecterdeveloppeurs";
    }
    
    @PostMapping(value = "/gestionnaire/createprojectmember", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createprojectmember(@RequestParam String idprojets,@RequestParam String iddeveloper) {
        System.out.println(" --------idprojets " + idprojets);
        System.out.println(" --------iddeveloper " + iddeveloper);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> LstObjects = mgestionnaireProxy.createprojectmember(Integer.valueOf(idprojets),Integer.valueOf(iddeveloper),commonparameter.created_by);
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        HashMap<String, String> Hmap = objectMapper.convertValue(LstObjects.get(0), HashMap.class);
        jSONObject.put("results", Hmap.get("code_statut"));
        jSONObject.put("msg", Hmap.get("desc_statut"));
        arrayObj.put(jSONObject);
        System.out.println(" -------- " + arrayObj.toString());
        return arrayObj.toString();
    }
    
    @PostMapping(value = "/gestionnaire/deleteprojectmember", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteprojectmember(@RequestParam String idprojets,@RequestParam String iddeveloper) {
        System.out.println(" --------idprojets " + idprojets);
        System.out.println(" --------iddeveloper " + iddeveloper);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> LstObjects = mgestionnaireProxy.deleteprojectmember(Integer.valueOf(idprojets), Integer.valueOf(iddeveloper));
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        HashMap<String, String> Hmap = objectMapper.convertValue(LstObjects.get(0), HashMap.class);
        jSONObject.put("results", Hmap.get("code_statut"));
        jSONObject.put("msg", Hmap.get("desc_statut"));
        arrayObj.put(jSONObject);
        System.out.println(" -------- " + arrayObj.toString());
        return arrayObj.toString();
    }
    
    
    @GetMapping("/gestionnaire/listedesdemandes")
    public String listedesdemandes(Model model, HttpSession httpSession) {
        List<MenuBean> LstMenuBeans = (List<MenuBean>) httpSession.getAttribute("lstmenusbeans");
        List<RequesttoparticipateBean> LstRequesttoparticipateBean = new ArrayList<>();
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userservice.findByUsername(userDetails.getUsername());
        LstRequesttoparticipateBean = mgestionnaireProxy.listrequesttoparticipate();
        List<ActionsBean> LstActionbeans = new ArrayList<>();

        List<ActionsBean> LstActionBeansToolbar = new ArrayList<>();
        List<ActionsBean> LstActionBeansNotToolbar = new ArrayList<>();

        LstActionbeans = mmenuProxy.listActionsToolBarparSousmenu(commonparameter.SOUSMENU_listedesdemandes);

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

        List<ActionsBean> LstactionsBeanNotToolBar = mmenuProxy.listActionsNotToolBarparSousmenu(commonparameter.SOUSMENU_listedesdemandes);
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

        model.addAttribute("lstRequesttoparticipateBean", LstRequesttoparticipateBean);
        model.addAttribute("lstmenusbeans", LstMenuBeans);
        model.addAttribute("lstactionsToolbar", LstActionBeansToolbar);
        model.addAttribute("lstactionsNotToolbar", LstActionBeansNotToolbar);
        return "modules/gestionnaire/listedesdemandes/index";
    }
    
    
    @PostMapping(value = "/gestionnaire/valideddemande", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String valideddemande(@RequestParam String idrequesttoparticipate) {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        List<Object> LstObjects = this.mgestionnaireProxy.validatedrequesttoparticipate(Integer.valueOf(idrequesttoparticipate),commonparameter.created_by);
        HashMap<String, String> Hmap = objectMapper.convertValue(LstObjects.get(0), HashMap.class);
        jSONObject.put("results", Hmap.get("code_statut"));
        jSONObject.put("msg", Hmap.get("desc_statut"));
        arrayObj.put(jSONObject);
        return arrayObj.toString();
    }
    
     @PostMapping(value = "/gestionnaire/rejectdemande", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String rejectdemande(@RequestParam String idrequesttoparticipate) {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        List<Object> LstObjects = this.mgestionnaireProxy.rejectrequesttoparticipate(Integer.valueOf(idrequesttoparticipate));
        HashMap<String, String> Hmap = objectMapper.convertValue(LstObjects.get(0), HashMap.class);
        jSONObject.put("results", Hmap.get("code_statut"));
        jSONObject.put("msg", Hmap.get("desc_statut"));
        arrayObj.put(jSONObject);
        return arrayObj.toString();
    }

}