/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.beans;

import java.util.List;

/**
 *
 * @author hp i3
 */
public class MenuBean {


    private Integer id;
    private String strValue;
    private String strDescription;
    private Integer intPriority;
    private String strClass;
    private String strHref;
    private String strStatus;
    private String pKey;
    private ModuleBean moduleBean;

    public ModuleBean getModuleBean() {
        return moduleBean;
    }

    public void setModuleBean(ModuleBean moduleBean) {
        this.moduleBean = moduleBean;
    }
   
    
    private List<SousmenuBean> lstSousmenuBean;

    public MenuBean() {
    }

    public MenuBean(Integer id) {
        this.id = id;
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

    public Integer getIntPriority() {
        return intPriority;
    }

    public void setIntPriority(Integer intPriority) {
        this.intPriority = intPriority;
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

    public List<SousmenuBean> getLstSousmenuBean() {
        return lstSousmenuBean;
    }

    public void setLstSousmenuBean(List<SousmenuBean> lstSousmenuBean) {
        this.lstSousmenuBean = lstSousmenuBean;
    }

    public String getStrClass() {
        return strClass;
    }

    public void setStrClass(String strClass) {
        this.strClass = strClass;
    }

    public String getStrHref() {
        return strHref;
    }

    public void setStrHref(String strHref) {
        this.strHref = strHref;
    }
    
  
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuBean)) {
            return false;
        }
        MenuBean other = (MenuBean) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

   
}
