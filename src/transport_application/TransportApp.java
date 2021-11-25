package transport_application;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

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
        System.out.println("(3) LogOut");
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
        System.out.println("(4) LogOut");
        System.out.println("***********************************************");
    }
    /**
     * Main Function
     * @param args 
     */
    public static void main(String[] args){
        Scanner Input = new Scanner(System.in);
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
                        System.out.println("Wait, the admin will verfy your information.....");
                        Admin admin = new Admin("admin" , "admin" , 19777 , "admin@1");
                        if(admin.verfyDatauser(userName, password, eMail, mobilePhone)){
                            Passenger passenger = new Passenger(userName , password , mobilePhone , eMail);
                            passenger.signUp(userName, password, eMail, mobilePhone);
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
                        Passenger passenger = new Passenger("","",0,"");
                        if(passenger.logIn(username, Password)){
                            passenger = passenger.retriveAllinfo(username);
                            System.out.println("***Passenger Info***");
                            System.out.println("Username: "+passenger.getUserName());
                            System.out.println("Email: "+passenger.getEmail());
                            System.out.println("Phone: "+passenger.getMobilePhone());
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
                                    passenger.requestRide(Source, Destination);
                                    System.out.println("Enter your selected driver username");
                                    String selectedDriver = Input.nextLine();
                                    Driver driver = new Driver("","",0,"",0,0,0,"");
                                    driver = driver.retriveAllinfo(selectedDriver);
                                    System.out.println("You can call the driver via "+ driver.getMobilePhone());
                                    System.out.println("You trip is completed");
                                    System.out.println("Please rate "+driver.getUserName());
                                    int rate =  passenger.rateAdriver();
                                    passenger.makeAtrip(passenger.getUserName(), driver.getUserName(), Source, Destination, driver.offer() , rate);
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
                        Driver driver = new Driver(userName , password , mobilePhone , eMail , drivingLicense , nationalID , 0 ,"");
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
                        Driver driverr = new Driver("","",0,"",0,0,0,"");
                        if(driverr.logIn(UserName, PassWord)){
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
                Admin admin = new Admin("","",0,"");
                if(admin.logIn(userrName, passsword)){
                    admin = admin.retriveAllinfo(userrName);
                    System.out.println("******************************");
                    System.out.println("***Admin Info***");
                    System.out.println("Username: "+admin.getUserName());
                    System.out.println("Email: "+admin.getEmail());
                    System.out.println("Phone: "+admin.getMobilePhone());
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
                                    pendingDrivers.element().signUp(pendingDrivers.element().getUserName(), pendingDrivers.element().getPassword(),pendingDrivers.element().getEmail(),
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
                            String reporteduser = Input.nextLine();
                            admin.suspendAuser(reporteduser);
                            System.out.println("Press (1) to continue and (0) to exit");
                            YorNo = Input.nextInt();
                            break;
                        case 3:
                            System.out.println("Enter the username");
                            String username = Input.nextLine();
                            System.out.println("Enter the passWord");
                            String passWord = Input.nextLine();
                            System.out.println("Enter the mobilePhone");
                            long mobilephone = Input.nextLong();
                            System.out.println("Enter the eMail");
                            String email = Input.nextLine();
                            admin.signUp(username, passWord, email, mobilephone);
                            System.out.println("Press (1) to continue and (0) to exit");
                            YorNo = Input.nextInt();
                            break;
                        case 4:
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
