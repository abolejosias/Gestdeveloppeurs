/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmenu.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "menu", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m")
    , @NamedQuery(name = "Menu.findById", query = "SELECT m FROM Menu m WHERE m.id = :id")
    , @NamedQuery(name = "Menu.findByStrValue", query = "SELECT m FROM Menu m WHERE m.strValue = :strValue")
    , @NamedQuery(name = "Menu.findByStrDescription", query = "SELECT m FROM Menu m WHERE m.strDescription = :strDescription")
    , @NamedQuery(name = "Menu.findByIntPriority", query = "SELECT m FROM Menu m WHERE m.intPriority = :intPriority")
    , @NamedQuery(name = "Menu.findByStrStatus", query = "SELECT m FROM Menu m WHERE m.strStatus = :strStatus")
    , @NamedQuery(name = "Menu.findByPKey", query = "SELECT m FROM Menu m WHERE m.pKey = :pKey")})
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "str_value", length = 30)
    private String strValue;
    @Column(name = "str_description", length = 200)
    private String strDescription;
    @Column(name = "int_priority")
    private Integer intPriority;
    
    @Column(name = "str_class", length = 100)
    private String strClass;
     
    @Column(name = "str_href", length = 100)
    private String strHref;
   
    @Column(name = "str_status", length = 20)
    private String strStatus;
    @Column(name = "p_key", length = 100)
    private String pKey;
    @JoinColumn(name = "idmodule", referencedColumnName = "id")
    
    @ManyToOne
    @JsonIgnore
    private Module idmodule;

    public Menu() {
    }

    public Menu(Integer id) {
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

    public Module getIdmodule() {
        return idmodule;
    }

    public void setIdmodule(Module idmodule) {
        this.idmodule = idmodule;
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
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mmenu.models.Menu[ id=" + id + " ]";
    }
    
}
