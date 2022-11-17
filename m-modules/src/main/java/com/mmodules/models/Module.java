/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmodules.models;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hp i3
 */
@Entity
@Table(name = "module", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Module.findAll", query = "SELECT m FROM Module m")
    , @NamedQuery(name = "Module.findById", query = "SELECT m FROM Module m WHERE m.id = :id")
    , @NamedQuery(name = "Module.findByStrValue", query = "SELECT m FROM Module m WHERE m.strValue = :strValue")
    , @NamedQuery(name = "Module.findByStrDescription", query = "SELECT m FROM Module m WHERE m.strDescription = :strDescription")
    , @NamedQuery(name = "Module.findByIntPriority", query = "SELECT m FROM Module m WHERE m.intPriority = :intPriority")
    , @NamedQuery(name = "Module.findByStrStatut", query = "SELECT m FROM Module m WHERE m.strStatut = :strStatut")
    , @NamedQuery(name = "Module.findByPKey", query = "SELECT m FROM Module m WHERE m.pKey = :pKey")
    , @NamedQuery(name = "Module.findByStrLink", query = "SELECT m FROM Module m WHERE m.strLink = :strLink")
    , @NamedQuery(name = "Module.findByStrIcone", query = "SELECT m FROM Module m WHERE m.strIcone = :strIcone")
    , @NamedQuery(name = "Module.findByStrIconeHover", query = "SELECT m FROM Module m WHERE m.strIconeHover = :strIconeHover")
    , @NamedQuery(name = "Module.findByStrIconeOut", query = "SELECT m FROM Module m WHERE m.strIconeOut = :strIconeOut")
    , @NamedQuery(name = "Module.findByStrLinkDefault", query = "SELECT m FROM Module m WHERE m.strLinkDefault = :strLinkDefault")})
public class Module implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 30)
    @Column(name = "str_value", length = 30)
    private String strValue;
    @Size(max = 200)
    @Column(name = "str_description", length = 200)
    private String strDescription;
    @Column(name = "int_priority")
    private Integer intPriority;
    @Size(max = 20)
    @Column(name = "str_statut", length = 20)
    private String strStatut;
    @Size(max = 100)
    @Column(name = "p_key", length = 100)
    private String pKey;
    @Size(max = 100)
    @Column(name = "str_link", length = 100)
    private String strLink;
    @Size(max = 20)
    @Column(name = "str_icone", length = 20)
    private String strIcone;
    @Size(max = 20)
    @Column(name = "str_icone_hover", length = 20)
    private String strIconeHover;
    @Size(max = 20)
    @Column(name = "str_icone_out", length = 20)
    private String strIconeOut;
    @Size(max = 100)
    @Column(name = "str_link_default", length = 100)
    private String strLinkDefault;

    public Module() {
    }

    public Module(Integer id) {
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

    public String getStrStatut() {
        return strStatut;
    }

    public void setStrStatut(String strStatut) {
        this.strStatut = strStatut;
    }

    public String getPKey() {
        return pKey;
    }

    public void setPKey(String pKey) {
        this.pKey = pKey;
    }

    public String getStrLink() {
        return strLink;
    }

    public void setStrLink(String strLink) {
        this.strLink = strLink;
    }

    public String getStrIcone() {
        return strIcone;
    }

    public void setStrIcone(String strIcone) {
        this.strIcone = strIcone;
    }

    public String getStrIconeHover() {
        return strIconeHover;
    }

    public void setStrIconeHover(String strIconeHover) {
        this.strIconeHover = strIconeHover;
    }

    public String getStrIconeOut() {
        return strIconeOut;
    }

    public void setStrIconeOut(String strIconeOut) {
        this.strIconeOut = strIconeOut;
    }

    public String getStrLinkDefault() {
        return strLinkDefault;
    }

    public void setStrLinkDefault(String strLinkDefault) {
        this.strLinkDefault = strLinkDefault;
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
        if (!(object instanceof Module)) {
            return false;
        }
        Module other = (Module) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mmodules.models.Module[ id=" + id + " ]";
    }
    
}
