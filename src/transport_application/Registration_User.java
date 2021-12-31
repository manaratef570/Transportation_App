/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transport_application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hamza
 */
public class Registration_User {
   
    public void signUp(String username, String passWord, String email , long mobilePhone , String birthdate ) {
        String dbURL = "jdbc:mysql://localhost:3306/transportapp";
        String user = "root";
        String pass = "root" ;
        try {
            Connection conn = DriverManager.getConnection(dbURL, user , pass);
            Statement addUser = conn.createStatement();
            String query = "Insert Into passenger"+"(userName , passWord, eMail , mobilePhone , birthdate)"
            +"Values('"+username+"' , '"+passWord+"' , '"+email+"' , '"+mobilePhone+"' , '"+birthdate+"')" ;
            addUser.executeUpdate(query);
            System.out.println("You are registered successfully");
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
    }

    public boolean logIn(String userName, String passWord){
        String dbURL = "jdbc:mysql://localhost:3306/transportapp";
        String user = "root";
        String pass = "root" ;
        boolean Flag = false ;
        try {
            Connection conn = DriverManager.getConnection(dbURL, user , pass);
            String query = "Select * from passenger where userName=? and passWord=?" ;
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
