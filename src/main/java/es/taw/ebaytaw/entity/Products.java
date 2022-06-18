/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.taw.ebaytaw.entity;

import es.taw.ebaytaw.DTO.ProductsDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mjura
 */
@Entity
@Table(name = "products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p")
    , @NamedQuery(name = "Products.findByProductID", query = "SELECT p FROM Products p WHERE p.productID = :productID")
    , @NamedQuery(name = "Products.findByTitle", query = "SELECT p FROM Products p WHERE p.title = :title")
    , @NamedQuery(name = "Products.findByDescription", query = "SELECT p FROM Products p WHERE p.description = :description")
    , @NamedQuery(name = "Products.findByInitialPrice", query = "SELECT p FROM Products p WHERE p.initialPrice = :initialPrice")
    , @NamedQuery(name = "Products.findByPhoto", query = "SELECT p FROM Products p WHERE p.photo = :photo")
    , @NamedQuery(name = "Products.findByStartDate", query = "SELECT p FROM Products p WHERE p.startDate = :startDate")
    , @NamedQuery(name = "Products.findByFinishDate", query = "SELECT p FROM Products p WHERE p.finishDate = :finishDate")
    , @NamedQuery(name = "Products.findByIsSold", query = "SELECT p FROM Products p WHERE p.isSold = :isSold")})
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "productID")
    private Integer productID;
    @Basic(optional = false)
    @Column(name = "title", nullable = false, length = 128)
    private String title;
    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 512)
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "initialPrice", nullable = false)
    private BigDecimal initialPrice;
    @Column(name = "photo", length = 512)
    private String photo;
    @Column(name = "startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "finishDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishDate;
    @Column(name = "isSold")
    private Boolean isSold;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productID")
    private List<Bids> bidsList;
    @JoinColumn(name = "categoryID", referencedColumnName = "categoryID")
    @ManyToOne(optional = false)
    private Categories categoryID;
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    @ManyToOne(optional = false)
    private Users userID;

    public Products() {
    }

    public Products(Integer productID) {
        this.productID = productID;
    }

    public Products(Integer productID, String title, String description, BigDecimal initialPrice) {
        this.productID = productID;
        this.title = title;
        this.description = description;
        this.initialPrice = initialPrice;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Boolean getIsSold() {
        return isSold;
    }

    public void setIsSold(Boolean isSold) {
        this.isSold = isSold;
    }

    @XmlTransient
    public List<Bids> getBidsList() {
        return bidsList;
    }

    public void setBidsList(List<Bids> bidsList) {
        this.bidsList = bidsList;
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
        hash += (productID != null ? productID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.productID == null && other.productID != null) || (this.productID != null && !this.productID.equals(other.productID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Products[ productID=" + productID + " ]";
    }

    @Transient
    public ProductsDTO toDTO(){
        ProductsDTO dto = new ProductsDTO();

        dto.setProductID(productID);
        dto.setTitle(title);
        dto.setDescription(description);
        dto.setInitialPrice(initialPrice);
        dto.setPhoto(photo);
        dto.setStartDate(startDate);
        dto.setFinishDate(finishDate);
        dto.setIsSold(isSold);
        dto.setBidsList(bidsList);
        dto.setCategoryID(categoryID);
        dto.setUserID(userID);
        
        return dto;
    }
    
}
