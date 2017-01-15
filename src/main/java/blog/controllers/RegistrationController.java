package blog.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import blog.forms.RegistrationForm;
import blog.services.NotificationService;
import blog.services.RegistrationService;

@Controller
public class RegistrationController {
	
	@Autowired
	NotificationService notifyService;
	
	@Autowired
	RegistrationService registrationService;
	
	@RequestMapping("/users/register")
	public String registration(RegistrationForm registrationForm)
	{
		return "register";
	}
	
	@RequestMapping(value = "/users/register", method=RequestMethod.POST)
	public String registrationPage(@Valid RegistrationForm registrationForm,
										  BindingResult bindingResult)
	{
		
		if (!registrationService.register(
				registrationForm.getUsername(),
				registrationForm.getPassword(), 
				registrationForm.getVerifyPassword(),
				registrationForm.getFullName()))
		{
			notifyService.addErrorMessage("Invalid Registration Form");
			return "register";
		}
		
		if(bindingResult.hasErrors())
		{
			notifyService.addErrorMessage("Please fill out the form correctly!");
			return "register";
		}
		
		
		notifyService.addInfoMessage("Registration Successful!");
		return "redirect:/";
	}
	

}
