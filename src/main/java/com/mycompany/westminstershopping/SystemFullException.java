/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.westminstershopping;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EdDom
 */
public class SystemFullException extends Exception {

    private static final Logger LOGGER = Logger.getLogger(SystemFullException.class.getName());

    public SystemFullException(String message) {
        super(message);
        // LOGGER.log(Level.SEVERE, message);
    }
}
