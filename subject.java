/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package transport_application;
//hamza
import java.util.ArrayList;

/**
 *
 * @author hamza
 */
public interface subject {
    public void subscribe( observer o );
    public void unsubscribe( observer o );
    public void notify_observers(String source , String destination ,ArrayList<Driver> results );

    
    
}
