package in.forFresher.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import in.forFresher.configuration.UserInfoDetails;
import in.forFresher.entity.Users;
import in.forFresher.repository.UsersRepository;

@Service
public class UserService {
	
	private UsersRepository userRepo;
	
	public UserService(UsersRepository userRepo) {
		this.userRepo = userRepo;
	}
	
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }
    
    public boolean authenticatedWithRole(String... roles) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        for (String role : roles) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (role.equals(authority.getAuthority())) {
                    return true;
                }
            }
        }

        return false;
    }

	 // Method to get the authenticated user's ID
    public Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // Ensure the authentication object is not null
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated");
        }

        UserInfoDetails userDetails = (UserInfoDetails) authentication.getPrincipal();
        return userDetails.getUserId();
    }

    // Method to check if the authenticated user matches a given ID
    public boolean isAuthenticatedUser(Long userId) {
        Long authenticatedUserId = getAuthenticatedUserId();
        return authenticatedUserId.equals(userId);
    }
    
    
}
