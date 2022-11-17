/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.controller;


import com.frontui.beans.MenuBean;
import com.frontui.beans.ModuleBean;
import com.frontui.beans.SousmenuBean;
import com.frontui.model.User;
import com.frontui.proxies.MmenuProxy;
import com.frontui.proxies.MmoduleProxy;
import com.frontui.service.PrivilegeService;
import com.frontui.service.Userservice;
import com.frontui.toolkits.commonparameter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author hp i3
 */
@Controller
public class FrontController {

    @Autowired
    Userservice userservice;

    @Autowired
    PrivilegeService privilegeService;

    @Autowired
    MmoduleProxy mmoduleProxy;

    @Autowired
    MmenuProxy mmenuProxy;

    @RequestMapping("/")
    public String Accueil(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String Login(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/home";
    }
    
    @GetMapping("/logout")
    public String logout(Model model) {
     SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false); 
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<ModuleBean> LstModuleBean = new ArrayList<>();
        //ObjectMapper objectMapper = new ObjectMapper();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userservice.findByUsername(userDetails.getUsername());
        List<ModuleBean> lstModuleBeans = mmoduleProxy.listedesmodules();
        if (!lstModuleBeans.isEmpty()) {
            lstModuleBeans.forEach((moduleBean) -> {
                if (privilegeService.isAvalaible(moduleBean.getPKey(), user)) {
                    System.out.println("message ---- privilege accorde ");
                    LstModuleBean.add(moduleBean);
                }
            });
        }
        model.addAttribute("lstmodules", LstModuleBean);
        return "home";
    }

    @GetMapping("/administration")
    public String administration(Model model, HttpSession httpSession) {

        List<MenuBean> LstMenuBeans = (List<MenuBean>) httpSession.getAttribute("lstmenusbeans");
        System.out.println("Session LstMenuBeans " + LstMenuBeans.size());
        model.addAttribute("lstmenusbeans", LstMenuBeans);
        return "modules/administration/default";
    }

    @GetMapping("/administration/welcome")
    public String default_administration(Model model, HttpSession httpSession) {
        List<MenuBean> LstMenuBeans = new ArrayList<>();
        List<SousmenuBean> LstSousmenuBean = new ArrayList<>();

        //recuperer le user connecte 
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userservice.findByUsername(userDetails.getUsername());

        List<MenuBean> lstmenubean = mmenuProxy.listdesmenusparmodule(commonparameter.MOD_Administration);
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
        return "modules/administration/default";
    }
    
    @GetMapping("/gestionnaire/welcome")
    public String default_gestionnaire(Model model, HttpSession httpSession) {
        List<MenuBean> LstMenuBeans = new ArrayList<>();
        List<SousmenuBean> LstSousmenuBean = new ArrayList<>();

        //recuperer le user connecte 
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userservice.findByUsername(userDetails.getUsername());

        List<MenuBean> lstmenubean = mmenuProxy.listdesmenusparmodule(commonparameter.MOD_Gestionnaire);
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
        return "modules/gestionnaire/default";
    }

}
