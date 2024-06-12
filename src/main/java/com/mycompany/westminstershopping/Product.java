/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.westminstershopping;

/**
 *
 * @author EdDom
 */
abstract class Product implements Comparable<Product> {
    private String productID;
    private String productName;
    private int numOfItems;
    private double price;
    
    public Product(String productID, String productName, int numOfItems,
                    double price) {
        this.productID = productID;
        this.productName = productName;
        this.numOfItems = numOfItems;
        this.price = price;
    }
    
    public String getProductID() {
        return this.productID;
    }
    
    public String getProductName() {
        return this.productName;
    }
    
    public int getNumOfItems() {
        return this.numOfItems;
    }
    
    public double getPrice() {
        return this.price;
    }
    
    public void setProductID(String productID) {
        this.productID = productID;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public void setNumOfItems(int numOfItems) {
        this.numOfItems = numOfItems;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public abstract String getProductCategory();
    
    public abstract String getAttributes();    
    
    public abstract String getInfo();

    public String getProduct() {
        String productInfo = String.format("""
                                           %s
                                           %s
                                           """ + this.getInfo(),
                                    this.productID, this.productName);
                
        return productInfo;      
    }
    
    public abstract String getCategoryAttribute1();
    
    public abstract String getCategoryAttribute2();
    
    @Override
    public int compareTo(Product selected) {
        return this.productID.compareTo(selected.getProductID());
    }

    public String toString() {                
        String info = String.format("""
                                    Product ID: %s
                                    Product Name: %s
                                    Number of Available Items: %s
                                    Price: %.2f
                                    """,
                                this.productID,
                                this.productName,
                                this.numOfItems,
                                this.price);
        
        return info;
    }
}