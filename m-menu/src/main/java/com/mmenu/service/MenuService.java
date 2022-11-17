/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmenu.service;

import com.mmenu.models.Actions;
import com.mmenu.models.Menu;
import com.mmenu.models.Module;
import com.mmenu.models.SousMenu;
import com.mmenu.toolkits.Iservicemsg;
import java.util.List;

/**
 *
 * @author hp i3
 */
public interface MenuService extends Iservicemsg{
    
    List<Menu> getallMenuByidmodule(Integer idmodule,String strStatus);
    List<SousMenu> getallSousMenuByidmenu(Integer idmenu,String strStatut);
    List<Actions> getallActionsToolbar(Integer idsousmenu ,String strStatut);
    List<Actions> getallActionsNotToolbar(Integer idsousmenu ,String strStatut);
    Boolean createMenu(String str_value,String str_description,Integer int_priority,Module Omodule);
    String getstrKey(String str_Value);
    Boolean updateMenu(Integer idmenu,String str_value,String str_description,Integer int_priority);
    void deleteMenu(Integer idmenu);
    List<Menu> getallMenu(String strStatus);
    Module getModulewithmenuId(Integer idmenu);
    List<SousMenu> getallSousMenus(String strStatus);
    Menu findmenu(Integer idmenu);
    void deleteSousmenu(Integer idsousmenu);
    Boolean createSousMenu(String str_value,String str_description,Integer int_priority,Integer idmenu);
    Boolean CheckifSousenuExist(String str_value,Integer idmenu);

}
