package in.forFresher.apiController;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.forFresher.entity.Users;
import in.forFresher.repository.UsersRepository;
import in.forFresher.services.MailService;
import in.forFresher.util.ValidationUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

	private UsersRepository userRepository;

	private MailService mailService;

	public AuthApiController(UsersRepository userRepository, MailService mailService) {
		this.userRepository = userRepository;
		this.mailService = mailService;
	}

	/**
     * Method that used to reset password with reset token and new password
     * @param request contain token and new password
     * @return
     */
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String newPassword = request.get("newPassword");

        Users user = userRepository.findByResetToken(token).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid token");
        }
        
        if(newPassword==null && (!ValidationUtil.isValidPassword(newPassword)) ) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("password not exist correctly");
        }

        // Check if token has expired
        if (user.getResetTokenExpiry().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token has expired");
        }

        // Update the user's password (hash it before saving)
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        user.setResetToken(null);  // Clear the reset token
        user.setResetTokenExpiry(null);  // Clear the expiry time
		try {
			userRepository.save(user);
		}catch(Exception e) {	
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("We cant store your new password");
		}
        
        //send email like password changed successfully
        try {
            mailService.sendPasswordChangeConfirmationEmail(user.getEmail(), user.getName());
        } catch (Exception e) {
            System.err.println("Failed to send confirmation email");
        }


        return ResponseEntity.ok("Password reset successful");
    }
}
