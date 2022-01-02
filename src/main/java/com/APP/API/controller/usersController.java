package com.APP.API.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.APP.API.transport_application.Admin;
import com.APP.API.transport_application.Driver;
import com.APP.API.transport_application.Passenger;
import com.APP.API.transport_application.Registration_Driver;
import com.APP.API.transport_application.Registration_User;
import com.APP.API.transport_application.Registration_admin;
import com.APP.API.transport_application.Ride;

@RestController
public class usersController {
	Admin admin =new Admin("admin", "admin", 0114, "123@sd");
	
	@PostMapping("/passengerLogin")
	private boolean passengerLogin(@RequestBody Passenger p) 
	{
		Registration_User r=new Registration_User();
		
	return r.logIn(p.getUserName(),  p.getPassword());
	}
	@PostMapping("/passengerSignup")
	private boolean passengerSignup(@RequestBody Passenger p) 
	{
		Registration_User r=new Registration_User();
	
		if(admin.verfyDatauser(p.getUserName(), p.getPassword(), p.getEmail(), p.getMobilePhone())) {
			
		   r.signUp(p.getUserName(), p.getPassword(), p.getEmail(), p.getMobilePhone(),p.get_birthdate());
		   return true;
		   }
		else {
			return false;
		}
	
	}
	/*
	@GetMapping("/requestRide")
	private Ride requestRide(@RequestBody Passenger p) 
	{
		 Ride ride;
	return ride.requestRide();
	}*/
	@GetMapping("/RetriveAllinfo")
	private Passenger retriveAllinfo(@RequestBody Passenger p) 
	{
		
	return p.retriveAllinfo(p.getUserName());
	}

		@PostMapping("/RequestRide")
		private Ride requestRide(@RequestBody Passenger p) 
		{
		 
			
		return p.requestRide( p.getSource() ,  p.getDestination(),p.getUserName());
		}
	
	//Admin
	@PostMapping("/AdminLogin")
	private boolean AdminLogin(@RequestBody Admin p) 
	{
		Registration_admin r=new Registration_admin();
		
	return r.logIn(p.getUserName(), p.getPassword()) ;
	}
	@GetMapping("/RetriveAllinfoAdmin")
	private Admin RetriveAllinfoAdmin(@RequestBody Admin p) 
	{
		
	return p.retriveAllinfo(p.getUserName());
	}
	
	@PostMapping("/SuspendAuser")
	private boolean suspendAuser(@RequestBody Passenger p) 
	{
      admin.suspendAuser(p.getUserName());
	
	return true;
	}
	//signup admin
	@PostMapping("/AdminSignup")
	private boolean AdminSignup(@RequestBody Admin p) 
	{
		
		Registration_admin r=new Registration_admin();
	     r.signUp(p.getUserName(), p.getPassword(), p.getEmail(), p.getMobilePhone());
		 
			return true;
	
	}
	@PostMapping("/PendingDrivers")
	private boolean PendingDrivers(@RequestBody Driver p) 
	{
		Registration_Driver r=new Registration_Driver();
		
		if(admin.verfyDatauser(p.getUserName(), p.getPassword(), p.getEmail(), p.getMobilePhone(),p.getDrivinglicense(),p.getnationalID())) {
			
		   r.signUp(p.getUserName(), p.getPassword(), p.getEmail(), p.getMobilePhone(),p.getDrivinglicense(),p.getnationalID());
		   return true;
		   }
		else {
			return false;
		}
	
	}
	//Driver
	
	
	@PostMapping("/DriverLogin")
	private boolean DriverLogin(@RequestBody Driver p) 
	{
		Registration_Driver r=new Registration_Driver();
		
	return r.logIn(p.getUserName(), p.getPassword()) ;
	}
	
	@PostMapping("/DriverSignup")
	private boolean DriverSignup(@RequestBody Driver p) 
	{
		Registration_Driver r=new Registration_Driver();
	
		if(admin.verfyDatauser(p.getUserName(), p.getPassword(), p.getEmail(), p.getMobilePhone())) {
			
		   r.signUp(p.getUserName(), p.getPassword(), p.getEmail(), p.getMobilePhone(),p.getDrivinglicense(),p.getnationalID());
		   return true;
		   }
		else {
			return false;
		}
	
	}
	
	@PostMapping("/AddFavareas")
	private boolean AddFavareas(@RequestBody Driver p) 
	{
		
		String[] s = new String[1];
		s[0]= p.getFavoriteAreas();
		p.addFavareas(p.getUserName(), s);
		return true;
	}
	@PostMapping("/changeStatus")
	private boolean changeStatus(@RequestBody Driver p) 
	{
		p.change_status(p.get_status(),p.getUserName()) ;
		
	return true;
	}
	
	@GetMapping("/viewUsersratings")
	private boolean viewUsersratings(@RequestBody Driver p) 
	{
		p.viewUsersratings(p.getUserName());
		
	return true;
	}
	 
	
	

}
