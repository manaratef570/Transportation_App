package transport_application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


//hamza
/*
 * Main Class
 */
public class TransportApp {
    /***
     * Function to display welcome menu to select the user role
     */
    public static void welcomeScreen(){
        System.out.println("***********************************************");
        System.out.println("Hello Sir, Welcome to our application");
        System.out.println("Enjoy with our services and go to your destination");
        System.out.println(" (1) Passenger");
        System.out.println(" (2) Driver");
        System.out.println(" (3) Admin");
        System.out.println("Your Choice: ");
        System.out.println("***********************************************");
    }
    
    /***
     * Function to select the registration option (Login - SignUp)
     */
    public static void registerationScreen(){
        System.out.println("***********************************************");
        System.out.println(" (1) Signup");
        System.out.println(" (2) Login");
        System.out.println("Your choice: ");
        System.out.println("***********************************************");
    }
    
    /**
     * Function to display the passenger operations menu
     */
    public static void passengerScreen(){
        System.out.println("***********************************************");
        System.out.println("(1) Request a trip");
        System.out.println("(2) LogOut");
        System.out.println("Your choice:");
        System.out.println("***********************************************");
    }
    /**
     * Function to display the driver operations menu
     */
    public static void driverScreen(){
        System.out.println("***********************************************");
        System.out.println("(1) View user's ratings");
        System.out.println("(2) Add a new favorite area/s");
        System.out.println("(3) change status ");
        System.out.println("(4) LogOut");
        System.out.println("***********************************************");
    }
    /**
     * Function to display the admin operations menu
     */
    public static void adminScreen(){
        System.out.println("***********************************************");
        System.out.println("(1) Verfying all pending drivers registrations");
        System.out.println("(2) Suspending an account");
        System.out.println("(3) Add a new admin");
        System.out.println("(4) add discount on specific area");
        System.out.println("(5) show events on specific ride");
        System.out.println("(6) LogOut");
        System.out.println("***********************************************");
    }
    
    public static void update_status(Driver driver)
    {
        String dbURL = "jdbc:mysql://localhost:3306/transportapp";
            String user = "root";
            String pass = "root" ;
            String query = "update driver set status = '"+driver.get_status()+"' where userName = '"+driver.getUserName()+"' ";
              try {
                   Connection conn = DriverManager.getConnection(dbURL, user , pass);
                   Statement logUpdate = conn.createStatement();
                   logUpdate.executeUpdate(query);
            
                    } catch (SQLException ex) {
                      System.out.println(ex);
                     }
                                     
    }
    
