/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phd.pojo;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dat98
 */
@Entity
@Table(name = "store_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StoreCategory.findAll", query = "SELECT s FROM StoreCategory s"),
    @NamedQuery(name = "StoreCategory.findById", query = "SELECT s FROM StoreCategory s WHERE s.id = :id")})
public class StoreCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    @ManyToOne
    private Category storeId;
    @JoinColumn(name = "cate_id", referencedColumnName = "id")
    @ManyToOne
    private Store cateId;

    public StoreCategory() {
    }

    public StoreCategory(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getStoreId() {
        return storeId;
    }

    public void setStoreId(Category storeId) {
        this.storeId = storeId;
    }

    public Store getCateId() {
        return cateId;
    }

    public void setCateId(Store cateId) {
        this.cateId = cateId;
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
        if (!(object instanceof StoreCategory)) {
            return false;
        }
        StoreCategory other = (StoreCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.phd.pojo.StoreCategory[ id=" + id + " ]";
    }
    
}