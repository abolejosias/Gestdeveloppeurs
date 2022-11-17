/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.beans;

import java.util.Date;

/**
 *
 * @author hp i3
 */
public class SousMenuActionsBean  {

    private Integer id;
    private Date createdAt;
  
    private Date updatedAt;
    private String strStatut;
    private ActionsBean idactions;
    private SousmenuBean idsousmenu;

    public SousMenuActionsBean() {
    }

    public SousMenuActionsBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStrStatut() {
        return strStatut;
    }

    public void setStrStatut(String strStatut) {
        this.strStatut = strStatut;
    }

    public ActionsBean getIdactions() {
        return idactions;
    }

    public void setIdactions(ActionsBean idactions) {
        this.idactions = idactions;
    }

    public SousmenuBean getIdsousmenu() {
        return idsousmenu;
    }

    public void setIdsousmenu(SousmenuBean idsousmenu) {
        this.idsousmenu = idsousmenu;
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
        if (!(object instanceof SousMenuActionsBean)) {
            return false;
        }
        SousMenuActionsBean other = (SousMenuActionsBean) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mmenu.models.SousMenuActions[ id=" + id + " ]";
    }
    
}
