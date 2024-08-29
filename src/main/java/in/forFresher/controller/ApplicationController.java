package in.forFresher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {
	
	@GetMapping(value = "/")
	public String getHome(Model model) {
		model.addAttribute("name", "saravanan");
		return "index";
	}
	
	@GetMapping(value = "/about")
	public String getAbout(Model model) {
		model.addAttribute("active_nav","about");
		return "about";
	}
	@GetMapping(value = "/community")
	public String getCommunity(Model model) {
		model.addAttribute("active_nav","community");
		return "community";
	}
	
	@GetMapping(value = "/features")
	public String getFeatures(Model model) {
		model.addAttribute("active_nav","features");
		return "features";
	}
	@GetMapping(value = "/contact")
	public String getContact(Model model) {
		model.addAttribute("active_nav","contact");
		return "contact";
	}
	@GetMapping(value = "/cookies-policy")
	public String getCookiesPolicy(Model model) {
		model.addAttribute("active_nav","cookies-policy");
		return "cookie-policy";
	}
	@GetMapping(value = "/terms-and-condition")
	public String geTermsAndCondition(Model model) {
		model.addAttribute("active_nav","terms-and-condition");
		return "terms-and-condition";
	}
	@GetMapping(value = "/privacy-policy")
	public String getPrivacyPolicy(Model model) {
		model.addAttribute("active_nav","privacy-policy");
		return "privacy-policy";
	}
}
