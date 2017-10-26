package com.plenumsoft.vuzee.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.plenumsoft.vuzee.security.AuthenticationUser;
import com.plenumsoft.vuzee.security.EmailExistsException;
import com.plenumsoft.vuzee.security.VuzeeUser;
import com.plenumsoft.vuzee.security.VuzeeUserService;
import com.plenumsoft.vuzee.security.viewmodels.RegistrationUserViewModel;

@Controller
public class AccountController {
	VuzeeUserService vuzeeUserService;

	@Autowired
	public AccountController(VuzeeUserService userService) {
		this.vuzeeUserService = userService;
	}
	
	@RequestMapping(value = "/account/login")
	public ModelAndView  login(Model model) {
		 ModelAndView mav = new ModelAndView();
		 mav.setViewName("account/login");
		 mav.addObject("error","true");
		 mav.addObject("authenticationUser", new AuthenticationUser());
		return mav;
	}
	
	
	@RequestMapping(value = "/account/register", method=RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("registrationUser", new RegistrationUserViewModel());
		return "account/register";
	}
	
	@RequestMapping(value = "/account/register", method=RequestMethod.POST)
	public String register(@Valid RegistrationUserViewModel registrationUser, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("registrationUser", registrationUser);
            return "account/register";
        }
		VuzeeUser vuzeeUser = new VuzeeUser();
		
		vuzeeUser.setEmail(registrationUser.getEmail());
		vuzeeUser.setFirstName(registrationUser.getFirstName());
		vuzeeUser.setLastName(registrationUser.getLastName());
		vuzeeUser.setPassword(registrationUser.getPassword());
		vuzeeUser.setRepeatPassword(registrationUser.getRepeatPassword());
		
		try {
			vuzeeUserService.registerNewAccount(vuzeeUser);
		} catch (EmailExistsException e) {
			return register(model);
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/account/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/account/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	
}
