package in.forFresher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {
	
	@GetMapping(value = "/")
	public String getHome(Model model) {
		model.addAttribute("name", "saravanan");
		System.out.println(" base method called");
		return "index";
	}
	
	@GetMapping(value = "/about")
	public String getAbout() {
		return "aboutPage";
	}
	@GetMapping(value = "/contact")
	public String getContact() {
		return "contactPage";
	}
	
	@GetMapping(value = "/privacy-policy")
	public String getPrivacyPolicy() {
		return "privacy-policy";
	}
}
