/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.westminstershopping;

/**
 *
 * @author EdDom
 */
public class Stock {

    public void itemAvailable(Product product) throws SoldOutException {

        if (product.getNumOfItems() <= 0) {
            throw new SoldOutException("Product sold out. No items remaining.");
        }
    }
}
