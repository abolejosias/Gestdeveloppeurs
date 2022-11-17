/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmenu.models;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hp i3
 */
@Entity
@Table(name = "sous_menu_actions", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SousMenuActions.findAll", query = "SELECT s FROM SousMenuActions s")
    , @NamedQuery(name = "SousMenuActions.findById", query = "SELECT s FROM SousMenuActions s WHERE s.id = :id")
    , @NamedQuery(name = "SousMenuActions.findByCreatedAt", query = "SELECT s FROM SousMenuActions s WHERE s.createdAt = :createdAt")
    , @NamedQuery(name = "SousMenuActions.findByUpdatedAt", query = "SELECT s FROM SousMenuActions s WHERE s.updatedAt = :updatedAt")
    , @NamedQuery(name = "SousMenuActions.findByStrStatut", query = "SELECT s FROM SousMenuActions s WHERE s.strStatut = :strStatut")})
public class SousMenuActions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "str_statut", length = 20)
    private String strStatut;
    @JoinColumn(name = "idactions", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Actions idactions;
    @JoinColumn(name = "idsousmenu", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private SousMenu idsousmenu;

    public SousMenuActions() {
    }

    public SousMenuActions(Integer id) {
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

    public Actions getIdactions() {
        return idactions;
    }

    public void setIdactions(Actions idactions) {
        this.idactions = idactions;
    }

    public SousMenu getIdsousmenu() {
        return idsousmenu;
    }

    public void setIdsousmenu(SousMenu idsousmenu) {
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
        if (!(object instanceof SousMenuActions)) {
            return false;
        }
        SousMenuActions other = (SousMenuActions) object;
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
