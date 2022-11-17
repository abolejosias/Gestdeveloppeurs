/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmenu.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp i3
 */
@Entity
@Table(name = "sous_menu", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SousMenu.findAll", query = "SELECT s FROM SousMenu s")
    , @NamedQuery(name = "SousMenu.findById", query = "SELECT s FROM SousMenu s WHERE s.id = :id")
    , @NamedQuery(name = "SousMenu.findByStrValue", query = "SELECT s FROM SousMenu s WHERE s.strValue = :strValue")
    , @NamedQuery(name = "SousMenu.findByStrDescription", query = "SELECT s FROM SousMenu s WHERE s.strDescription = :strDescription")
    , @NamedQuery(name = "SousMenu.findByStrComposant", query = "SELECT s FROM SousMenu s WHERE s.strComposant = :strComposant")
    , @NamedQuery(name = "SousMenu.findByIdmenu", query = "SELECT s FROM SousMenu s WHERE s.idmenu = :idmenu")
    , @NamedQuery(name = "SousMenu.findByIntPriority", query = "SELECT s FROM SousMenu s WHERE s.intPriority = :intPriority")
    , @NamedQuery(name = "SousMenu.findByStrUrl", query = "SELECT s FROM SousMenu s WHERE s.strUrl = :strUrl")
    , @NamedQuery(name = "SousMenu.findByStrStatus", query = "SELECT s FROM SousMenu s WHERE s.strStatus = :strStatus")
    , @NamedQuery(name = "SousMenu.findByPKey", query = "SELECT s FROM SousMenu s WHERE s.pKey = :pKey")})
public class SousMenu implements Serializable {

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
    @Basic(optional = false)
    @Column(name = "str_composant", nullable = false, length = 40)
    private String strComposant;
    @Column(name = "idmenu")
    private Integer idmenu;
    @Column(name = "int_priority")
    private Integer intPriority;
    @Column(name = "str_url", length = 50)
    private String strUrl;
    @Column(name = "str_status", length = 20)
    private String strStatus;
    @Column(name = "p_key", length = 100)
    private String pKey;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsousmenu")
    @JsonIgnore
    private Collection<SousMenuActions> sousMenuActionsCollection;

    public SousMenu() {
    }

    public SousMenu(Integer id) {
        this.id = id;
    }

    public SousMenu(Integer id, String strComposant) {
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

    @XmlTransient
    public Collection<SousMenuActions> getSousMenuActionsCollection() {
        return sousMenuActionsCollection;
    }

    public void setSousMenuActionsCollection(Collection<SousMenuActions> sousMenuActionsCollection) {
        this.sousMenuActionsCollection = sousMenuActionsCollection;
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
        if (!(object instanceof SousMenu)) {
            return false;
        }
        SousMenu other = (SousMenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mmenu.models.SousMenu[ id=" + id + " ]";
    }
    
}
