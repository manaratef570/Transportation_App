package transport_application;

public abstract class User {
    
    private String userName ;
    private String password ;
    private long mobilePhone ;
    private String Email;
    
    public void setUsername (String userName){
        this.userName = userName ;
    }
    
    public void setPassword(String password){
        this.password = password ;
    }
    
    public void setMobilephone(long mobilePhone){
        this.mobilePhone = mobilePhone;
    }
    
    public void setEmail(String Email){
        this.Email = Email ;
    }
    
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
    
    abstract public void signUp(String username , String passWord , String email ,  long ... otherdata);
    
    abstract public boolean logIn(String username , String passWord);
}