    public static boolean is_firstTrip( String userName )
    {
        String dbURL = "jdbc:mysql://localhost:3306/transportapp";
        String user = "root";
        String pass = "root" ;
        boolean Flag = false ;
        try {
            Connection conn = DriverManager.getConnection(dbURL, user , pass);
            String query = "Select * from trip where Passenger=? " ;
            PreparedStatement log = conn.prepareStatement(query);
            log.setString(1, userName);
            ResultSet result = log.executeQuery();
            Flag = result.next() ;
        } catch (SQLException ex) {
            System.out.println("Error in connection");
        }
        return Flag;
    }
    
    
    /**
     * Main Function
     * @param args 
     */
    public static void main(String[] args) throws ParseException{
        ArrayList<String> holidays = new ArrayList<String>();
        holidays.add("06/10");
        holidays.add("25/01");
        holidays.add("03/05");
        holidays.add("30/12");
        Admin admin = new Admin("admin" , "admin" , 19777 , "admin@1");//to manage system
        Registration_User PS = new Registration_User();
        Registration_Driver DR = new Registration_Driver();
        Registration_admin AD = new Registration_admin();
        Scanner Input = new Scanner(System.in);
        Scanner Input_admin = new Scanner(System.in);
        Queue<Driver> pendingDrivers = new LinkedList<>();
        int YorNo = 0 ;
        do{
        welcomeScreen();
        int selectedRole = Input.nextInt();
        switch (selectedRole) {
            case 1:
                registerationScreen();
                int selectedOption = Input.nextInt();
                switch(selectedOption){
                    case 1:
                        System.out.println("***Signup***");
                        System.out.println("Enter the username");
                        Input.nextLine();
                        String userName = Input.nextLine();
                        System.out.println("Enter the password");
                        String password = Input.nextLine();
                        System.out.println("Enter the eMail");
                        String eMail = Input.nextLine();
                        System.out.println("Enter the mobilePhone");
                        long mobilePhone = Input.nextLong();
                        System.out.println("Enter your birhdate as format ( \"dd-MM-yyyy\") ");
                        Scanner input2 = new Scanner(System.in);
                        String birthdate = input2.nextLine();
                        System.out.println("Wait, the admin will verfy your information.....");
                        if(admin.verfyDatauser(userName, password, eMail, mobilePhone)){
                            Passenger passenger = new Passenger(userName , password , mobilePhone , eMail , birthdate);
                            PS.signUp(userName, password, eMail, mobilePhone,birthdate);
                        }
                        System.out.println("Press (1) to continue and (0) to exit");
                        YorNo = Input.nextInt();
                        break;
                    case 2:
                        System.out.println("***Login***");
                        System.out.println("Enter the username");
                        Input.nextLine();
                        String username = Input.nextLine();
                        System.out.println("Enter the password");
                        String Password = Input.nextLine();
                        Passenger passenger = new Passenger("","",0,"","");
                        if(PS.logIn(username, Password)){
                            passenger = passenger.retriveAllinfo(username);
                            System.out.println("***Passenger Info***");
                            System.out.println("Username: "+passenger.getUserName());
                            System.out.println("Email: "+passenger.getEmail());
                            System.out.println("Phone: "+passenger.getMobilePhone());
                            System.out.println("date: "+passenger.get_birthdate());
                            System.out.println("******************************");
                            passengerScreen();
                            int selectedTask = Input.nextInt();
                            switch(selectedTask){
                                case 1:
                                    System.out.println("Enter your current location");
                                    Input.nextLine();
                                    String Source = Input.nextLine();
                                    System.out.println("Enter your destination");
                                    String Destination = Input.nextLine();
                                    System.out.println("Enter number of passenger ");
                                    Scanner input3 = new Scanner(System.in);
                                    int passenger_num = input3.nextInt();
                                    Ride ride ;
                                    ride = passenger.requestRide(Source, Destination);
                                    System.out.println("Enter your selected driver username");
                                    String selectedDriver = Input.nextLine();
                                    Driver driver = new Driver("","",0,"",0,0,0,"",0,0);
                                    driver = driver.retriveAllinfo(selectedDriver);
                                    String Sttime = "10:00AM";
                                    String endtime = "10:01AM";
                                    String loctime = "10:10AM";
                                    String Destime = "10:45Am";
                                    Events event1 = new Events("captin set price", Sttime , driver.getUserName() , null, driver.get_price());
                                    Events event2 = new Events("user accepts  price", endtime , driver.getUserName() , passenger.getUserName() , driver.get_price());
                                    driver.set_status(1);
                                    Events event3 = new Events("Driver arrived to the location of user", loctime , driver.getUserName() , passenger.getUserName() , driver.get_price());
                                    update_status( driver );
                                    
                                    System.out.println("You can call the driver via "+ driver.getMobilePhone());
                                    System.out.println("You trip is completed");
                                    Events event4 = new Events("Driver arrived to the Destination of user", Destime , driver.getUserName() , passenger.getUserName() , driver.get_price());
                                    admin.add_event(event1);
                                    admin.add_event(event2);
                                    admin.add_event(event3);
                                    admin.add_event(event4);
                                                                        
                                    System.out.println("Please rate "+driver.getUserName());
                                    int rate =  passenger.rateAdriver();
                                    float updated_price=driver.get_price();
                                    discount normal = new discount_No();
                                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                              
                                    LocalDate date = LocalDate.now();  
                                    String real_date = date.format(DateTimeFormatter.ISO_DATE);
                                    float discount=0;
                                    
                                    if( Destination.equals(admin.get_area()) )//and search in trip tabl and birthday
                                    {
                                        discount Discount = new discount_10();
                                        ride = new Ride( Source , Destination , driver.get_price() , Discount );
                                        discount=(  ride.getPrice() );
                                        System.out.println("its your lucky day .. your destination has 10% discount " );
 
                                    }
                                    
                                    
                                    if( passenger_num > 1  ) // 
                                    {
                                        discount Discount = new discount_5();
                                        ride = new Ride( Source , Destination , driver.get_price() , Discount );
                                        discount+=(  ride.getPrice() );
                                        System.out.println("because the number of passengers is more than 1 you got 5% discount ");
                                    }
                                    
                                    if( is_firstTrip(passenger.getUserName()) == false )
                                    {
                                        discount Discount = new discount_10();
                                        ride = new Ride( Source , Destination , driver.get_price() , Discount );
                                        discount+=(  ride.getPrice() );
                                        System.out.println("its yourfirst trip with us so you got 10% discount ");
                                    }
                                    
                                    for( int i=0 ; i<holidays.size() ; i++ )
                                    {                                       
                                        String[] date1 = real_date.split("-");
                                        String[] date2 = holidays.get(i).split("/");
                                        // 1-> month , 2-> dayes
                                       if( ( date1[1].equals(date2[1]) ) && ( date1[2].equals(date2[0]) ) ){
                                            discount Discount = new discount_5();
                                            ride = new Ride( Source , Destination , driver.get_price() , Discount );
                                            discount+=(  ride.getPrice() );
                                            System.out.println(" its public holiday so you get 5% discount " );
                                       }
                                        
                                    }
                                    
                                    float normalPrice=driver.get_price();
                                    driver.set_price(normalPrice - discount);
                                    System.out.println(" your total price =  "+ driver.get_price());

                                    
                                    
                                    if( passenger.get_birthdate().equals(real_date) )//and search in trip tabl and birthday
                                    {
                                        discount Discount = new discount_10();
                                        ride = new Ride( Source , Destination , driver.get_price() , Discount );
                                        driver.set_price(  ride.getPrice() );
                                        System.out.println("its your lucky day .. its your birthday you got 10% discount so your price  = " + driver.get_price());
 
                                    }
                                    
                                 
                                    ride.makeAtrip(passenger.getUserName(), driver.getUserName(), Source, Destination,driver.get_price(), rate);//was offer
                                    driver.updateAvgrating(driver.getUserName());
                                    System.out.println("Thanks");
                                    System.out.println("Press (1) to continue and (0) to exit");
                                    YorNo = Input.nextInt();
                                    break;
                                case 2:
                                    passenger.logOut();
                                    break;
                            }
                        }
                        break;
                }
                break;
            case 2:
                registerationScreen();
                int selectOption = Input.nextInt();
                switch(selectOption){
                    case 1:
                        System.out.println("***Signup***");
                        System.out.println("Enter the username");
                        Input.nextLine();
                        String userName = Input.nextLine();
                        System.out.println("Enter the password");
                        String password = Input.nextLine();
                        System.out.println("Enter the eMail");
                        String eMail = Input.nextLine();
                        System.out.println("Enter the mobilePhone");
                        long mobilePhone = Input.nextLong();
                        System.out.println("Enter the Driving license");
                        long drivingLicense = Input.nextLong();
                        System.out.println("Enter the nationalID");
                        long nationalID = Input.nextLong();
                        Driver driver = new Driver(userName , password , mobilePhone , eMail , drivingLicense , nationalID , 0 ,"",0,0);
                        pendingDrivers.add(driver);
                        System.out.println("Your Info will be verfy and we will notify you if your account is accepted");
                        System.out.println("Thank You");
                        System.out.println("Press (1) to continue and (0) to exit");
                        YorNo = Input.nextInt();
                        break;
                    case 2:
                        System.out.println("***Login***");
                        System.out.println("Enter the username");
                        Input.nextLine();
                        String UserName = Input.nextLine();
                        System.out.println("Enter the password");
                        String PassWord = Input.nextLine();
                        Driver driverr = new Driver("","",0,"",0,0,0,"",0,0);
                        if(DR.logIn(UserName, PassWord)){
                            driverr = driverr.retriveAllinfo(UserName);
                            System.out.println("***Driver Info***");
                            System.out.println("Username: "+driverr.getUserName());
                            System.out.println("Email: "+driverr.getEmail());
                            System.out.println("Phone: "+driverr.getMobilePhone());
                            System.out.println("******************************");
                            driverScreen();
                            int selectedTask = Input.nextInt();
                            switch(selectedTask){
                                case 1:
                                    driverr.viewUsersratings(driverr.getUserName());
                                    System.out.println("Press (1) to continue and (0) to exit");
                                    YorNo = Input.nextInt();
                                    break;
                                case 2:
                                    System.out.println("Enter the number of new areas");
                                    int numAreas = Input.nextInt();
                                    String Areas[] = new String[numAreas];
                                    Input.nextLine();
                                    for(int i = 0 ;i<numAreas ; i++){
                                        System.out.println("Enter the area name");
                                        String area = Input.nextLine();
                                        Areas[i] = area;
                                    }
                                    driverr.addFavareas(driverr.getUserName(), Areas);
                                    System.out.println("Press (1) to continue and (0) to exit");
                                    YorNo = Input.nextInt();
                                    break;
                                case 3:
                                    Scanner input = new Scanner(System.in);
                                    System.out.println("enter 0 if you want to be active ");
                                    System.out.println("enter 1 if you want to be disactive ");
                                    int choice = input.nextInt();
                                    driverr.change_status(choice);
                                    System.out.println("Press (1) to continue and (0) to exit");
                                    YorNo = Input.nextInt();
                                    break;
                                case 4:
                                    driverr.logOut();
                                    break;
                            }
                        }
                }
                break;
            case 3:
                System.out.println("***Login***");
                System.out.println("Enter the username");
                Input.nextLine();
                String userrName = Input.nextLine();
                System.out.println("Enter the password");
                String passsword = Input.nextLine();
                if(AD.logIn(userrName, passsword)){
                    Admin admin1 = new Admin("","",0,"") ;
                    admin1 = admin.retriveAllinfo(userrName);
                    System.out.println("******************************");
                    System.out.println("***Admin Info***");
                    System.out.println("Username: "+admin1.getUserName());
                    System.out.println("Email: "+admin1.getEmail());
                    System.out.println("Phone: "+admin1.getMobilePhone());
                    System.out.println("******************************");
                    adminScreen();
                    int selectedTask = Input.nextInt();
                    switch(selectedTask){
                        case 1:
                            System.out.println("Pending Drivers");
                            for(int i = 0 ; i<pendingDrivers.size() ; i++){
                                System.out.println("**************************************");
                                System.out.println("User Name: "+pendingDrivers.element().getUserName());
                                System.out.println("PassWord: "+pendingDrivers.element().getPassword());
                                System.out.println("Email: "+pendingDrivers.element().getEmail());
                                System.out.println("Phone: "+pendingDrivers.element().getMobilePhone());
                                System.out.println("National ID: "+pendingDrivers.element().getnationalID());
                                System.out.println("Driving License: "+pendingDrivers.element().getDrivinglicense());
                                System.out.println("**************************************");
                                if(admin.verfyDatauser(pendingDrivers.element().getUserName(), pendingDrivers.element().getPassword(),
                                pendingDrivers.element().getEmail(), pendingDrivers.element().getMobilePhone() ,
                                pendingDrivers.element().getDrivinglicense() , pendingDrivers.element().getnationalID())){
                                  DR.signUp(pendingDrivers.element().getUserName(), pendingDrivers.element().getPassword(),pendingDrivers.element().getEmail(),
                                  pendingDrivers.element().getMobilePhone(),pendingDrivers.element().getDrivinglicense(),pendingDrivers.element().getnationalID());
                                }
                                else{
                                    return;
                                }
                            }
                            System.out.println("All pending requestes are accepted");
                            System.out.println("Press (1) to continue and (0) to exit");
                            YorNo = Input.nextInt();
                            break;
                        case 2:
                            System.out.println("Enter the username");
                            String reporteduser = Input_admin.nextLine();
                            admin.suspendAuser(reporteduser);
                            System.out.println("Press (1) to continue and (0) to exit");
                            YorNo = Input.nextInt();
                            break;
                        case 3:
                            System.out.println("Enter the username");
                            String username = Input_admin.nextLine();
                            System.out.println("Enter the passWord");
                            String passWord = Input_admin.nextLine();
                            System.out.println("Enter the mobilePhone");
                            long mobilephone = Input_admin.nextLong();
                            System.out.println("Enter the eMail");
                            String email = Input_admin.nextLine();
                            AD.signUp(username, passWord, email, mobilephone);
                            System.out.println("Press (1) to continue and (0) to exit");
                            YorNo = Input.nextInt();
                            break;
                        case 4 :
                            System.out.println("Enter the area");
                            String area = Input_admin.nextLine();
                            admin.Add_discount(area);
                            System.out.println("Press (1) to continue and (0) to exit");
                            YorNo = Input.nextInt();
                            break;
                        case 5 :
                            System.out.println("enter name of driver to see his events");
                            Scanner input = new Scanner(System.in);
                            String driverName = input.nextLine();
                            admin.show_event(driverName);
                            System.out.println("Press (1) to continue and (0) to exit");
                            YorNo = Input.nextInt();
                            break;
                        case 6:
                            admin.logOut();
                            break;
                    }
                }
                else{
                    System.out.println("The username or password is wrong");
                }
                break;
            default:
                break;
        }
        }while(YorNo == 1);
    }
   }
