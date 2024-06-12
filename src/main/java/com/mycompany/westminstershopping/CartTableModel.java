/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.westminstershopping;

import java.util.*;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author EdDom
 */
public class CartTableModel extends AbstractTableModel {
    private String[] columnNames;
    private ShoppingCart shoppingCart;
    private ArrayList<Product> productsInCart;
    
    public CartTableModel(ShoppingCart shoppingCart, ArrayList<Product> productsInCart) {
        this.columnNames = new String[]{"Product",
                                        "Quantity",
                                        "Price"};
        this.shoppingCart = shoppingCart;
        this.productsInCart = productsInCart;
    }

    @Override
    public int getRowCount() {
        return this.productsInCart.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;
        
        if (columnIndex == 0) {
            temp = productsInCart.get(rowIndex).getProduct();
        } else if (columnIndex == 1) {
            temp = shoppingCart.getQuantity(productsInCart.get(rowIndex));
        } else if (columnIndex == 2) {
            temp = String.format("%.2f £", productsInCart.get(rowIndex).getPrice());
        }
        
        return temp;
    }
    
    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }
    
}
