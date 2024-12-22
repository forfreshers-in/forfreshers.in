package in.forFresher.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import in.forFresher.entity.Users;
import in.forFresher.repository.UsersRepository;

@Component
public class CurrentUserProvider {

	@Autowired
	private UsersRepository usersRepository;
	
    public Users getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(authentication+ " from user Login check line 14");
        if (authentication != null && authentication.isAuthenticated()) {
        	UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        	Users user = usersRepository.findByUsername(userDetails.getUsername());
        	if(user != null) {
        		return user;
        	}
        	return null;
        }
        return null;
    }
}
