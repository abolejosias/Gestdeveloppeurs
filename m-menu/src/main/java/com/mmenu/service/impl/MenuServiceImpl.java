/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmenu.service.impl;

import com.mmenu.models.Actions;
import com.mmenu.models.Menu;
import com.mmenu.models.Module;
import com.mmenu.models.SousMenu;
import com.mmenu.models.SousMenuActions;
import com.mmenu.repository.MenuRepository;
import com.mmenu.repository.SousmenuRepository;
import com.mmenu.repository.SousmenuactionsRepository;
import com.mmenu.service.MenuService;
import com.mmenu.toolkits.commonparameter;
import com.mmenu.toolkits.utils.logger;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hp i3
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    SousmenuRepository sousmenuRepository;

    @Autowired
    SousmenuactionsRepository sousmenuactionsRepository;

    private String str_message;
    private String str_Detailmessage;

    @Override
    public List<Menu> getallMenuByidmodule(Integer idmodule, String strStatus) {
        return menuRepository.findbyidmodule(idmodule, strStatus);
    }

    @Override
    public List<SousMenu> getallSousMenuByidmenu(Integer idmenu, String strStatut) {
        return sousmenuRepository.findbyIdmenu(idmenu, strStatut);
    }

    @Override
    public List<Actions> getallActionsToolbar(Integer idsousmenu, String strStatut) {
        List<Actions> lstActions = new ArrayList<>();
        try {
            List<SousMenuActions> LstSousMenuActions = sousmenuactionsRepository.getallSousmenuactionsByidMenu(idsousmenu, strStatut);
            LstSousMenuActions.forEach((OsousMenuactions) -> {
                if (OsousMenuactions.getIdactions().getStrXtype().equals(commonparameter.BUTTON_TOOLBAR)) {
                    lstActions.add(OsousMenuactions.getIdactions());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            this.buildErrorTraceMessage(e.getMessage());
        }
        return lstActions;
    }

    @Override
    public List<Actions> getallActionsNotToolbar(Integer idsousmenu, String strStatut) {
        List<Actions> lstActions = new ArrayList<>();
        try {
            List<SousMenuActions> LstSousMenuActions = sousmenuactionsRepository.getallSousmenuactionsByidMenu(idsousmenu, strStatut);
            LstSousMenuActions.forEach((OsousMenuactions) -> {
                if (OsousMenuactions.getIdactions().getStrXtype().equals(commonparameter.BUTTON)) {
                    lstActions.add(OsousMenuactions.getIdactions());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            this.buildErrorTraceMessage(e.getMessage());
        }
        return lstActions;
    }

    @Override
    @Transactional
    public Boolean createMenu(String str_value, String str_description, Integer int_priority, Module oModule) {
        Boolean resultat = false;
        try {
            if (oModule != null) {
                String str_Key = this.getstrKey(str_value);
                Menu menu = new Menu();
                menu.setIdmodule(oModule);
                menu.setIntPriority(int_priority);
                menu.setStrValue(str_value);
                menu.setStrDescription(str_description);
                menu.setStrClass(commonparameter.str_iconlayers);
                menu.setPKey(commonparameter.str_prefixmenu + str_Key);
                menu.setStrHref("#" + str_Key.toLowerCase());
                menu.setStrStatus(commonparameter.statut_enable);
                menuRepository.save(menu);
                this.buildSuccesTraceMessage(commonparameter.SUCCES_CREATION);
            }
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return resultat;
    }

    @Override
    public String getMessage() {
        return str_message;
    }

    @Override
    public void setMessage(String message) {
        this.str_message = message;
    }

    @Override
    public String getDetailmessage() {
        return str_Detailmessage;
    }

    @Override
    public void setDetailmessage(String Detailmessage) {
        this.str_Detailmessage = Detailmessage;
    }

    @Override
    public void buildTraceMessage(String Message, String DetailMessage) {
        this.setDetailmessage(DetailMessage);
        this.setMessage(Message);
    }

    @Override
    public void buildErrorTraceMessage(String DetailMessage) {
        this.setDetailmessage(DetailMessage);
        this.setMessage(commonparameter.PROCESS_FAILED);
        new logger().OCategory.error(DetailMessage);
    }

    @Override
    public void buildErrorTraceMessage(String DetailMessage, String ErrorSystem) {
        this.setDetailmessage(DetailMessage + ".ERREUR  SYS:[ " + ErrorSystem + "]");
        setMessage(commonparameter.PROCESS_FAILED);
        new logger().OCategory.error(DetailMessage);
    }

    @Override
    public void buildSuccesTraceMessage(String DetailMessage) {
        this.setDetailmessage(DetailMessage);
        setMessage(commonparameter.PROCESS_SUCCESS);
        new logger().OCategory.info(DetailMessage);
    }

    @Override
    public void prinntTraceInfo(Object message) {
        new logger().OCategory.info(message);
    }

    @Override
    public void prinntTraceDebug(Object message) {
        new logger().OCategory.debug(message);
    }

    @Override
    public void prinntTraceError(Object message) {
        new logger().OCategory.error(message);

    }

    @Override
    public String getstrKey(String str_Value) {
        String str_link = "";
        String[] tabname = str_Value.split(" ");
        for (String tabname1 : tabname) {
            str_link = str_link + tabname1;
        }
        return str_link.toUpperCase();
    }

    @Override
    @Transactional
    public Boolean updateMenu(Integer idmenu, String str_value, String str_description, Integer int_priority) {
        Boolean resultat = false;
        try {
            String str_Key = this.getstrKey(str_value);
            Optional<Menu> optionmenu = menuRepository.findById(idmenu);
            if (optionmenu.get() != null) {
                Menu menu = optionmenu.get();
                menu.setIntPriority(int_priority);
                menu.setStrValue(str_value);
                menu.setStrDescription(str_description);
                menu.setPKey(commonparameter.str_prefixmenu + str_Key);
                menu.setStrHref("#" + str_Key.toLowerCase());
                menu.setStrStatus(commonparameter.statut_enable);
                menuRepository.save(menu);
                this.buildSuccesTraceMessage(commonparameter.SUCCES_UPDATE);
            }
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return resultat;
    }

    @Override
    @Transactional
    public void deleteMenu(Integer idmenu) {
        try {
            Optional<Menu> optionmenu = menuRepository.findById(idmenu);
            if (optionmenu.get() != null) {
                menuRepository.delete(optionmenu.get());
                this.buildSuccesTraceMessage(commonparameter.SUCCES_UPDATE);
            }
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
    }

    @Override
    public List<Menu> getallMenu(String strStatus) {
        List<Menu> lstmenus = new ArrayList<>();
        try {
            lstmenus = menuRepository.getallmenu(strStatus);
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return lstmenus;
    }

    @Override
    public Module getModulewithmenuId(Integer idmenu) {
        Module module = null;
        try {
            Optional<Menu> optionalmenu = menuRepository.findById(idmenu);
            if (optionalmenu.isPresent()) {
                module = optionalmenu.get().getIdmodule();
            }
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return module;
    }

    @Override
    public List<SousMenu> getallSousMenus(String strStatus) {
        List<SousMenu> LstSousMenus = new ArrayList<>();
        try {
            LstSousMenus = this.sousmenuRepository.getallsousmenu(strStatus);
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return LstSousMenus;
    }

    @Override
    public Menu findmenu(Integer idmenu) {
        Menu menu = null;
        try {
            menu = this.menuRepository.findById(idmenu).get();
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return menu;
    }

    @Override
    public void deleteSousmenu(Integer idsousmenu) {
        try {
            this.sousmenuRepository.deleteById(idsousmenu);
             this.buildSuccesTraceMessage(commonparameter.SUCCES_UPDATE);
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }

    }

    @Override
    @Transactional
    public Boolean createSousMenu(String str_value, String str_description, Integer int_priority, Integer idmenu) {
        Boolean resultat = false;
        try {
            if (this.CheckifSousenuExist(str_value, idmenu)) {
                String str_Key = this.getstrKey(str_value);
                SousMenu oSousMenu = new SousMenu();
                oSousMenu.setIdmenu(idmenu);
                oSousMenu.setIntPriority(int_priority);
                oSousMenu.setStrValue(str_Key.toLowerCase());
                oSousMenu.setStrComposant(str_Key.toLowerCase());
                oSousMenu.setStrDescription(str_description);
                oSousMenu.setPKey(commonparameter.str_prefixsousmenu + str_Key);
                oSousMenu.setStrUrl(str_Key.toLowerCase());
                oSousMenu.setStrStatus(commonparameter.statut_enable);
                sousmenuRepository.save(oSousMenu);
                this.buildSuccesTraceMessage(commonparameter.SUCCES_CREATION);
            }else{
                this.buildErrorTraceMessage(commonparameter.EXIST_DATA);
            }
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return resultat;
    }

    @Override
    public Boolean CheckifSousenuExist(String str_value, Integer idmenu) {
        Boolean resultat = false;
        try {
            List<SousMenu> LstSousMenus = this.sousmenuRepository.findsousmenu(str_value, idmenu);
            if (LstSousMenus.isEmpty()) {
                resultat = true;
            }
        } catch (Exception e) {
            this.buildErrorTraceMessage(e.getMessage());
        }
        return resultat;
    }

}
