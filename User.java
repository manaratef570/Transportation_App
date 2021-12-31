package transport_application;

/**
 * Abstract Class
 * @author Abdelrahman
 */
public abstract class User {
    
    private final String userName  ;
    private final String password ;
    private final long mobilePhone ;
    private final String Email;
   
    /**
     * Parameterized Constructor
     * @param userName
     * @param password
     * @param mobilePhone
     * @param Email 
     */
    User(String userName , String password , long mobilePhone , String Email){
        this.userName = userName ;
        this.password = password ;
        this.mobilePhone = mobilePhone;
        this.Email = Email ;
    }
    /**
     * Get Functions
     * @return 
     */
    public String getUserName(){
        return userName ;
    }
    
    public String getPassword(){
        return password ;
    }
    
    public String getEmail(){
        return Email;
    }
    
    public long getMobilePhone(){
        return mobilePhone;
    }
    /**
     * Function to logOut system.
     */
    public void logOut(){
        System.out.println("***********************************");
        System.out.println("Thank you for using our Application");
        System.out.println("***********************************");
    }
    /**
     * Abstract function to signup
     * @param username
     * @param passWord
     * @param email
     * @param otherdata 
     */
    
    /*
    abstract public void signUp(String username , String passWord , String email ,  long ... otherdata);
    /**
     * Abstract Function to Login
     * @param username
     * @param passWord
     * @return 
     */
    
    
   // abstract public  boolean logIn(String username , String passWord);
    /**
     * Abstract Function to return all data
     * @param key
     * @return 
     */
    abstract public User retriveAllinfo(String key);

}
