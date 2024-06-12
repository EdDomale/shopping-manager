/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.westminstershopping;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author EdDom
 */
public class WestminsterShoppingManagerTest {

    private WestminsterShoppingManager shoppingManager;
    private SystemCapacityManager capacityManager;
    private ArrayList<Product> products;
    private Product jumper;
    private Product laptop;

    public WestminsterShoppingManagerTest() {
    }

    @BeforeEach
    public void setUp() {
        this.shoppingManager = new WestminsterShoppingManager();
        this.capacityManager = this.shoppingManager.capacity;
        this.products = this.shoppingManager.products = new ArrayList<>();

        this.jumper = new Clothing("0001", "Jumper", 20, 89.00, "Large", "Black/White");
        this.products.add(this.jumper);

        this.laptop = new Electronics("0002", "Laptop", 2, 990.00, "Asus", "No warranty");
        this.products.add(this.laptop);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test addProduct method for adding a product when system
     * has space.
     */
    @org.junit.jupiter.api.Test
    public void testAddProductWhenSystemNotFull() {
        // Electronics(productID, productName, numOfItems, price, brand, warrantyPeriod)
        Product product = new Electronics("A", "Speakers", 22, 599.98, "Bose", "2 weeks warranty");
        this.shoppingManager.addProduct(product);

        assertTrue(this.products.size() < this.capacityManager.getCapacity());
        assertTrue(this.products.contains(product));
    }

    /**
     * Test the addProduct method by adding a product when only
     * one space remains.
     */
    @org.junit.jupiter.api.Test
    public void testAddProductWhenOneSpaceRemains() {
        this.capacityManager.setCapacity(3);

        // Electronics(productID, productName, numOfItems, price, brand, warrantyPeriod)
        Product product = new Electronics("A", "Speakers", 22, 599.98, "Bose", "2 weeks warranty");
        this.shoppingManager.addProduct(product);

        assertFalse(this.products.size() < this.capacityManager.getCapacity());
        assertTrue(this.products.contains(product));
    }

    /**
     * Test addProduct method for adding a product when system
     * is full.
     */
    @org.junit.jupiter.api.Test
    public void testAddProductWhenSystemFull() {
        this.capacityManager.setCapacity(2);

        // Electronics(productID, productName, numOfItems, price, brand, warrantyPeriod)
        Product product = new Electronics("A", "Speakers", 22, 599.98, "Bose", "2 weeks warranty");
        this.shoppingManager.addProduct(product);

        assertFalse(this.products.size() < this.capacityManager.getCapacity());
        assertFalse(this.products.contains(product));
    }

    /**
     * Test addProduct for adding an electronic product.
     */
    @org.junit.jupiter.api.Test
    public void testAddElectronicProduct() {
        // Electronics(productID, productName, numOfItems, price, brand, warrantyPeriod)
        String[] attributes = {"e", "A", "iPad", "50", "979.98", "Apple", "No warranty"};
        Product product = this.shoppingManager.createProduct(attributes);

        this.shoppingManager.addProduct(product);

        assertTrue(this.products.contains(product));
    }

    /**
     * Test addProduct method for adding a clothing product.
     */
    @org.junit.jupiter.api.Test
    public void testAddClothingProduct() {
        // Clothing(productID, productName, numOfItems, price, size, colour)
        String[] attributes = {"c", "A", "Cargos", "7", "120.00", "Medium", "Green"};
        Product product = this.shoppingManager.createProduct(attributes);

        this.shoppingManager.addProduct(product);

        assertTrue(this.products.contains(product));
    }

    /**
     * Test addProduct method for adding a product with a
     * category that does not exist.
     */
    @org.junit.jupiter.api.Test
    public void testAddProductWithInvalidCategory() {
        // Furniture(productID, productName, numOfItems, price, colour, warrantyPeriod);
        String[] attributes = {"f", "A", "Couch", "3", "579.58", "Black", "10 year warranty"};
        Product product = this.shoppingManager.createProduct(attributes);

        this.shoppingManager.addProduct(product);

        assertFalse(this.products.contains(product));
    }

    /**
     * Test addProduct method for adding a product without a
     * category.
     */
    @org.junit.jupiter.api.Test
    public void testAddProductWithoutCategory() {
        // (productID, productName, numOfItems, price, size, colour)
        String[] attributes = {"", "A", "Cargos", "7", "120.00", "Medium", "Green"};
        Product product = this.shoppingManager.createProduct(attributes);

        this.shoppingManager.addProduct(product);

        assertFalse(this.products.contains(product));
    }

    /**
     * Test addProduct method for adding an electronic product with invalid
     * details.
     */
    @org.junit.jupiter.api.Test
    public void testAddElectronicProductWithInvalidData() {
        // Electronics(productID, productName, numOfItems, price, brand, warrantyPeriod)
        String[] attributes = {"e", "A", "iPad", "Fifty", "979.98", "Apple", "No warranty"};

        assertThrows(NumberFormatException.class, () -> {
            this.shoppingManager.createProduct(attributes);
        });
    }

    /**
     * Test addProduct method for adding a clothing product with invalid
     * details.
     */
    @org.junit.jupiter.api.Test
    public void testAddClothingProductWithInvalidData() {
        // Clothing(productID, productName, numOfItems, price, size, colour)
        String[] attributes = {"c", "A", "Cargos", "7", "£120.00", "Medium", "Green"};

        assertThrows(NumberFormatException.class, () -> {
            this.shoppingManager.createProduct(attributes);
        });
    }

    /**
     * Test deleteProduct method for deleting a product.
     */
    @org.junit.jupiter.api.Test
    public void testDeleteProduct() {
        Product product = this.shoppingManager.findProduct("0002");
        this.shoppingManager.deleteProduct(product);

        assertEquals(this.laptop, product);
        assertFalse(this.products.contains(product));
    }

    /**
     * Test deleteProduct method for deleting a product that does not exist.
     */
    @org.junit.jupiter.api.Test
    public void testDeleteNonExistingProduct() {
        PrintStream originalOut = System.out;

        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        Product product = this.shoppingManager.findProduct(null);
        this.shoppingManager.deleteProduct(product);

        String separator = System.getProperty("line.separator");
        String expected = "Product not found." + separator;

        assertEquals(expected, os.toString());

        System.setOut(originalOut);
    }

    /**
     * Test displayProduct method for displaying all products in system.
     */
    @org.junit.jupiter.api.Test
    public void testDisplayProducts() {
        PrintStream originalOut = System.out;

        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        this.shoppingManager.displayProducts(this.products);

        String separator = System.getProperty("line.separator");
        String expected = "********************" + separator
                + "Product ID: 0001" + separator
                + "Product Name: Jumper" + separator
                + "Number of Available Items: 20" + separator
                + "Price: 89.00" + separator
                + "Size: Large" + separator
                + "Colour: Black/White" + separator
                + "Type: Clothing" + separator + separator
                + "Product ID: 0002" + separator
                + "Product Name: Laptop" + separator
                + "Number of Available Items: 2" + separator
                + "Price: 990.00" + separator
                + "Brand: Asus" + separator
                + "Warranty Period: No warranty" + separator
                + "Type: Electronics" + separator + separator
                + "********************" + separator + separator;

        String actual = os.toString().replace("\r\n", "\n").replace("\n", System.lineSeparator());
        assertEquals(expected, actual);

        System.setOut(originalOut);
    }

}
