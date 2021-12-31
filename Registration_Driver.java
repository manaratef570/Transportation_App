/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transport_application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hamza
 */
public class Registration_Driver {
    
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
    
}
