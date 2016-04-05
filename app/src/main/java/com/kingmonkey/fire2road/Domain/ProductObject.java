package com.kingmonkey.fire2road.Domain;

/**
 * Created by Jiovany on 28/03/2016.
 */
public class ProductObject {
    private String productName;
    private String productShortDescription;
    private String productImageUrl;
    private String productPrice;

    public ProductObject(String productImageUrl, String productName, String productPrice, String productShortDescription) {
        this.productImageUrl = productImageUrl;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productShortDescription = productShortDescription;
    }

    @Override
    public String toString() {
        return "ProductObject{" +
                "productImageUrl='" + productImageUrl + '\'' +
                ", productName='" + productName + '\'' +
                ", productShortDescription='" + productShortDescription + '\'' +
                ", productPrice='" + productPrice + '\'' +
                '}';
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductShortDescription() {
        return productShortDescription;
    }

    public void setProductShortDescription(String productShortDescription) {
        this.productShortDescription = productShortDescription;
    }
}
