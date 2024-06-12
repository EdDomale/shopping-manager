/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.westminstershopping;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author EdDom
 */
public class WestminsterShoppingManager implements ShoppingManager {

    public ArrayList<Product> products;
    public SystemCapacityManager capacity = new SystemCapacityManager();

    private boolean quit = false;
    private Scanner input = new Scanner(System.in);

    public void startManager() {
        this.products = loadProductsFromFile();

        while (!this.quit) {
            displayMenu();
            selectMenuOption(fetchOption());
        }
    }

    public ArrayList<Product> loadProductsFromFile() {
        String filePath = "products.txt";
        ArrayList<Product> loadedProducts = new ArrayList<>();

        try {
            FileReader file = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(file);

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 7) {
                    String category = parts[0];
                    String productID = parts[1];
                    String productName = parts[2];
                    int numOfItems = Integer.parseInt(parts[3]);
                    double price = Double.parseDouble(parts[4]);

                    if (category.equals("Electronics")) {
                        String brand = parts[5];
                        String warrantyPeriod = parts[6];
                        Product product = new Electronics(productID, productName, numOfItems, price, brand, warrantyPeriod);
                        loadedProducts.add(product);
                    } else if (category.equals("Clothing")) {
                        String size = parts[5];
                        String colour = parts[6];
                        Product product = new Clothing(productID, productName, numOfItems, price, size, colour);
                        loadedProducts.add(product);
                    }
                }
            }

