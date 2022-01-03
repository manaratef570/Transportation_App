/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.APP.APPI.DB;
//hamza
import java.util.ArrayList;

import com.APP.API.Users.Driver;

/**
 *
 * @author hamza
 */
public interface subject {
    public void subscribe( observer o );
    public void unsubscribe( observer o );
    public void notify_observers(String source , String destination ,ArrayList<Driver> results );

    
    
}
