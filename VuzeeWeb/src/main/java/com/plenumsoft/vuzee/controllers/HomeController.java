package com.plenumsoft.vuzee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value= {"/"})
public class HomeController {
	String prefix = "home/";
	
	@RequestMapping(value="/")
	public String index() {
		
		return "index";
	}
	
}
