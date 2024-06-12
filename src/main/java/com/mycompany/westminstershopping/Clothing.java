/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.westminstershopping;

/**
 *
 * @author EdDom
 */
public class Clothing extends Product {
    private String size;
    private String colour;
    
    public Clothing(String productID, String productName, int numOfItems,
                    double price) {
        super(productID, productName, numOfItems, price);
    }    
    
    public Clothing(String productID, String productName, int numOfItems,
                    double price, String size) {
        super(productID, productName, numOfItems, price);
        this.size = size;
    }    
    
    public Clothing(String productID, String productName, int numOfItems,
                    double price, String size, String colour) {
        super(productID, productName, numOfItems, price);
        this.size = size;
        this.colour = colour;
    }
    
    public String getSize() {
        return this.size;
    }
    
    public String getColour() {
        return this.colour;
    }
    
    public void setSize(String size) {
        this.size = size;
    }
    
    public void setColour(String colour) {
        this.colour = colour;
    }
    
    @Override
    public String getProductCategory() {
        return "Clothing";
    }

    @Override
    public String getCategoryAttribute1() {
        return this.getSize();
    }
    
    @Override
    public String getCategoryAttribute2() {
        return this.getColour();
    }    
    
    @Override
    public String getAttributes() {
        String attributes = String.format("Clothing,%s,%s,%s,%s,%s,%s",
                                            this.getProductID(),
                                            this.getProductName(),
                                            this.getNumOfItems(),
                                            this.getPrice(),
                                            this.size,
                                            this.colour);
        
        return attributes;
    }    
    
    @Override
    public String getInfo() {
        String info = String.format("%s, %s", this.size,
                                                this.colour);
        
        return info;
    }   
    
    @Override
    public String toString() {
        String info = String.format(super.toString() + 
                                    """
                                    Size: %s
                                    Colour: %s
                                    Type: Clothing
                                    """,
                                this.size, this.colour);
        
        
        return info;
    }
}
