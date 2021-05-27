package com.example.appkhushveehoreca;

public class WishlistModel {


    private String productImage;



    private String productId;
    private String productTitle;
    private String productTitle1;
    private String freeCoupons;
    private String productPrice;
    private String cuttedPrice;

    public WishlistModel(String id, String productImage, String productTitle, String productTitle1, String freeCoupons, String productPrice, String cuttedPrice) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productTitle1 = productTitle1;
        this.freeCoupons = freeCoupons;
        this.productPrice = productPrice;
        this.cuttedPrice = cuttedPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public WishlistModel(String productId) {
        this.productId = productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductTitle1() {
        return productTitle1;
    }

    public void setProductTitle1(String productTitle1) {
        this.productTitle1 = productTitle1;
    }

    public String getFreeCoupons() {
        return freeCoupons;
    }

    public void setFreeCoupons(String freeCoupons) {
        this.freeCoupons = freeCoupons;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCuttedPrice() {
        return cuttedPrice;
    }

    public void setCuttedPrice(String cuttedPrice) {
        this.cuttedPrice = cuttedPrice;
    }
}