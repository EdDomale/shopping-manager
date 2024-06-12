/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.westminstershopping;

import java.util.ArrayList;


/**
 *
 * @author EdDom
 */
public interface ShoppingManager {
    public abstract void addProduct(Product product);
    public abstract void deleteProduct(Product product);
    public abstract void displayProducts(ArrayList<Product> products);
    public abstract void saveProducts(ArrayList<Product> products);
    public abstract void launchShoppingCentreFrame();
}
