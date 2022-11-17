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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "privilegelevel", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Privilegelevel.findAll", query = "SELECT p FROM Privilegelevel p")
    , @NamedQuery(name = "Privilegelevel.findById", query = "SELECT p FROM Privilegelevel p WHERE p.id = :id")
    , @NamedQuery(name = "Privilegelevel.findByName", query = "SELECT p FROM Privilegelevel p WHERE p.name = :name")
    , @NamedQuery(name = "Privilegelevel.findByCreatedAt", query = "SELECT p FROM Privilegelevel p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "Privilegelevel.findByUpdatedAt", query = "SELECT p FROM Privilegelevel p WHERE p.updatedAt = :updatedAt")
    , @NamedQuery(name = "Privilegelevel.findByStrStatut", query = "SELECT p FROM Privilegelevel p WHERE p.strStatut = :strStatut")})
public class Privilegelevel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Size(max = 20)
    @Column(name = "str_statut", length = 20)
    private String strStatut;
    @OneToMany(mappedBy = "idprivilegelevel")
    @JsonIgnore
    private Collection<Privileges> privilegesCollection;

    public Privilegelevel() {
    }

    public Privilegelevel(Integer id) {
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

    @XmlTransient
    @JsonIgnore
    public Collection<Privileges> getPrivilegesCollection() {
        return privilegesCollection;
    }

    public void setPrivilegesCollection(Collection<Privileges> privilegesCollection) {
        this.privilegesCollection = privilegesCollection;
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
        if (!(object instanceof Privilegelevel)) {
            return false;
        }
        Privilegelevel other = (Privilegelevel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.frontui.model.Privilegelevel[ id=" + id + " ]";
    }
    
}
