/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transport_application;

/**
 *
 * @author hamza
 */
public class discount_5 implements discount{

    @Override
    public int update_Price(int price) {
        return (price - (int) (0.05*price) );
    }
    
}