            System.out.println("\tWestminster Shopping Manager - Welcome Back!");
        } catch (IOException error) {
            System.out.println("\tWelcome to the Westminster Shopping Manager");
        }

        return loadedProducts;
    }

    public void displayMenu() {
        String menu = """
                      --------------------------------------------------
                                        ************
                                        *   MENU   *
                                        ************
                      OPTIONS:
                      \t1) Add a new product
                      \t2) Delete a product
                      \t3) Print the list of the products
                      \t4) Save in a file
                      \t5) Open client GUI
                      \t0) Quit
                      --------------------------------------------------
                      """;

        System.out.println(menu);
    }

    public void selectMenuOption(int option) {
        switch (option) {
            case 1:
                addProduct(createProduct(fetchProductAttributes()));
                break;
            case 2:
                deleteProduct(findProduct(fetchProductID()));
                saveProducts(this.products);
                break;
            case 3:
                displayProducts(this.products);
                break;
            case 4:
                saveProducts(this.products);
                break;
            case 5:
                launchShoppingCentreFrame();
                break;
            case 0:
                this.quit = true;
                break;
        }
    }

    public int fetchOption() {
        do {
            try {
                System.out.print("Select an option: ");
                int option = this.input.nextInt();

                if (isValidOption(option)) {
                    return option;
                }
            } catch (InputMismatchException error) {
                System.out.println("Invalid option.");
                this.input.nextLine();
            }
        } while (true);
    }

    @Override
    public void addProduct(Product product) {
        try {
            this.capacity.reportCapacityStatus(this.products);

            if (product != null) {
                this.products.add(product);
            }            
        } catch (SystemFullException error) {
            System.err.println(error.getMessage());
        }        
    }

    @Override
    public void deleteProduct(Product product) {
        if (product != null) {
            this.products.remove(product);
        } else {
            System.out.println("Product not found.");
        }
    }

    @Override
    public void displayProducts(ArrayList<Product> products) {
        Collections.sort(products);

        System.out.println("********************");
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println("********************\n");
    }

    @Override
    public void saveProducts(ArrayList<Product> products) {
        String filePath = "products.txt";

        try {
            FileWriter file = new FileWriter(filePath, false);
            BufferedWriter writer = new BufferedWriter(file);

            writer.newLine();

            for (Product product : products) {
                writer.write(product.getAttributes());
                writer.newLine();
            }
            
            System.out.println("Save complete.");
            
            this.capacity.reportProductCount(products);

            writer.close();
            file.close();
        } catch (IOException error) {
            System.out.println("Save incomplete: " + error.getMessage());
        }
    }

    @Override
    public void launchShoppingCentreFrame() {
        ShoppingCentreFrame frame = new ShoppingCentreFrame(this.products);        
        
        frame.pack();
        frame.setVisible(true);
    }

    public boolean isValidOption(int option) {
        if (option >= 0 && option <= 5) {
            return true;
        } else {
            System.out.println("Invalid option.");
        }

        return false;
    }    
    
    public Product createProduct(String[] attributes) {
        switch (attributes[0]) {
            case "e":
                return createElectronicProduct(attributes);
            case "c":
                return createClothingProduct(attributes);
            default:
                System.out.println("Invalid type selected.");
                break;
        }
        
        return null;
    }

    public Product findProduct(String productID) {
        for (int item = 0; item < this.products.size(); item++) {
            if (this.products.get(item).getProductID().equals(productID)) {
                return this.products.get(item);
            }
        }

        return null;
    }

    public String[] fetchProductAttributes() {
        String[] attributes = new String[7];

        displayProductTypeOptions();
        this.input.nextLine();
        attributes[0] = fetchType();

        System.out.print("Enter Product ID: ");
        attributes[1] = this.input.nextLine();

        System.out.print("Enter Product Name: ");
        attributes[2] = this.input.nextLine();

        System.out.print("Enter Number of Available Items: ");
        attributes[3] = String.valueOf(fetchNumOfItems());

        System.out.print("Enter Price: ");
        attributes[4] = String.valueOf(fetchPrice());

        if (attributes[0].equals("e")) {
            System.out.print("Enter Brand: ");
            this.input.nextLine();
            attributes[5] = this.input.nextLine();

            System.out.print("Enter Warranty Period: ");
            attributes[6] = this.input.nextLine();
        } else if (attributes[0].equals("c")) {
            System.out.print("Enter Size: ");
            this.input.nextLine();
            attributes[5] = this.input.nextLine();

            System.out.print("Enter Colour: ");
            attributes[6] = this.input.nextLine();
        }

        return attributes;
    }    
    
    public Product createElectronicProduct(String[] attributes) {
        String productID = attributes[1];

        String productName = attributes[2];

        int numOfItems = Integer.parseInt(attributes[3]);

        double price = Double.parseDouble(attributes[4]);

        String brand = attributes[5];

        String warrantyPeriod = attributes[6];

        Product newProduct = new Electronics(productID, productName, numOfItems,
                price, brand, warrantyPeriod);

        return newProduct;
    }

    public Product createClothingProduct(String[] attributes) {
        String productID = attributes[1];

        String productName = attributes[2];

        int numOfItems = Integer.parseInt(attributes[3]);

        double price = Double.parseDouble(attributes[4]);

        String size = attributes[5];

        String colour = attributes[6];

        Product newProduct = new Clothing(productID, productName, numOfItems,
                price, size, colour);

        return newProduct;
    }

    public String fetchProductID() {
        System.out.print("Enter Product ID: ");
        this.input.nextLine();
        String productID = this.input.nextLine();

        return productID;
    }

    public void displayProductTypeOptions() {
        String types = """
                       Specify product type:
                       \tEnter 'e' for Electronics
                       \tEnter 'c' for Clothing
                       """;

        System.out.println(types);
    }

    public String fetchType() {
        System.out.print("Select an option: ");
        String option = this.input.nextLine();

        return option;
    }

    public int fetchNumOfItems() {
        int numOfItems;

        do {
            try {
                return numOfItems = this.input.nextInt();
            } catch (InputMismatchException error) {
                System.out.print("Invalid input. Enter Number of Available Items: ");
                this.input.nextLine();
            }
        } while (true);
    }

    public double fetchPrice() {
        double price;

        do {
            try {
                return price = this.input.nextDouble();
            } catch (InputMismatchException error) {
                System.out.print("Invalid input. Enter Price: ");
                this.input.nextLine();
            }
        } while (true);
    }
}
