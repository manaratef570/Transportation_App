/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transport_application;

/**
 *
 * @author hamza
 */
public class Events {
    private String name ;
    private String time ;
    private String driver ;
    private String passenger ;
    private float price ;
    
    public Events( String name , String time , String driver , String passenger , float price ){
        this.name=name ;
        this.time=time;
        this.driver=driver;
        this.passenger=passenger;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getDriver() {
        return driver;
    }

    public String getPassenger() {
        return passenger;
    }

    public float getPrice() {
        return price;
    }
    
    
}
