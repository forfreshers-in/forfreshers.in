package in.forFresher.apiController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.forFresher.services.MailService;

import in.forFresher.util.ValidationUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ContactAPIController {

	@Autowired
	private MailService emailService;

	@PostMapping("/contact")
	public ResponseEntity<Object> handleContactForm(@RequestParam("name") String name,
			@RequestParam("email") String email, @RequestParam("message") String message, HttpServletRequest request) {
		if(name == null || name.isBlank()) {
			return ResponseEntity.internalServerError().body("name is not valid");
		}
		if(email == null || email.isBlank() || ! ValidationUtil.isValidEmail(email)) {
			return ResponseEntity.internalServerError().body("email is not valid");
		}
		if(message == null || message.isBlank()) {
			return ResponseEntity.internalServerError().body("message is not valid");
		}
		
		String referer = request.getHeader("Referer");
		try {
			if (referer != null && referer.startsWith("https://forfreshers.in")) {
				String subject = "New Contact Form Submission from " + name;
				String emailText = "Name: " + name + "\nEmail: " + email + "\n\nMessage:\n" + message;
				emailService.sendSimpleMessage("forfreshers3@gmail.com", subject, emailText);

				return ResponseEntity.ok().build();
			} else {
				return ResponseEntity.badRequest().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("");
		}

	}
}