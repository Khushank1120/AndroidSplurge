package com.example.appkhushveehoreca;

public class CartItemModel {

    public static final int CART_ITEM = 0;
    public static final int TOTAL_AMOUNT = 1;

    private int type;
    private Long productQuantity, maxQuantity;
    private String productID;
    private int productImage;
    private String productTitle;
    private String productPrice;
    private String cuttedPrice;

    public CartItemModel(int type, int productImage, String productTitle, String productPrice, String cuttedPrice) {
        this.type = type;
//        this.productQuantity = productQuantity;
//        this.maxQuantity = maxQuantity;
//        this.productID = productID;
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.cuttedPrice = cuttedPrice;
    }

    public int getType() {
        return type;
    }

    // Cart total amount left not needed so didn't make //

    public void setType(int type) {
        this.type = type;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
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

