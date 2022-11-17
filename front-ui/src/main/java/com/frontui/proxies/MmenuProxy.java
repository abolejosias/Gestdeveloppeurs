/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.proxies;

import com.frontui.beans.ActionsBean;
import com.frontui.beans.MenuBean;
import com.frontui.beans.ModuleBean;
import com.frontui.beans.SousmenuBean;
import java.util.List;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author hp i3
 */
@FeignClient(name = "m-menu", decode404 = true)
@RibbonClient(name = "m-menu")
public interface MmenuProxy {

    @GetMapping(value = "/menu/{idmodule}")
    public List<MenuBean> listdesmenusparmodule(@PathVariable int idmodule);

    @GetMapping(value = "/sousmenu/{idmenu}")
    public List<SousmenuBean> listsousmenusparmenu(@PathVariable int idmenu);

    @GetMapping(value = "/actionstoolbar/{idsousmenu}")
    public List<ActionsBean> listActionsToolBarparSousmenu(@PathVariable int idsousmenu);

    @GetMapping(value = "/actionsnottoolbar/{idsousmenu}")
    public List<ActionsBean> listActionsNotToolBarparSousmenu(@PathVariable int idsousmenu);

    @PostMapping(value = "/createmenu")
    public List<Object> createmenu(@RequestParam String str_value, @RequestParam String str_description, @RequestParam Integer int_priority, @RequestParam Integer idmodule);

    @PostMapping(value = "/deletemenu")
    public List<Object> deletemenu(@RequestParam Integer idmenu);
    
    
    @GetMapping(value = "/listmenus")
    public List<MenuBean> listMenus();
    
    @GetMapping(value = "/findmodulebymenuid/{id}")
    public ModuleBean findmodulebymenuid(@PathVariable Integer id);
    
    @GetMapping(value = "/listsousmenus")
    public List<SousmenuBean> listsousmenus();
    
    @GetMapping(value = "/findmenu/{id}")
    public MenuBean findmenubyid(@PathVariable Integer id);
    
    @PostMapping(value = "/createsousmenu")
    public List<Object> createsousmenu(@RequestParam String str_value, @RequestParam String str_description, @RequestParam Integer int_priority, @RequestParam Integer idmenu);
    
    @PostMapping(value = "/deletesousmenu")
    public List<Object> deletesousmenu(@RequestParam Integer idsousmenu);
}
