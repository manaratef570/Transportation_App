/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.APP.API.payment;

/**
 *
 * @author hamza
 */
public class discount_5 implements discount{

    @Override
    public float update_Price(float price) {
        return  (float) ( (0.05*price) );
    }
    
}