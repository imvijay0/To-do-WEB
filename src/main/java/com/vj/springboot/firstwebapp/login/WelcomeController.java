package com.vj.springboot.firstwebapp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {
	
	 
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String WelcomePage(ModelMap model)
	
	{
		
		model.put("name", getLoggedInUsername());
		return "Welcome";
		
	}
	
	
	private String getLoggedInUsername()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		return authentication.getName();
		
	}
	
	
	
	
}
