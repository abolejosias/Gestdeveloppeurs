/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.model;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hp i3
 */
@Entity
@Table(name = "role_privileges", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolePrivileges.findAll", query = "SELECT r FROM RolePrivileges r")
    , @NamedQuery(name = "RolePrivileges.findById", query = "SELECT r FROM RolePrivileges r WHERE r.id = :id")
    , @NamedQuery(name = "RolePrivileges.findByCreatedBy", query = "SELECT r FROM RolePrivileges r WHERE r.createdBy = :createdBy")
    , @NamedQuery(name = "RolePrivileges.findByUpdatedBy", query = "SELECT r FROM RolePrivileges r WHERE r.updatedBy = :updatedBy")
    , @NamedQuery(name = "RolePrivileges.findByCreatedAt", query = "SELECT r FROM RolePrivileges r WHERE r.createdAt = :createdAt")
    , @NamedQuery(name = "RolePrivileges.findByUpdatedAt", query = "SELECT r FROM RolePrivileges r WHERE r.updatedAt = :updatedAt")
    , @NamedQuery(name = "RolePrivileges.findByStrStatut", query = "SELECT r FROM RolePrivileges r WHERE r.strStatut = :strStatut")})
public class RolePrivileges implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 40)
    @Column(name = "created_by", length = 40)
    private String createdBy;
    @Size(max = 40)
    @Column(name = "updated_by", length = 40)
    private String updatedBy;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Size(max = 20)
    @Column(name = "str_statut", length = 20)
    private String strStatut;
    @JoinColumn(name = "idprivileges", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Privileges idprivileges;
    @JoinColumn(name = "idroles", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Role idroles;

    public RolePrivileges() {
    }

    public RolePrivileges(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
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

    public Privileges getIdprivileges() {
        return idprivileges;
    }

    public void setIdprivileges(Privileges idprivileges) {
        this.idprivileges = idprivileges;
    }

    public Role getIdroles() {
        return idroles;
    }

    public void setIdroles(Role idroles) {
        this.idroles = idroles;
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
        if (!(object instanceof RolePrivileges)) {
            return false;
        }
        RolePrivileges other = (RolePrivileges) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.frontui.model.RolePrivileges[ id=" + id + " ]";
    }
    
}
