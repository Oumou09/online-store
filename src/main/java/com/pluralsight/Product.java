package com.pluralsight;

public class Product {
    private static String productId;
    private String productDescription;
    private double productPrice;

    public Product( String productId, String productDescription, double productPrice) {
        this.productId = productId;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public static String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
