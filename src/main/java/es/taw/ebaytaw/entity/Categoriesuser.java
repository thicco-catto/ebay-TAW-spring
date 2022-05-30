/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

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
 * @author mjura
 */
@Entity
@Table(name = "categoriesuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoriesuser.findAll", query = "SELECT c FROM Categoriesuser c")
    , @NamedQuery(name = "Categoriesuser.findByCategoryUserID", query = "SELECT c FROM Categoriesuser c WHERE c.categoryUserID = :categoryUserID")})
public class Categoriesuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "categoryUserID")
    private Integer categoryUserID;
    @JoinColumn(name = "categoryID", referencedColumnName = "categoryID")
    @ManyToOne(optional = false)
    private Categories categoryID;
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    @ManyToOne(optional = false)
    private Users userID;

    public Categoriesuser() {
    }

    public Categoriesuser(Integer categoryUserID) {
        this.categoryUserID = categoryUserID;
    }

    public Integer getCategoryUserID() {
        return categoryUserID;
    }

    public void setCategoryUserID(Integer categoryUserID) {
        this.categoryUserID = categoryUserID;
    }

    public Categories getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Categories categoryID) {
        this.categoryID = categoryID;
    }

    public Users getUserID() {
        return userID;
    }

    public void setUserID(Users userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryUserID != null ? categoryUserID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoriesuser)) {
            return false;
        }
        Categoriesuser other = (Categoriesuser) object;
        if ((this.categoryUserID == null && other.categoryUserID != null) || (this.categoryUserID != null && !this.categoryUserID.equals(other.categoryUserID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Categoriesuser[ categoryUserID=" + categoryUserID + " ]";
    }
    
}
