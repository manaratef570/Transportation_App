package transport_application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


public class Driver extends User {
    
    private final long drivinglicense ;
    private final long nationalID ;
    private final float avgRating ;
    private final String favoriteAreas ;
    
    /**
     * Parameterized Constructor
     * @param userName
     * @param password
     * @param mobilePhone
     * @param Email
     * @param drivinglicense
     * @param nationalID
     * @param avgRating
     * @param favoriteAreas 
     */
    public Driver(String userName, String password, long mobilePhone, String Email, long drivinglicense , long nationalID ,
    float avgRating , String favoriteAreas) {
        super(userName, password, mobilePhone, Email);
        this.drivinglicense = drivinglicense ;
        this.nationalID = nationalID ;        
        this.avgRating = avgRating ;
        this.favoriteAreas = favoriteAreas ;
    }
    /**
     * Get Functions
     * @return 
     */
    public long getDrivinglicense(){
        return drivinglicense ;
    }
    
    public long getnationalID(){
        return nationalID ;
    }
    
    public float getRating(){
        return avgRating ;
    }
    
    public String[] getAreas(){
        return favoriteAreas.split("-");
    }
    /**
     * Function to signUp as a Driver
     * @param username
     * @param passWord
     * @param email
     * @param otherdata 
     */
    @Override
    public void signUp(String username, String passWord, String email, long... otherdata) {
        String dbURL = "jdbc:mysql://localhost:3306/transportapp";
        String user = "root";
        String pass = "root" ;
        try {
            Connection conn = DriverManager.getConnection(dbURL, user , pass);
            Statement addUser = conn.createStatement();
            String query = "Insert Into driver"+"(userName , passWord, eMail , mobilePhone ,drivinglicense , nationalID )"
            +"Values('"+username+"' , '"+passWord+"' , '"+email+"' , '"+otherdata[0]+"' , '"+otherdata[1]+"' , '"+otherdata[2]+"')" ;
            addUser.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("Error in connection");
        } 
    }
    /**
     * Function to suggest a price for a trip
     * @return 
     */
    public int offer(){
        Random rand = new Random(); 
        int price = rand.nextInt(300);
        return price;
    }
    /**
     * Function to update the average rating for a driver
     * @param driverName 
     */
    public void updateAvgrating(String driverName){
        String dbURL = "jdbc:mysql://localhost:3306/transportapp";
        String user = "root";
        String pass = "root" ;
        double p = 0;
        try {
            Connection conn = DriverManager.getConnection(dbURL, user , pass);
            String query = "Select AVG(Rating) as AVGRATING from trip where Driver='"+driverName+"'" ;
            Statement log = conn.createStatement();
            ResultSet result = log.executeQuery(query);
            while(result.next()){
                p = result.getDouble("AVGRATING");
            }
            String updateQuery = "Update driver set avgRating='"+p+"' Where userName ='"+driverName+"'";
            Statement logUpdate = conn.createStatement();
            logUpdate.executeUpdate(updateQuery);
            
        } catch (SQLException ex) {
            System.out.println("Error in connectionn");
        }
    }
    /**
     * Function to login as a driver
     * @param userName
     * @param passWord
     * @return 
     */
    @Override
     public boolean logIn(String userName, String passWord){
        String dbURL = "jdbc:mysql://localhost:3306/transportapp";
        String user = "root";
        String pass = "root" ;
        boolean Flag = false ;
        try {
            Connection conn = DriverManager.getConnection(dbURL, user , pass);
            String query = "Select * from driver where userName=? and passWord=?" ;
            PreparedStatement log = conn.prepareStatement(query);
            log.setString(1, userName);
            log.setString(2, passWord);
            ResultSet result = log.executeQuery();
            Flag = result.next() ;
        } catch (SQLException ex) {
            System.out.println("Error in connection");
        }
        return Flag;
    }
     /**
      * Function to return all driver data
      * @param key
      * @return 
      */
    @Override
    public  Driver retriveAllinfo(String key) {
        String dbURL = "jdbc:mysql://localhost:3306/transportapp";
        String user = "root";
        String pass = "root" ;
        Driver D = new Driver("","",0,"",0,0,0,""); ;
         try {
            Connection conn = DriverManager.getConnection(dbURL, user , pass);
            String query = "Select * from driver where userName='"+key+"' " ;
            Statement log = conn.createStatement();
            ResultSet result = log.executeQuery(query);
            while(result.next()){
                D = new Driver(result.getString("userName"),result.getString("passWord"),result.getInt("mobilePhone"),
                result.getString("eMail"),result.getInt("drivinglicense"),result.getInt("nationalID"),
                result.getFloat("avgRating"),result.getString("favAreas"));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error in connection");
        }
        return D ;
    }
    /**
     * Function to view all users ratings for a specific driver
     * @param driverUser 
     */
    public  void viewUsersratings(String driverUser){
        String dbURL = "jdbc:mysql://localhost:3306/transportapp";
        String user = "root";
        String pass = "root" ;
        try {
            Connection conn = DriverManager.getConnection(dbURL, user , pass);
            String query = "Select * from trip where Driver='"+driverUser+"'" ;
            Statement log = conn.createStatement();
            ResultSet result = log.executeQuery(query);
            System.out.println("Users Ratings");
            while(result.next()){
                System.out.println("User: "+result.getString("Passenger")+"   "+"Rating: "+result.getString("Rating"));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error in connection");
        }
    }
    /**
     * Function to add some favorite areas for a driver
     * @param userName
     * @param Areas 
     */
    public void addFavareas(String userName ,String [] Areas){
        String dbURL = "jdbc:mysql://localhost:3306/transportapp";
        String user = "root";
        String pass = "root" ;
        String list = "" ;
        String list2 = "" ;
        String newlist = "" ;
        try {
            Connection conn = DriverManager.getConnection(dbURL, user , pass);
            Statement addArea = conn.createStatement();
            String query = "Select favAreas from driver where userName = '"+userName+"'";
            ResultSet result = addArea.executeQuery(query);
            while(result.next()){
                list = result.getString("favAreas");
            }
            for (String Area : Areas) {
                list2 = Area + "-" ;
            }
            String newquery = "Select CONCAT('"+list+"' , '"+list2+"') AS newList from driver where userName = '"+userName+"'";
            ResultSet newresult = addArea.executeQuery(newquery);
            while(newresult.next()){
                newlist = newresult.getString("newList");
            }
            String queryUpdate = "Update driver Set favAreas = '"+newlist+"' Where userName ='"+userName+"'";
            addArea.executeUpdate(queryUpdate);
            System.out.println("The areas is added");
        } catch (SQLException ex) {
            System.out.println("Error in connection");
        } 
    }
}