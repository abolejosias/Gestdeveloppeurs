/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmenu.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hp i3
 */
@Entity
@Table(name = "actions", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actions.findAll", query = "SELECT a FROM Actions a")
    , @NamedQuery(name = "Actions.findById", query = "SELECT a FROM Actions a WHERE a.id = :id")
    , @NamedQuery(name = "Actions.findByStrXtype", query = "SELECT a FROM Actions a WHERE a.strXtype = :strXtype")
    , @NamedQuery(name = "Actions.findByStrName", query = "SELECT a FROM Actions a WHERE a.strName = :strName")
    , @NamedQuery(name = "Actions.findByStrId", query = "SELECT a FROM Actions a WHERE a.strId = :strId")
    , @NamedQuery(name = "Actions.findByStrDescription", query = "SELECT a FROM Actions a WHERE a.strDescription = :strDescription")
    , @NamedQuery(name = "Actions.findByStrText", query = "SELECT a FROM Actions a WHERE a.strText = :strText")
    , @NamedQuery(name = "Actions.findByPKey", query = "SELECT a FROM Actions a WHERE a.pKey = :pKey")
    , @NamedQuery(name = "Actions.findByStrClass", query = "SELECT a FROM Actions a WHERE a.strClass = :strClass")
    , @NamedQuery(name = "Actions.findByStrDatatarget", query = "SELECT a FROM Actions a WHERE a.strDatatarget = :strDatatarget")
    , @NamedQuery(name = "Actions.findByStrDatatoggle", query = "SELECT a FROM Actions a WHERE a.strDatatoggle = :strDatatoggle")
    , @NamedQuery(name = "Actions.findByStrIcon", query = "SELECT a FROM Actions a WHERE a.strIcon = :strIcon")
    , @NamedQuery(name = "Actions.findByStrClassLegende", query = "SELECT a FROM Actions a WHERE a.strClassLegende = :strClassLegende")
    , @NamedQuery(name = "Actions.findByStrDataplacement", query = "SELECT a FROM Actions a WHERE a.strDataplacement = :strDataplacement")
    , @NamedQuery(name = "Actions.findByIntPriority", query = "SELECT a FROM Actions a WHERE a.intPriority = :intPriority")
    , @NamedQuery(name = "Actions.findByStrStatus", query = "SELECT a FROM Actions a WHERE a.strStatus = :strStatus")})
public class Actions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "str_xtype", length = 30)
    private String strXtype;
    @Column(name = "str_name", length = 50)
    private String strName;
    @Basic(optional = false)
    @Column(name = "str_id", nullable = false, length = 50)
    private String strId;
    @Column(name = "str_description", length = 200)
    private String strDescription;
    @Basic(optional = false)
    @Column(name = "str_text", nullable = false, length = 50)
    private String strText;
    @Column(name = "p_key", length = 100)
    private String pKey;
    @Column(name = "str_class", length = 100)
    private String strClass;
    @Column(name = "str_datatarget", length = 50)
    private String strDatatarget;
    @Column(name = "str_datatoggle", length = 40)
    private String strDatatoggle;
    @Column(name = "str_icon", length = 200)
    private String strIcon;
    @Column(name = "str_class_legende", length = 150)
    private String strClassLegende;
    @Column(name = "str_dataplacement", length = 40)
    private String strDataplacement;
    @Basic(optional = false)
    @Column(name = "int_priority", nullable = false)
    private short intPriority;
    @Column(name = "str_status", length = 20)
    private String strStatus;

    public Actions() {
    }

    public Actions(Integer id) {
        this.id = id;
    }

    public Actions(Integer id, String strId, String strText, short intPriority) {
        this.id = id;
        this.strId = strId;
        this.strText = strText;
        this.intPriority = intPriority;
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

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actions)) {
            return false;
        }
        Actions other = (Actions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mmenu.models.Actions[ id=" + id + " ]";
    }
    
}
