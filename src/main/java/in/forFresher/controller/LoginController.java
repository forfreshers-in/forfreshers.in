package in.forFresher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String showLoginPage(HttpServletResponse response) {
		response.setHeader("X-Frame-Options", "SAMEORIGIN");
		return "auth/login";
	}
	
}
