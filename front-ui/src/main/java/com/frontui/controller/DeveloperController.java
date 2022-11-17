/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontui.beans.ActionsBean;
import com.frontui.beans.DeveloperBean;
import com.frontui.beans.MenuBean;
import com.frontui.beans.ProjectsBean;
import com.frontui.beans.SkillsInOutDeveloperBean;
import com.frontui.beans.SousmenuBean;
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
public class DeveloperController {
 
    
    @Autowired
    Userservice userservice;

    @Autowired
    PrivilegeService privilegeService;
    
    @Autowired
    MgestionnaireProxy mgestionnaireProxy;
    
    @Autowired
    MmenuProxy mmenuProxy;
    
    
    @GetMapping("/gestionnaire/developpeur")
    public String developpeurs(Model model, HttpSession httpSession) {
        List<MenuBean> LstMenuBeans = (List<MenuBean>) httpSession.getAttribute("lstmenusbeans");
        List<DeveloperBean> LstDeveloperBean = new ArrayList<>();
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userservice.findByUsername(userDetails.getUsername());
        LstDeveloperBean = mgestionnaireProxy.listDevelopers();
        List<ActionsBean> LstActionbeans = new ArrayList<>();

        List<ActionsBean> LstActionBeansToolbar = new ArrayList<>();
        List<ActionsBean> LstActionBeansNotToolbar = new ArrayList<>();

        LstActionbeans = mmenuProxy.listActionsToolBarparSousmenu(commonparameter.SOUSMENU_developpeurs);

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

        List<ActionsBean> LstactionsBeanNotToolBar = mmenuProxy.listActionsNotToolBarparSousmenu(commonparameter.SOUSMENU_developpeurs);
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

        model.addAttribute("lstDevelopers", LstDeveloperBean);
        model.addAttribute("lstmenusbeans", LstMenuBeans);
        model.addAttribute("lstactionsToolbar", LstActionBeansToolbar);
        model.addAttribute("lstactionsNotToolbar", LstActionBeansNotToolbar);

        return "modules/gestionnaire/developpeurs/index";
    }
    
    
    
