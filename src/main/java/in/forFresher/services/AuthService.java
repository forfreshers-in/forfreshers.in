package in.forFresher.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import in.forFresher.entity.Users;
import in.forFresher.repository.UsersRepository;
import in.forFresher.util.ValidationUtil;

@Service
public class AuthService {
	
	private UsersRepository userRepo;
	private MailService mailService;
	
	public AuthService(UsersRepository userRepo, MailService mailService) {
		this.userRepo = userRepo;
		this.mailService = mailService;
	}

	/**
     * Method generate reset token and stored at user database
     * @param user
     * @return generated token as String
     * @author Saravanan Raja
     */
    public String generateResetToken(Users user) {
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);

        // Set expiry time to 1 hour from now
        user.setResetTokenExpiry(LocalDateTime.now().plusHours(24));

        userRepo.save(user);
        return token;
    }
    
    public String resetPassword(String email) throws Exception {
    	if(! ValidationUtil.isValidEmail(email)) {
    		throw new Exception("Invalid email address");
    	}
    	try {
    		Users user = userRepo.findByEmail(email);
    		if(user == null) {
    			throw new Exception("We are tired to search your email :(");
    		}
    		String token = generateResetToken(user);
    		String resetLink = "https://forfreshers.in/reset-new-password?pwrtok="+token;
    		mailService.sendPasswordResetEmail(email, resetLink, user.getName());
    	}catch(Exception e) {
    		throw new Exception("Oops Something went wrong, Check your mail");
    	}
    	return "Check your inbox :)";
    }
}
