package com.pluralsight;

public class Product {
    private int productId;
    private String productName;
    private double productDescription;

    public Product(int productId, String productName, double productDescription) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(double productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescription=" + productDescription +
                '}';
    }
}
