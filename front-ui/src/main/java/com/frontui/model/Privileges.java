/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author hp i3
 */
@Entity
@Table(name = "privileges", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Privileges.findAll", query = "SELECT p FROM Privileges p")
    , @NamedQuery(name = "Privileges.findById", query = "SELECT p FROM Privileges p WHERE p.id = :id")
    , @NamedQuery(name = "Privileges.findByName", query = "SELECT p FROM Privileges p WHERE p.name = :name")
    , @NamedQuery(name = "Privileges.findByDescription", query = "SELECT p FROM Privileges p WHERE p.description = :description")
    , @NamedQuery(name = "Privileges.findByCreatedBy", query = "SELECT p FROM Privileges p WHERE p.createdBy = :createdBy")
    , @NamedQuery(name = "Privileges.findByUpdatedBy", query = "SELECT p FROM Privileges p WHERE p.updatedBy = :updatedBy")
    , @NamedQuery(name = "Privileges.findByCreatedAt", query = "SELECT p FROM Privileges p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "Privileges.findByUpdatedAt", query = "SELECT p FROM Privileges p WHERE p.updatedAt = :updatedAt")
    , @NamedQuery(name = "Privileges.findByStrStatut", query = "SELECT p FROM Privileges p WHERE p.strStatut = :strStatut")})
public class Privileges implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;
    @Size(max = 254)
    @Column(name = "description", length = 254)
    private String description;
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
    @JoinColumn(name = "idprivilegelevel", referencedColumnName = "id")
    @ManyToOne
    private Privilegelevel idprivilegelevel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprivileges")
    private Collection<RolePrivileges> rolePrivilegesCollection;

    public Privileges() {
    }

    public Privileges(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Privilegelevel getIdprivilegelevel() {
        return idprivilegelevel;
    }

    public void setIdprivilegelevel(Privilegelevel idprivilegelevel) {
        this.idprivilegelevel = idprivilegelevel;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<RolePrivileges> getRolePrivilegesCollection() {
        return rolePrivilegesCollection;
    }

    public void setRolePrivilegesCollection(Collection<RolePrivileges> rolePrivilegesCollection) {
        this.rolePrivilegesCollection = rolePrivilegesCollection;
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
        if (!(object instanceof Privileges)) {
            return false;
        }
        Privileges other = (Privileges) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.frontui.model.Privileges[ id=" + id + " ]";
    }
    
}
