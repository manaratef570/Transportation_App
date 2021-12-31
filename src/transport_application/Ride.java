package transport_application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Manar Atef
 */

public class Ride {
    
    private final String Source ;
    private final String Destination ;
    private final float Price;
    private discount Discount ;
    
    public Ride(String Source, String Destination , float Price , discount Discount) {
        this.Source = Source;
        this.Destination = Destination;
        this.Price = Price;
        this.Discount=Discount;
    }
    
    public void makeAtrip(String passengerUser , String driverUser , String Source , String Destination , float Price , int rating){
        String dbURL = "jdbc:mysql://localhost:3306/transportapp";
        String user = "root";
        String pass = "root" ;
        try {
            Connection conn = DriverManager.getConnection(dbURL, user , pass);
            String query = "Insert Into trip"+"(Passenger , Driver , Source , Destination , Price , Rating)"+
            "Values('"+passengerUser+"' , '"+driverUser+"' , '"+Source+"' ,'"+Destination+"' , '"+Price+"','"+rating+"')";
            Statement log = conn.createStatement();
            log.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }

    public float getPrice() {
        
        return Discount.update_Price(Price);
    }

    public String getSource() {
        return Source;
    }

    public String getDestination() {
        return Destination;
    }
}

