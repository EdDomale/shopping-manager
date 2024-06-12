/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.westminstershopping;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EdDom
 */
public class SystemCapacityManager {

    private int capacity = 50;
    private static final Logger LOGGER = Logger.getLogger(SystemCapacityManager.class.getName());

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void reportCapacityStatus(ArrayList<Product> products) throws SystemFullException {
        if (products.size() < this.capacity) {
            LOGGER.log(Level.INFO, String.format("Add up to %s more products before limit is reached.", getCapacity() - fetchProductCount(products)));
        } else {
            throw new SystemFullException("Cannot add more products. Capacity has been reached!");
        }
    }

    public void reportProductCount(ArrayList<Product> products) {
        LOGGER.log(Level.INFO, String.format("Total number of products: %s", fetchProductCount(products)));
    }

    public int fetchProductCount(ArrayList<Product> products) {
        return products.size();
    }
}
