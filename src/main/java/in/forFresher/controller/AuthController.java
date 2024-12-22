package in.forFresher.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import in.forFresher.entity.Users;
import in.forFresher.repository.UsersRepository;
import in.forFresher.services.AuthService;

@Controller
public class AuthController {
	
	private ModelAndView mv;
	private AuthService authService;
	private UsersRepository usersRepo;
	
	@Autowired
	public AuthController(AuthService authService, UsersRepository usersRepo) {
		this.authService = authService;
		this.usersRepo = usersRepo;
	}
	
	@GetMapping("/request-password-reset")
	public ModelAndView requestPasswordReset(@RequestParam(required = false) String email) {
		mv = new ModelAndView();
		mv.setViewName("auth/reset-password");
		if(email != null) {
			try {
				String message = authService.resetPassword(email);
				mv.addObject("success", message);
			}catch(Exception e) {
				mv.addObject("error", e.getMessage());
			}
		}
		return mv;
	}
	/**
	 * 
	 * @param pwrtok
	 * @return
	 */
	@GetMapping("/reset-new-password")
	public ModelAndView resetNewPassword(@RequestParam(required = true) String pwrtok) {
		Users user = null;
		mv = new ModelAndView();
		mv.setViewName("auth/reset-new-password");		
		user = usersRepo.findByResetToken(pwrtok).orElse(null);
		if(user == null) {
			mv.addObject("error","Invalid reset link");
			return mv;
		}
		// Check if token has expired
        if (user.getResetTokenExpiry().isBefore(LocalDateTime.now())) {
        	mv.addObject("error","Reset link Expired");
        	return mv;
        }
		mv.addObject("name", user.getName());
		return mv;
	}
}
