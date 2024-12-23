package in.forFresher.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import in.forFresher.entity.Users;
import in.forFresher.repository.UsersRepository;


@Configuration
public class UserInfoDetailsService implements UserDetailsService{

	@Autowired
	private UsersRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users user = userRepo.findByUsername(usernameOrEmail);
		
		if( user == null ) {
			user = userRepo.findByEmail(usernameOrEmail);
		}
		
		if(user == null) {
		    throw new UsernameNotFoundException("user is not found");
		}
		return new UserInfoDetails(user);
	} 

}	
