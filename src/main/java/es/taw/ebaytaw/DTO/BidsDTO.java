/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.taw.ebaytaw.DTO;


import es.taw.ebaytaw.entity.Products;
import es.taw.ebaytaw.entity.Users;

import java.math.BigDecimal;

/**
 *
 * @author 34637
 */
public class BidsDTO {
    private Integer bidID;
    private Products productID;
    private Users userID;
    private BigDecimal price;

    public Integer getBidID() {
        return bidID;
    }

    public void setBidID(Integer bidID) {
        this.bidID = bidID;
    }

    public Products getProductID() {
        return productID;
    }

    public void setProductID(Products productID) {
        this.productID = productID;
    }

    public Users getUserID() {
        return userID;
    }

    public void setUserID(Users userID) {
        this.userID = userID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
}
