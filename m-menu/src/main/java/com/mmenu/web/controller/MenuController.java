/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmenu.web.controller;

import com.mmenu.models.Actions;
import com.mmenu.models.Menu;
import com.mmenu.models.Module;
import com.mmenu.models.SousMenu;
import com.mmenu.proxies.MmoduleProxy;
import com.mmenu.service.MenuService;
import com.mmenu.toolkits.commonparameter;
import com.mmenu.web.exception.ActionNotFoundException;
import com.mmenu.web.exception.MessageNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hp i3
 */
@RestController
public class MenuController {

    @Autowired
    MenuService menuService;

    @Autowired
    MmoduleProxy mmoduleProxy;

    @GetMapping(value = "/menu/{idmodule}")
    public List<Menu> listdesmenusparmodule(@PathVariable int idmodule) {
        List<Menu> listmenu = menuService.getallMenuByidmodule(idmodule, commonparameter.statut_enable);
        if (listmenu.isEmpty()) {
            throw new ActionNotFoundException(commonparameter.LIST_EMPHTY);
        }
        return listmenu;
    }

    @GetMapping(value = "/sousmenu/{idmenu}")
    public List<SousMenu> listsousmenusparmenu(@PathVariable int idmenu) {
        List<SousMenu> Listsousmenu = menuService.getallSousMenuByidmenu(idmenu, commonparameter.statut_enable);
        if (Listsousmenu.isEmpty()) {
            throw new ActionNotFoundException(commonparameter.LIST_EMPHTY);
        }
        return Listsousmenu;
    }

    @GetMapping(value = "/actionstoolbar/{idsousmenu}")
    public List<Actions> listActionsToolBarparSousmenu(@PathVariable int idsousmenu) {
        List<Actions> listActions = menuService.getallActionsToolbar(idsousmenu, commonparameter.statut_enable);
        if (listActions.isEmpty()) {
            return new ArrayList<Actions>(); //throw  new ActionNotFoundException(commonparameter.LIST_EMPHTY);
        }
        return listActions;
    }

    @GetMapping(value = "/actionsnottoolbar/{idsousmenu}")
    public List<Actions> listActionsNotToolBarparSousmenu(@PathVariable int idsousmenu) {
        List<Actions> listActions = menuService.getallActionsNotToolbar(idsousmenu, commonparameter.statut_enable);
        if (listActions.isEmpty()) {
            return new ArrayList<Actions>();//throw  new ActionNotFoundException(commonparameter.LIST_EMPHTY);
        }
        return listActions;
    }

    @PostMapping(value = "/createmenu")
    public List<Object> createmenu(@RequestParam String str_value, @RequestParam String str_description, @RequestParam Integer int_priority, @RequestParam Integer idmodule) {
        System.err.println("id module ********************************************" + idmodule);
        Module Omodule = mmoduleProxy.findmodule(idmodule);
        menuService.createMenu(str_value, str_description, int_priority, Omodule);
        return MessageNotFoundException.getMessageException(menuService.getMessage(), menuService.getDetailmessage());
    }

    @PostMapping(value = "/deletemenu")
    public List<Object> deletemenu(@RequestParam Integer idmenu) {
        menuService.deleteMenu(idmenu);
        return MessageNotFoundException.getMessageException(menuService.getMessage(), menuService.getDetailmessage());
    }

    @GetMapping(value = "/listmenus")
    public List<Menu> listMenus() {
        List<Menu> listMenu = menuService.getallMenu(commonparameter.statut_enable);
        return listMenu;
    }

    @GetMapping(value = "/findmodulebymenuid/{id}")
    public Module findmodulebymenuid(@PathVariable Integer id) {
        return menuService.getModulewithmenuId(id);
    }

    @GetMapping(value = "/getallmodules")
    public List<Module> getallmodules() {
        List<Module> listmodules = new ArrayList<>();
        try {
            listmodules = mmoduleProxy.listedesmodules();
        } catch (Exception e) {
            menuService.buildErrorTraceMessage(e.getMessage());
        }
        return listmodules;
    }

    @GetMapping(value = "/listsousmenus")
    public List<SousMenu> listsousmenus() { 
           return menuService.getallSousMenus(commonparameter.statut_enable);
    }
    
     @GetMapping(value = "/findmenu/{id}")
    public Menu findmenubyid(@PathVariable Integer id) {
        return menuService.findmenu(id);
    }

    @PostMapping(value = "/createsousmenu")
    public List<Object> createsousmenu(@RequestParam String str_value, @RequestParam String str_description, @RequestParam Integer int_priority, @RequestParam Integer idmenu) {
        System.err.println("id menu ********************************************" + idmenu);
        menuService.createSousMenu(str_value, str_description, int_priority, idmenu);
        return MessageNotFoundException.getMessageException(menuService.getMessage(), menuService.getDetailmessage());
    }
    
      @PostMapping(value = "/deletesousmenu")
    public List<Object> deletesousmenu(@RequestParam Integer idsousmenu) {
        menuService.deleteSousmenu(idsousmenu);
        return MessageNotFoundException.getMessageException(menuService.getMessage(), menuService.getDetailmessage());
    }
    
}
