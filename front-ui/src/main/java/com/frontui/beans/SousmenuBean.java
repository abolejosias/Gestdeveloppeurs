/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.beans;

import java.util.Collection;

/**
 *
 * @author hp i3
 */
public class SousmenuBean {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String strValue;
    private String strDescription;
    private String strComposant;
    private Integer idmenu;
    private Integer intPriority;
    private String strUrl;
    private String strStatus;
    private String pKey;
    private MenuBean menuBean;
    private Collection<SousMenuActionsBean> sousMenuActionsCollection;

   
    public SousmenuBean() {
    }

    public SousmenuBean(Integer id) {
        this.id = id;
    }

    public MenuBean getMenuBean() {
        return menuBean;
    }

    public void setMenuBean(MenuBean menuBean) {
        this.menuBean = menuBean;
    }
    
    

    public SousmenuBean(Integer id, String strComposant) {
        this.id = id;
        this.strComposant = strComposant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }

    public String getStrComposant() {
        return strComposant;
    }

    public void setStrComposant(String strComposant) {
        this.strComposant = strComposant;
    }

    public Integer getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public Integer getIntPriority() {
        return intPriority;
    }

    public void setIntPriority(Integer intPriority) {
        this.intPriority = intPriority;
    }

    public String getStrUrl() {
        return strUrl;
    }

    public void setStrUrl(String strUrl) {
        this.strUrl = strUrl;
    }

    public String getStrStatus() {
        return strStatus;
    }

    public void setStrStatus(String strStatus) {
        this.strStatus = strStatus;
    }

    public String getPKey() {
        return pKey;
    }

    public void setPKey(String pKey) {
        this.pKey = pKey;
    }

  
 public Collection<SousMenuActionsBean> getSousMenuActionsCollection() {
        return sousMenuActionsCollection;
    }

    public void setSousMenuActionsCollection(Collection<SousMenuActionsBean> sousMenuActionsCollection) {
        this.sousMenuActionsCollection = sousMenuActionsCollection;
    }
   
 

}
