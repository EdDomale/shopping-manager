/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.westminstershopping;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author EdDom
 */
public class ShoppingCentreFrame extends JFrame {

    private ArrayList<Product> products;
    private ShoppingCart shoppingCart;
    private JPanel northPane;
    private JPanel southPane;
    private GridBagConstraints position;
    private JComboBox categoryDropDown;
    private ProductsTableModel tableModel;
    private JTable productsTable;
    private JLabel productID;
    private JLabel category;
    private JLabel name;
    private JLabel categoryAtt1;
    private JLabel categoryAtt2;
    private JLabel items;

    public ShoppingCentreFrame(ArrayList<Product> products) {
        super("Westminster Shopping Centre");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.products = products;
        this.shoppingCart = new ShoppingCart();

        this.northPane = new JPanel(new GridBagLayout());
        this.add(this.northPane, BorderLayout.NORTH);

        addShoppingCartBtn();
        addDropDownLabel();
        addDropDown();
        addTable();

        this.southPane = new JPanel(new GridLayout(8, 0));
        this.add(this.southPane, BorderLayout.SOUTH);

        addLabels();
        addSavetoCartBtn();
    }

    private void addShoppingCartBtn() {
        this.position = new GridBagConstraints();
        this.position.weightx = 0.5;

        JButton shoppingCartButton = new JButton("Shopping Cart");
        this.position.gridx = 3;
        this.position.gridy = 0;
        this.position.anchor = GridBagConstraints.NORTHEAST;
        this.position.insets = new Insets(5, 0, 0, 5);
        this.northPane.add(shoppingCartButton, this.position);

        ShoppingCartBtnHandler handler = new ShoppingCartBtnHandler();
        shoppingCartButton.addActionListener(handler);
    }

    private class ShoppingCartBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            createShoppingCartFrame();
        }
    }

    private void createShoppingCartFrame() {
        ShoppingCartFrame frame = new ShoppingCartFrame(this.shoppingCart);

        frame.pack();
        frame.setVisible(true);
    }

    private void addDropDownLabel() {
        JLabel categoryLabel = new JLabel("Select Product Category");
        this.position.gridx = 0;
        this.position.gridy = 1;
        this.position.insets = new Insets(0, 0, 0, 50);
        this.northPane.add(categoryLabel, this.position);
    }

    private void addDropDown() {
        String categoryOptions[] = {"All", "Electronics", "Clothing"};

        this.categoryDropDown = new JComboBox(categoryOptions);
        this.position.gridwidth = 2;
        this.position.fill = GridBagConstraints.HORIZONTAL;
        this.position.gridx = 1;
        this.position.gridy = 1;
        this.position.insets = new Insets(0, 0, 0, 0);
        this.northPane.add(this.categoryDropDown, this.position);

        DropDownHandler handler = new DropDownHandler();
        this.categoryDropDown.addActionListener(handler);
    }

    private class DropDownHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String query = categoryDropDown.getSelectedItem().toString();

            filter(query);
        }
    }

    private void filter(String categoryClass) {
        ArrayList<Product> filteredProducts = new ArrayList<Product>();

        if (categoryClass.equals("Electronics")) {
            for (Product product : this.products) {
                if (product instanceof Electronics) {
                    filteredProducts.add(product);
                }
            }
        } else if (categoryClass.equals("Clothing")) {
            for (Product product : this.products) {
                if (product instanceof Clothing) {
                    filteredProducts.add(product);
                }
            }
        } else if (categoryClass.equals("All")) {
            for (Product product : this.products) {
                filteredProducts.add(product);
            }
        }

        this.tableModel.setProducts(filteredProducts);
        this.tableModel.fireTableDataChanged();
    }

    private void addTable() {
        this.tableModel = new ProductsTableModel(this.products);

        this.productsTable = new JTable(this.tableModel);

        this.productsTable.setRowSelectionAllowed(true);
        this.productsTable.setRowHeight(30);

        this.productsTable.setAutoCreateRowSorter(true);

        RenderCells renderer = new RenderCells();
        this.productsTable.setDefaultRenderer(Object.class, renderer);

        JScrollPane scrollPane = new JScrollPane(this.productsTable);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        this.position.gridwidth = 4;
        this.position.gridx = 0;
        this.position.gridy = 2;
        this.position.insets = new Insets(70, 10, 10, 10);
        northPane.add(scrollPane, this.position);
    }

    private class RenderCells extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {
            Component renderer = super.getTableCellRendererComponent(table,
                    value,
                    isSelected,
                    hasFocus,
                    row,
                    column);

            int viewRow = productsTable.getRowSorter().convertRowIndexToModel(row);

            if (tableModel.getRow(viewRow).getNumOfItems() < 3) {
                renderer.setBackground(Color.RED);
            } else {
                renderer.setBackground(table.getBackground());
            }

            return renderer;
        }
    }

    private void addLabels() {
        JLabel detailsTitle = new JLabel("Selected Product - Details", JLabel.LEFT);
        this.southPane.add(detailsTitle);

        this.productID = new JLabel("", JLabel.LEFT);
        this.southPane.add(this.productID);

        this.category = new JLabel("", JLabel.LEFT);
        this.southPane.add(this.category);

        this.name = new JLabel("", JLabel.LEFT);
        this.southPane.add(this.name);

        this.categoryAtt1 = new JLabel("", JLabel.LEFT);
        this.southPane.add(this.categoryAtt1);

        this.categoryAtt2 = new JLabel("", JLabel.LEFT);
        this.southPane.add(this.categoryAtt2);

        this.items = new JLabel("", JLabel.LEFT);
        this.southPane.add(this.items);

        ProductListHandler handler = new ProductListHandler();
        productsTable.addMouseListener(handler);
    }

    private class ProductListHandler extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent evt) {
            int index = productsTable.getSelectedRow();
            int row = productsTable.getRowSorter().convertRowIndexToModel(index);

            Product selected = tableModel.getRow(row);
            updateDetails(selected);
        }
    }

    private void updateDetails(Product selected) {
        this.productID.setText(String.format("Product ID: %s", selected.getProductID()));
        this.category.setText(String.format("Category: %s", selected.getProductCategory()));
        this.name.setText(String.format("Name: %s", selected.getProductName()));

        if (selected.getProductCategory().equals("Electronics")) {
            this.categoryAtt1.setText(String.format("Brand: %s", selected.getCategoryAttribute1()));
            this.categoryAtt2.setText(String.format("Warranty Period: %s", selected.getCategoryAttribute2()));
        } else if (selected.getProductCategory().equals("Clothing")) {
            this.categoryAtt1.setText(String.format("Size: %s", selected.getCategoryAttribute1()));
            this.categoryAtt2.setText(String.format("Colour: %s", selected.getCategoryAttribute2()));
        }

        this.items.setText(String.format("Items: %s", selected.getNumOfItems()));
    }

    private void addSavetoCartBtn() {
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton cartButton = new JButton("Add to Shopping Cart");
        buttonPanel.add(cartButton);

        this.southPane.add(buttonPanel);

        ShoppingCartHandler handler = new ShoppingCartHandler();
        cartButton.addActionListener(handler);
    }

    private class ShoppingCartHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            addToCart();
        }
    }

    private void addToCart() {
        int index = this.productsTable.getSelectedRow();
        int row = this.productsTable.getRowSorter().convertRowIndexToModel(index);

        Product selected = this.tableModel.getRow(row);

        this.shoppingCart.addProduct(selected);

        int numOfItems = selected.getNumOfItems();
        
        if (numOfItems > 0) {
            selected.setNumOfItems(numOfItems - 1);
            updateDetails(selected);
        }
    }
}
