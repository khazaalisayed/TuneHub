package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entities.LoginData;
import com.example.demo.entities.Users;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@Controller
public class UsersController {
	@Autowired
	UsersService service;
	
	@PostMapping("/register")
	public String addUsers(@ModelAttribute Users user) {
		boolean userStatus = service.emailExists(user.getEmail());
		if(userStatus == false) {
		service.addUser(user);
		System.out.print("user added");		
		}
		else{
			System.out.println("User already exist");
		}
		return "home";
		
	}
	@PostMapping("/validate")
	public String validate(@RequestBody LoginData data
			,HttpSession session, Model model)
		
	
	{
		String email=data.getEmail();
		 String password=data.getPassword();
		 System.out.print("Call received");
		if(service.validateUser(email,password)== true)
		{
		
			
		String role= service.getRole(email);
		session.setAttribute("email", email);	
		if(role.equals("admin"))
			{
			return "adminHome";
			}
			else
			{
				boolean isprem= service.getIsPremimum(email);
				if(isprem == true)
					return "customerPremiumHome";
				else
					return "customerHome";
			}
		}
		else
		{
			return "login";
		}
		
	}
	

	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "login";
	}
	{
		
	}
	
}