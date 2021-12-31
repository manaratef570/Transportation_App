package transport_application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//hamza
public class Admin extends User{

    String area="";
    ArrayList<Events> events = new ArrayList<Events>();
    /**
     * Parameterized Constructor
     * @param userName
     * @param password
     * @param mobilePhone
     * @param Email 
     */
    public Admin(String userName, String password, long mobilePhone, String Email) {
        super(userName, password, mobilePhone, Email);
    }
    /**
     * Function to validate the data for a driver or for a passenger
     * @param userName
     * @param passWord
     * @param email
     * @param otherdata
     * @return 
     */
    public boolean verfyDatauser(String userName , String passWord , String email ,  long ... otherdata){
        int dOrp = otherdata.length ;
        switch(dOrp){
            case 1:
                if(email.contains("@")&& validateNumericdata(otherdata[0]) <= 11){
                    String dbURL = "jdbc:mysql://localhost:3306/transportapp";
                    String user = "root";
                    String pass = "root" ;
                    boolean Flag = true ;
                    try {
                        Connection conn = DriverManager.getConnection(dbURL, user , pass);
                        String query = "Select * from passenger where userName=? and passWord=? and eMail=? and mobilePhone=?" ;
                        PreparedStatement log = conn.prepareStatement(query);
                        String query1 = "Select * from passenger where userName=?" ;
                        PreparedStatement log1 = conn.prepareStatement(query1);
                        log.setString(1, userName);
                        log.setString(2, passWord);
                        log.setString(3, email);
                        log.setLong(4, otherdata[0]);
                        ResultSet result = log.executeQuery();
                        log1.setString(1, userName);
                        ResultSet result1 = log1.executeQuery();
                        if(Flag == result.next()){
                            System.out.println("The user is already registered");
                            return false ;
                        }
                        else if(Flag == result1.next()){
                            System.out.println("username is already taken");
                            return false ;
                        }
                        else{
                            return true ; 
                        }
                    } catch (SQLException ex) {
                        System.out.println("Error in connection");
                    }
                }
                break;
            case 3:
                if(email.contains("@")&& validateNumericdata(otherdata[0]) <= 11){
                    String dbURL = "jdbc:mysql://localhost:3306/transportapp";
                    String user = "root";
                    String pass = "root" ;
                    boolean Flag = true ;
                    try {
                        Connection conn = DriverManager.getConnection(dbURL, user , pass);
                        String query = "Select * from driver where userName=? and passWord=? and eMail=? and mobilePhone=? and drivinglicense=? and nationalID=?" ;
                        PreparedStatement log = conn.prepareStatement(query);
                        log.setString(1, userName);
                        log.setString(2, passWord);
                        log.setString(3, email);
                        log.setLong(4, otherdata[0]);
                        log.setLong(5, otherdata[1]);
                        log.setLong(6, otherdata[2]);
                        ResultSet result = log.executeQuery();
                        String query1 = "Select * from driver where userName=?" ;
                        PreparedStatement log1 = conn.prepareStatement(query1);
                        log1.setString(1, userName);
                        ResultSet result1 = log1.executeQuery();
                        String query2 = "Select * from driver where drivinglicense=?" ;
                        PreparedStatement log2 = conn.prepareStatement(query2);
                        log2.setLong(1, otherdata[1]);
                        ResultSet result2 = log2.executeQuery();
                        String query3 = "Select * from driver where nationalID=?" ;
                        PreparedStatement log3 = conn.prepareStatement(query3);
                        log3.setLong(1, otherdata[2]);
                        ResultSet result3 = log3.executeQuery();
                        if(Flag == result.next()){
                            System.out.println("The user is already registered");
                            return false ;
                        }
                        else if(Flag == result1.next()){
                            System.out.println("username is already taken");
                            return false ;
                        }
                        else if(Flag == result2.next()){
                            System.out.println("This driving license is already registred");
                            return false;
                        }
                        else if(Flag == result3.next()){
                            System.out.println("This national ID is already registered");
                            return false;
                        }
                        else{
                            return true ; 
                        }
                    } catch (SQLException ex) {
                        System.out.println("Error in connection");
                    }
                }
                break;
        }
        return false ;
    }
    /**
     * Function to validate the numeric data
     * @param key
     * @return 
     */
    public int validateNumericdata(long key){
        int count = 0 ; 
        while (key != 0) {
        key /= 10;
        ++count;
       }
       return count+1 ;
    }
    /**
     * Function to add a new admin
     * @param username
     * @param passWord
     * @param email
     * @param otherdata 
     */
    
    
    
    
    /**
     * Function to login as an admin
     * @param username
     * @param passWord
     * @return 
     */
   
    /**
     * Function to return all admin data
     * @param key
     * @return 
     */
    @Override
    public Admin retriveAllinfo(String key) {
        String dbURL = "jdbc:mysql://localhost:3306/transportapp";
        String user = "root";
        String pass = "root" ;
        Admin A = new Admin("","",0,"");
         try {
            Connection conn = DriverManager.getConnection(dbURL, user , pass);
            String query = "Select * from admin where userName='"+key+"' " ;
            Statement log = conn.createStatement();
            ResultSet result = log.executeQuery(query);
            while(result.next()){
                A = new Admin(result.getString("userName"),result.getString("passWord"),result.getInt("mobilePhone"),
                result.getString("eMail"));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error in connection");
        }
        return A ;
    }
    /**
     * Function to suspend passenger or driver account
     * @param userName 
     */
    public void suspendAuser(String userName){
        
        System.out.println("hello");
        String dbURL = "jdbc:mysql://localhost:3306/transportapp";
        String user = "root";
        String pass = "root" ;
         try {
            Connection conn = DriverManager.getConnection(dbURL, user , pass);
            String queryDriver = "Delete from driver Where userName='"+userName+"'" ;
            String queryPassenger = "Delete from passenger Where userName = '"+userName+"'" ;
            Statement log1 = conn.createStatement();
            Statement log2 = conn.createStatement();
            log1.executeUpdate(queryDriver);
            log2.executeUpdate(queryPassenger); 
            System.out.println("The user's account is susppended suceessfully");
        } catch (SQLException ex) {
            System.out.println("Error in connection");
        }
    }
    
    public void Add_discount( String area ){
        this.area = area;
    }
    
    public String get_area(){
        return area;
    }
    
    public void add_event( Events event )
    {
        events.add(event);
    }
    
    public void show_event( String username )
    {
        if( events.size()==0 )
             System.out.println("No events");
        
        for ( int i=0 ; i<events.size() ; i++ )
        {
            if( events.get(i).getDriver().equals(username) )
            {
                System.out.println("event name " + events.get(i).getName() );
                System.out.println("event time " + events.get(i).getTime() );
                System.out.println("driver name " + events.get(i).getDriver() );
                System.out.println("passenger name " + events.get(i).getPassenger() );
                System.out.println(" price " + events.get(i).getPrice() );
                System.out.println("---------------------------------");
            }
        }
    }
}
