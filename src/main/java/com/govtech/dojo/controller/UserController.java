package com.govtech.dojo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.govtech.dojo.model.User;
import com.govtech.dojo.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;


	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView loginForm() {
		ModelAndView model = new ModelAndView();
		model.setViewName("user/login");
		return model;
	}	

	@RequestMapping(value = { "/signup" }, method = RequestMethod.GET)
	public ModelAndView signupForms() {
		ModelAndView model = new ModelAndView();
		model.setViewName("user/welcome");
		return model;
	}
	
	/**
	 * add password check logic:
	 * When I signup with for a new user account. And I key
	 * in a password which is too short. Then I should see an alert telling me my
	 * password is too short. Password must be at least 12 characters in length.
	 */
	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	public ModelAndView createAccount(@Valid User user, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		
		//password check code goes here
		User userExists = userService.findUserByEmail(user.getEmail());
		
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "This email already exists!");
		}
		if (bindingResult.hasErrors()) {
			model.setViewName("user/signup");
		} else {
			userService.saveUser(user);
			model.addObject("msg", "User has been registered successfully!");
			model.addObject("user", new User());
			model.setViewName("user/welcome");
		}
		
		return model;
	}
	
	
	
	@RequestMapping(value = { "/users" }, method = RequestMethod.POST)
	public ModelAndView loginPage(@Valid User user, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		
		if (userExists == null) {
			bindingResult.rejectValue("email", "error.user", "This account doesn't exists!");
		} else {
			model.setViewName("user/welcome");
		}
		
		return model;
	}
	
	
	@RequestMapping(value = { "/users/welcome" }, method = RequestMethod.GET)
	public ModelAndView welcome() {
		ModelAndView model = new ModelAndView();

		model.setViewName("user/welcome");
		return model;
	}	
	
	
	@RequestMapping(value = { "/users/logout" }, method = RequestMethod.GET)
	public ModelAndView logout() {
		ModelAndView model = new ModelAndView();

		model.setViewName("home/index");
		return model;
	}		
	
	

	@RequestMapping(value = { "/access_denied" }, method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		ModelAndView model = new ModelAndView();
		model.setViewName("errors/access_denied");
		return model;
	}
}