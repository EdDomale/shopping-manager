/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.westminstershopping;

import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author EdDom
 */
public class ShoppingCart {
    private ArrayList<Product> listOfProducts = new ArrayList<Product>();
    private ArrayList<Product> checkout = new ArrayList<Product>();
    private int quantity;
    private Stock stock = new Stock();
    
    public void addProduct(Product product) {
        try {
            this.stock.itemAvailable(product);
            this.listOfProducts.add(product);
        } catch (SoldOutException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    
    public void removeProduct(Product product) {
        this.listOfProducts.remove(product);
    }
    
    public double calculateTotal() {
        double total = 0;
        
        for (Product product : this.listOfProducts) {
            total += product.getPrice();
        }
        
        return total;
    }
    
    public double calculateDiscount() {
        double discount = 0;
        
        if (isDiscountAvailable()) {
            discount = calculateTotal() * 0.2;
        }
        
        return discount;
    }
    
    public boolean isDiscountAvailable() {
        int numOfElectronics = 0;
        int numOfClothing = 0; 
        
        for (Product product : this.listOfProducts) {
            if (product instanceof Electronics) {
                numOfElectronics++;
            } else if (product instanceof Clothing) {
                numOfClothing++;                
            }
        }
        
        return (numOfElectronics >= 3 || numOfClothing >= 3);
    }
    
    public double calculateFinal() {
        double finalTotal;
        
        finalTotal = calculateTotal() - calculateDiscount();
        
        return finalTotal;
    }
    
    public ArrayList<Product> getCheckout() {
        for (Product product : this.listOfProducts) {
            if (!this.checkout.contains(product)) {
                this.checkout.add(product);
            }
        }
        
        return this.checkout;
    }
    
    public int getQuantity(Product checkoutProduct) {
        this.quantity = 0;
        
        for (Product listProduct : this.listOfProducts) {
            if (checkoutProduct.equals(listProduct)) {
                this.quantity++;
            }
        }
        
        return this.quantity;
    }
}
