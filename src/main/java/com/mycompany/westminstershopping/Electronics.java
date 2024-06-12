/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.westminstershopping;

/**
 *
 * @author EdDom
 */
public class Electronics extends Product {
    private String brand;
    private String warrantyPeriod;
    
    public Electronics(String productID, String productName, int numOfItems,
                        double price) {
        super(productID, productName, numOfItems, price);
    }
    
    public Electronics(String productID, String productName, int numOfItems,
                        double price, String brand) {
        super(productID, productName, numOfItems, price);
        this.brand = brand;
    }
    
    public Electronics(String productID, String productName, int numOfItems,
                        double price, String brand, String warrantyPeriod) {
        super(productID, productName, numOfItems, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }
    
    public String getBrand() {
        return this.brand;
    }
    
    public String getWarrantyPeriod() {
        return this.warrantyPeriod;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
    
    @Override
    public String getProductCategory() {
        return "Electronics";
    }

    @Override
    public String getCategoryAttribute1() {
        return this.getBrand();
    }
    
    @Override
    public String getCategoryAttribute2() {
        return this.getWarrantyPeriod();
    }
    
    @Override
    public String getAttributes() {
        String attributes = String.format("Electronics,%s,%s,%s,%s,%s,%s",
                                            this.getProductID(),
                                            this.getProductName(),
                                            this.getNumOfItems(),
                                            this.getPrice(),
                                            this.brand,
                                            this.warrantyPeriod);
        
        return attributes;
    }
    
    @Override
    public String getInfo() {
        String info = String.format("%s, %s", this.brand,
                                                this.warrantyPeriod);
        
        return info;
    }
    
    @Override
    public String toString() {      
        String info = String.format(super.toString() +
                                    """
                                    Brand: %s
                                    Warranty Period: %s
                                    Type: Electronics
                                    """,
                                this.brand, this.warrantyPeriod);
        
        return info;
    }
}
