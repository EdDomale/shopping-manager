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
public class ProductsTableModel extends AbstractTableModel {
    private String[] columnNames;
    private ArrayList<Product> products;
    
    public ProductsTableModel(ArrayList<Product> products) {
        this.columnNames = new String[]{"Product ID",
                                        "Name",
                                        "Category",
                                        "Price(£)", 
                                        "Info"};
        this.products = products;
    }
    
    @Override
    public int getRowCount() {
        return this.products.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;
        
        if (columnIndex == 0) {
            temp = products.get(rowIndex).getProductID();
        } else if (columnIndex == 1) {
            temp = products.get(rowIndex).getProductName();
        } else if (columnIndex == 2) {
            temp = products.get(rowIndex).getProductCategory();
        } else if (columnIndex == 3) {
            temp = String.format("%.2f", products.get(rowIndex).getPrice());            
        } else if (columnIndex == 4) {
            temp = products.get(rowIndex).getInfo();
        }
        
        return temp;
    }
    
    public Product getRow(int rowIndex) {
        Product product = products.get(rowIndex);
        
        return product;
    }    
    
    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }
    
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
