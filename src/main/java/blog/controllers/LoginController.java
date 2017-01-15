package blog.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import blog.forms.LoginForm;
import blog.services.NotificationService;
import blog.services.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NotificationService notifyService;
	
	@RequestMapping("/users/login")
	public String login(LoginForm loginForm)
	{
		return "login";
	}
	
	@RequestMapping(value="/users/login", method=RequestMethod.POST)
	public String loginPage(@Valid LoginForm loginForm, BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
		{
			notifyService.addErrorMessage("Please fill out the form correctly");
			return "login";
		}
		
		if(!userService.authenticate(
				
				loginForm.getUsername(), loginForm.getPassword()))
		{
			notifyService.addErrorMessage("Invalid Login!");
			return "login";
		}
		
		notifyService.addInfoMessage("Login Successful");
		return "redirect:/";
	}

}