    @PostMapping(value = "/gestionnaire/storedeveloppeurs", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createdeveloppeurs(@RequestParam String first_name, @RequestParam String last_name) {
        System.out.println("com.frontui.controller.DeveloppeurController.createdeveloppeur()**************************"+first_name);
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        ObjectMapper objectMapper = new ObjectMapper();
        User user=userservice.CreateUser(first_name, last_name);
        List<Object> lstObjects = this.mgestionnaireProxy.createdeveloper(first_name, last_name,commonparameter.created_by,user.getId());
        HashMap<String, String> Hmap = objectMapper.convertValue(lstObjects.get(0), HashMap.class);
        jSONObject.put("results", Hmap.get("code_statut"));
        jSONObject.put("msg", Hmap.get("desc_statut"));
        arrayObj.put(jSONObject);
        return arrayObj.toString();
    }
    
    
     //findmodule
    @GetMapping(value = "/gestionnaire/finddeveloppeurs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String finddeveloppeurbyId(@PathVariable String id) {
        JSONObject jSONObject = new JSONObject();
        JSONArray arrayObj = new JSONArray();
        DeveloperBean developerBean = this.mgestionnaireProxy.finddeveloperbyid(Integer.valueOf(id));
        jSONObject.put("id", developerBean.getId());
        jSONObject.put("first_name", developerBean.getFirstName());
        jSONObject.put("last_name", developerBean.getLastName());
        arrayObj.put(jSONObject);
        String result = "{\"recordsTotal\":1,\"results\":" + arrayObj.toString() + "}";
        return result;
    }
    
    
    @PutMapping(value = "/gestionnaire/updatedeveloppeurs", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updatedeveloppeurs(@RequestParam String iddeveloppeurs, @RequestParam String first_name, @RequestParam String last_name) {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        List<Object> lstObjects = this.mgestionnaireProxy.udpatedeveloper(Integer.valueOf(iddeveloppeurs),first_name,last_name,commonparameter.created_by);
        HashMap<String, String> Hmap = objectMapper.convertValue(lstObjects.get(0), HashMap.class);
        jSONObject.put("results", Hmap.get("code_statut"));
        jSONObject.put("msg", Hmap.get("desc_statut"));
        arrayObj.put(jSONObject);
        return arrayObj.toString();
    }
    
     @PostMapping(value = "/gestionnaire/deletedeveloppeurs", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deletedeveloppeurs(@RequestParam String iddeveloppeurs) {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        List<Object> LstObjects = this.mgestionnaireProxy.deletedeveloper(Integer.valueOf(iddeveloppeurs));
        HashMap<String, String> Hmap = objectMapper.convertValue(LstObjects.get(0), HashMap.class);
        jSONObject.put("results", Hmap.get("code_statut"));
        jSONObject.put("msg", Hmap.get("desc_statut"));
        arrayObj.put(jSONObject);
        return arrayObj.toString();
    }
    
     @GetMapping(value = "/gestionnaire/affichercompetence")
    public String affichercompetence(Model model, HttpSession httpSession, @RequestParam String iddeveloper) {
        List<MenuBean> LstMenuBeans = (List<MenuBean>) httpSession.getAttribute("lstmenusbeans");
        List<SkillsInOutDeveloperBean> LstSkillsInOutDeveloperBean = mgestionnaireProxy.listSkillsAutho_UnAutho_To_Developer(Integer.valueOf(iddeveloper));
       
        DeveloperBean developerBean = mgestionnaireProxy.finddeveloperbyid(Integer.valueOf(iddeveloper));
        model.addAttribute("lstmenusbeans", LstMenuBeans);
        model.addAttribute("lstSkillsInOutDeveloperBean", LstSkillsInOutDeveloperBean);
        model.addAttribute("developerBean", developerBean);
        return "modules/gestionnaire/developpeurs/affichercompetence";
    }
    
    @PostMapping(value = "/gestionnaire/createdevelopperskills", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createdevelopperskills(@RequestParam String iddeveloper,@RequestParam String idskills) {
        System.out.println(" --------iddeveloper " + iddeveloper);
        System.out.println(" --------idskills " + idskills);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> LstObjects = mgestionnaireProxy.createdeveloperskills(Integer.valueOf(iddeveloper),Integer.valueOf(idskills),commonparameter.created_by);
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        HashMap<String, String> Hmap = objectMapper.convertValue(LstObjects.get(0), HashMap.class);
        jSONObject.put("results", Hmap.get("code_statut"));
        jSONObject.put("msg", Hmap.get("desc_statut"));
        arrayObj.put(jSONObject);
        System.out.println(" -------- " + arrayObj.toString());
        return arrayObj.toString();
    }
    
    @PostMapping(value = "/gestionnaire/deletedevelopperskills", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deletedevelopperskills(@RequestParam String iddeveloper,@RequestParam String idskills) {
        System.out.println(" --------iddeveloper " + iddeveloper);
        System.out.println(" --------idskills " + idskills);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> LstObjects = mgestionnaireProxy.deletedeveloperskillswithid(Integer.valueOf(iddeveloper), Integer.valueOf(idskills));
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        HashMap<String, String> Hmap = objectMapper.convertValue(LstObjects.get(0), HashMap.class);
        jSONObject.put("results", Hmap.get("code_statut"));
        jSONObject.put("msg", Hmap.get("desc_statut"));
        arrayObj.put(jSONObject);
        System.out.println(" -------- " + arrayObj.toString());
        return arrayObj.toString();
    }
    
    
    //module developpeur
    
      @GetMapping("/developpeurs/welcome")
    public String default_administrationdeveloppeurs(Model model, HttpSession httpSession) {
        List<MenuBean> LstMenuBeans = new ArrayList<>();
        List<SousmenuBean> LstSousmenuBean = new ArrayList<>();

        //recuperer le user connecte 
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userservice.findByUsername(userDetails.getUsername());

        List<MenuBean> lstmenubean = mmenuProxy.listdesmenusparmodule(commonparameter.MOD_Developpeurs);
        lstmenubean.forEach((menuBean) -> {
            if (privilegeService.isAvalaible(menuBean.getPKey(), user)) {
                System.out.println("----------" + menuBean.getPKey() + " privilege accorde ");
                List<SousmenuBean> lstsousmenuBean = mmenuProxy.listsousmenusparmenu(menuBean.getId());
                lstsousmenuBean.forEach((sousmenuBean) -> {
                    if (privilegeService.isAvalaible(sousmenuBean.getPKey(), user)) {
                        System.out.println("----------" + sousmenuBean.getPKey() + " privilege accorde ");
                        LstSousmenuBean.add(sousmenuBean);
                    }
                });
                menuBean.setLstSousmenuBean(LstSousmenuBean);
                LstMenuBeans.add(menuBean);
            }
        });

        if (!LstMenuBeans.isEmpty()) {
            System.out.println("LstMenuBeans " + LstMenuBeans.size() + " list des sousmenus " + LstMenuBeans.get(0).getLstSousmenuBean().size());
        }

        httpSession.setAttribute("lstmenusbeans", LstMenuBeans);
        model.addAttribute("lstmenusbeans", LstMenuBeans);
        return "modules/developpeurs/default";
    }

    
        
    @GetMapping("/developpeurs/listedesprojets")
    public String listedesprojets(Model model, HttpSession httpSession) {
        List<MenuBean> LstMenuBeans = (List<MenuBean>) httpSession.getAttribute("lstmenusbeans");
        List<ProjectsBean> LstprojetBean = new ArrayList<>();
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userservice.findByUsername(userDetails.getUsername());
        DeveloperBean developerBean = mgestionnaireProxy.finddeveloperbyiduser(user.getId());
        System.out.println("developerBean ****************  "+developerBean.getLastName());
        LstprojetBean = mgestionnaireProxy.listotherprojects(developerBean.getId());
        
        List<ActionsBean> LstActionbeans = new ArrayList<>();

        List<ActionsBean> LstActionBeansToolbar = new ArrayList<>();
        List<ActionsBean> LstActionBeansNotToolbar = new ArrayList<>();

        LstActionbeans = mmenuProxy.listActionsToolBarparSousmenu(commonparameter.SOUSMENU_listedesprojets);

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

        List<ActionsBean> LstactionsBeanNotToolBar = mmenuProxy.listActionsNotToolBarparSousmenu(commonparameter.SOUSMENU_listedesprojets);
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

        return "modules/developpeurs/listedesprojets/index";
    }
    
    
    @GetMapping("/developpeurs/mesprojets")
    public String mesprojets(Model model, HttpSession httpSession) {
        List<MenuBean> LstMenuBeans = (List<MenuBean>) httpSession.getAttribute("lstmenusbeans");
        List<ProjectsBean> LstprojetBean = new ArrayList<>();
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userservice.findByUsername(userDetails.getUsername());
        DeveloperBean developerBean = mgestionnaireProxy.finddeveloperbyiduser(user.getId());
        LstprojetBean = mgestionnaireProxy.listmyprojects(developerBean.getId());
        List<ActionsBean> LstActionbeans = new ArrayList<>();

        List<ActionsBean> LstActionBeansToolbar = new ArrayList<>();
        List<ActionsBean> LstActionBeansNotToolbar = new ArrayList<>();

        LstActionbeans = mmenuProxy.listActionsToolBarparSousmenu(commonparameter.SOUSMENU_mesprojets);

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

        List<ActionsBean> LstactionsBeanNotToolBar = mmenuProxy.listActionsNotToolBarparSousmenu(commonparameter.SOUSMENU_mesprojets);
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

        return "modules/developpeurs/mesprojets/index";
    }
    
    
    @PostMapping(value = "/developpeurs/senddemande", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String senddemande(@RequestParam String idprojets) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userservice.findByUsername(userDetails.getUsername());
        DeveloperBean developerBean = mgestionnaireProxy.finddeveloperbyiduser(user.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray arrayObj = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        System.err.println("*********************"+developerBean.getId()+" *******idprojets***** "+idprojets);
        List<Object> LstObjects = this.mgestionnaireProxy.createrequesttoparticipate(Integer.valueOf(idprojets),developerBean.getId(),commonparameter.created_by);
        HashMap<String, String> Hmap = objectMapper.convertValue(LstObjects.get(0), HashMap.class);
        jSONObject.put("results", Hmap.get("code_statut"));
        jSONObject.put("msg", Hmap.get("desc_statut"));
        arrayObj.put(jSONObject);
        return arrayObj.toString();
    }

}
