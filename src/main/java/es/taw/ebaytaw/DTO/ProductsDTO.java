/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.taw.ebaytaw.DTO;

import es.taw.ebaytaw.entity.Bids;
import es.taw.ebaytaw.entity.Categories;
import es.taw.ebaytaw.entity.Users;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mjura
 */
public class ProductsDTO {
    private int productID;
    private Users userID;
    private List<Bids> bidsList;
    private Categories categoryID;
    private String title;
    private String description;
    private BigDecimal initialPrice;
    private String photo;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date startDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date finishDate;
    private Boolean isSold;

    private Integer userIDint;
    private Integer categoryIDint;

    public ProductsDTO() {
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
    
    public Users getUserID() {
        return userID;
    }

    public List<Bids> getBidsList() {
        return bidsList;
    }

    public Categories getCategoryID() {
        return categoryID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    public String getPhoto() {
        return photo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public Boolean getIsSold() {
        return isSold;
    }

    public void setUserID(Users userID) {
        this.userID = userID;
    }

    public void setBidsList(List<Bids> bidsList) {
        this.bidsList = bidsList;
    }

    public void setCategoryID(Categories categoryID) {
        this.categoryID = categoryID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public void setIsSold(Boolean isSold) {
        this.isSold = isSold;
    }

    public Integer getUserIDint() {
        return userIDint;
    }

    public void setUserIDint(Integer userIDint) {
        this.userIDint = userIDint;
    }

    public Integer getCategoryIDint() {
        return categoryIDint;
    }

    public void setCategoryIDint(Integer categoryIDint) {
        this.categoryIDint = categoryIDint;
    }

    public String getStartDateToString() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        
        StringBuilder fechaAdaptada = new StringBuilder();
        fechaAdaptada.append(formatoFecha.format(this.startDate));
        fechaAdaptada.append(" a las ");
        fechaAdaptada.append(formatoHora.format(this.startDate));
        
        return fechaAdaptada.toString();
    }
    
    public String getFinishDateToString() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        
        StringBuilder fechaAdaptada = new StringBuilder();
        fechaAdaptada.append(formatoFecha.format(this.finishDate));
        fechaAdaptada.append(" a las ");
        fechaAdaptada.append(formatoHora.format(this.finishDate));
        
        return fechaAdaptada.toString();
    }
    
    public Bids lastBidPrice() {
        Bids mayorPuja = null;
        
        if (this.getBidsList() != null && !this.getBidsList().isEmpty()) {
            mayorPuja = this.getBidsList().get(0);
        
            for (Bids puja : this.getBidsList()) {
                if (puja.getPrice().compareTo(mayorPuja.getPrice()) == 1) {
                    mayorPuja = puja;
                }
            } 
        }
        
        return mayorPuja;
    }

    @Override
    public String toString() {
        return "ProductsDTO{" +
                "productID=" + productID +
                ", userID=" + userID +
                ", bidsList=" + bidsList +
                ", categoryID=" + categoryID +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", initialPrice=" + initialPrice +
                ", photo='" + photo + '\'' +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", isSold=" + isSold +
                '}';
    }
}
