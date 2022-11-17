/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.beans;



/**
 *
 * @author hp i3
 */

public class ActionsBean {

    private Integer id;
    private String strXtype;
    private String strName;
    private String strId;
    private String strDescription;
    private String strText;
    private String pKey;
    private String strClass;
    private String strDatatarget;
    private String strDatatoggle;
    private String strIcon;
    private String strClassLegende;
    private String strDataplacement;
    private short intPriority;
    private String strStatus;

    public ActionsBean() {
    }

    public ActionsBean(Integer id) {
        this.id = id;
    }

   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrXtype() {
        return strXtype;
    }

    public void setStrXtype(String strXtype) {
        this.strXtype = strXtype;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }

    public String getStrText() {
        return strText;
    }

    public void setStrText(String strText) {
        this.strText = strText;
    }

    public String getPKey() {
        return pKey;
    }

    public void setPKey(String pKey) {
        this.pKey = pKey;
    }

    public String getStrClass() {
        return strClass;
    }

    public void setStrClass(String strClass) {
        this.strClass = strClass;
    }

    public String getStrDatatarget() {
        return strDatatarget;
    }

    public void setStrDatatarget(String strDatatarget) {
        this.strDatatarget = strDatatarget;
    }

    public String getStrDatatoggle() {
        return strDatatoggle;
    }

    public void setStrDatatoggle(String strDatatoggle) {
        this.strDatatoggle = strDatatoggle;
    }

    public String getStrIcon() {
        return strIcon;
    }

    public void setStrIcon(String strIcon) {
        this.strIcon = strIcon;
    }

    public String getStrClassLegende() {
        return strClassLegende;
    }

    public void setStrClassLegende(String strClassLegende) {
        this.strClassLegende = strClassLegende;
    }

    public String getStrDataplacement() {
        return strDataplacement;
    }

    public void setStrDataplacement(String strDataplacement) {
        this.strDataplacement = strDataplacement;
    }

    public short getIntPriority() {
        return intPriority;
    }

    public void setIntPriority(short intPriority) {
        this.intPriority = intPriority;
    }

    public String getStrStatus() {
        return strStatus;
    }

    public void setStrStatus(String strStatus) {
        this.strStatus = strStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

 
}
