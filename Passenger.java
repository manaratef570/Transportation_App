package transport_application;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Passenger extends User implements subject {
    
    private String birthdate;
    /**
     * Parameterized Constructor
     * @param userName
     * @param password
     * @param mobilePhone
     * @param Email 
     */
    public Passenger(String userName, String password, long mobilePhone, String Email , String birthdate) {
        super(userName, password, mobilePhone, Email);
        this.birthdate = birthdate;
    }
    /**
     * LogIn Function for Passenger
     * @param userName
     * @param passWord
     * @return 
     */
    /**
     * SignUp Function for Passenger
     * @param username
     * @param passWord
     * @param email
     * @param otherdata 
     */
    
    
    /**
     * Function to return the passenger Info 
     * @param key
     * @return 
     */
    @Override
    public  Passenger retriveAllinfo(String key) {
        String dbURL = "jdbc:mysql://localhost:3306/transportapp";
        String user = "root";
        String pass = "root" ;
        Passenger P = new Passenger("","",0,"","");
         try {
            Connection conn = DriverManager.getConnection(dbURL, user , pass);
            String query = "Select * from passenger where userName='"+key+"' " ;
            Statement log = conn.createStatement();
            ResultSet result = log.executeQuery(query);
            while(result.next()){
                P = new Passenger(result.getString("userName"),result.getString("passWord"),result.getInt("mobilePhone"),
                result.getString("eMail") , result.getString("birthdate") );
            }
            
        } catch (SQLException ex) {
            System.out.println("Error in connection");
        }
        return P ;
    }
    /**
     * Function to rate a driver
     * @return 
     */
    public int rateAdriver(){
         Scanner Input = new Scanner(System.in);
         System.out.println("Rate the driver from *(Star) to *****(Five Stars)");
         System.out.println("(1)*");
         System.out.println("(2)**");
         System.out.println("(3)***");
         System.out.println("(4)*****");
         System.out.println("(5)******");
         int rate = Input.nextInt() ;
         return rate ;
     }
    /**
     * Function to complete a trip
     * @param passengerUser
     * @param driverUser
     * @param Source
     * @param Destination
     * @param Price
     * @param rating 
     */
    
    /**
     * Function to request a driver
     * @param Source
     * @param Destination 
     * @return  
     */
    public Ride requestRide(String Source , String Destination){
        
                
        ArrayList<Driver> results = searchDrivers(Source);
        notify_observers( Source , Destination , results);
        // 
        for(int i = 0 ; i<results.size() ; i++){
            if( results.get(i).get_status() != 0 )continue;
            System.out.println("**********************************************");
            System.out.println("Driver Profile" + (i+1));
            System.out.println("-----------------------");
            System.out.println("Driver UserName:"+ results.get(i).getUserName());
            System.out.println("Driver Email:"+results.get(i).getEmail());
            System.out.println("Driver Phone:"+results.get(i).getMobilePhone());
            System.out.println("Driver Rating:"+results.get(i).getRating());
            System.out.println("**********************************************");
            System.out.println("Suggested Price: "+results.get(i).get_price()+"EGP");
        }
         discount normal = new discount_No();
         Ride ride;
         ride = new Ride( Source , Destination , 0 , normal );
         return ride;
            
    }
    
    public String get_birthdate(){
        return birthdate;
    }
    
    /**
     * Function to search about specific drivers
     * @param Source
     * @return 
     */
    public ArrayList<Driver> searchDrivers(String Source){
        ArrayList<Driver> customizedDrivers = new ArrayList() ;
        String dbURL = "jdbc:mysql://localhost:3306/transportapp";
        String user = "root";
        String pass = "root" ;
        try {
            Connection conn = DriverManager.getConnection(dbURL, user , pass);
            Statement log = conn.createStatement();
            String query = "SELECT * FROM driver Where favAreas LIKE '%" +Source+"%'";
            ResultSet result = log.executeQuery(query);
            while(result.next()){
                Driver D = new Driver(result.getString("userName") , result.getString("passWord")
                , result.getInt("mobilePhone"), result.getString("eMail") , result.getInt("drivinglicense") , result.getInt("nationalID")
                , result.getFloat("avgRating") ,  result.getString("favAreas"),result.getInt("price"),result.getInt("status") );
                customizedDrivers.add(D);
            }        
        } catch (SQLException ex) {
            System.out.println("Error in connection");
        } 
        return customizedDrivers ;
    } 

    public void subscribe(observer o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void unsubscribe(observer o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void notify_observers( String source , String destination ,ArrayList<Driver> results  ) {
         // get all drivers in favArea
         
        for( int i=0 ; i<results.size() ; i++ )
        {
            // req(4) (1) check on status of driver
            if( results.get(i).get_status() != 0 )
                continue ;
            results.get(i).update(source , destination );// inside call offer price and enter price then return it
        }
    }

    
}
