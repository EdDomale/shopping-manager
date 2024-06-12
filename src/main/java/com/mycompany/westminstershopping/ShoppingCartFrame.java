/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.westminstershopping;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author EdDom
 */
public class ShoppingCartFrame extends JFrame {
    private ShoppingCart shoppingCart;    
    private JPanel summary;
    private GridBagConstraints position;    
    private JLabel total;
    private JLabel discount;
    private JLabel finalTotal;
    
    public ShoppingCartFrame(ShoppingCart shoppingCart) {
        super("Shopping Cart");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        this.shoppingCart = shoppingCart;
                
        addTable();
        
        this.summary = new JPanel(new GridBagLayout());
        this.add(this.summary, BorderLayout.SOUTH);
        
        addLabel();
    }
    
    private void addTable() {
        CartTableModel tableModel = new CartTableModel(shoppingCart, shoppingCart.getCheckout());
        
        JTable cartTable = new JTable(tableModel);
        
        cartTable.setRowHeight(100);
        cartTable.setFocusable(false);
        cartTable.setRowSelectionAllowed(false);
        
        JScrollPane scrollPane = new JScrollPane(cartTable);
        this.add(scrollPane, BorderLayout.NORTH);      
    }

    private void addLabel() {
        this.position = new GridBagConstraints();
                
        JLabel totalLabel = new JLabel("Total");
        this.position.gridwidth = 2;
        this.position.gridx = 0;
        this.position.gridy = 0;
        this.position.anchor = GridBagConstraints.EAST;
        this.position.insets = new Insets(10,0,10,10);        
        this.summary.add(totalLabel, this.position);
        
        this.total = new JLabel(String.valueOf(String.format("%.2f £", shoppingCart.calculateTotal())));
        this.position.gridwidth = 1;
        this.position.gridx = 2;
        this.position.gridy = 0;
        this.position.anchor = GridBagConstraints.WEST;
        this.position.insets = new Insets(10,10,10,0);        
        this.summary.add(this.total, this.position);
        
        JLabel discountLabel = new JLabel("Three Items in same Category Discount (20%)");
        this.position.gridwidth = 2;
        this.position.gridx = 0;
        this.position.gridy = 1;
        this.position.anchor = GridBagConstraints.EAST;
        this.position.insets = new Insets(10,0,10,10);        
        this.summary.add(discountLabel, this.position);
        
        this.discount = new JLabel(String.format("- %.2f £", shoppingCart.calculateDiscount()));
        this.position.gridwidth = 1;
        this.position.gridx = 2;
        this.position.gridy = 1;
        this.position.anchor = GridBagConstraints.WEST;
        this.position.insets = new Insets(10,10,10,0);        
        this.summary.add(this.discount, this.position);
        
        JLabel finalTotalLabel = new JLabel("Final Total");
        this.position.gridwidth = 2;
        this.position.gridx = 0;
        this.position.gridy = 2;
        this.position.anchor = GridBagConstraints.EAST;
        this.position.insets = new Insets(10,0,50,10);        
        this.summary.add(finalTotalLabel, this.position);
        
        this.finalTotal = new JLabel(String.valueOf(String.format("%.2f £", shoppingCart.calculateFinal())));
        this.position.gridwidth = 1;
        this.position.gridx = 2;
        this.position.gridy = 2;
        this.position.anchor = GridBagConstraints.WEST;
        this.position.insets = new Insets(10,10,50,0);        
        this.summary.add(this.finalTotal, this.position);        
    }
}
