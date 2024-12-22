package in.forFresher.configuration;

import java.util.HashSet;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import in.forFresher.entity.Jobs;
import in.forFresher.entity.Location;
import in.forFresher.entity.Users;
import in.forFresher.repository.JobsRepository;
import in.forFresher.repository.LocationRepository;
import in.forFresher.repository.UsersRepository;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class InitialDataLoader implements CommandLineRunner{
	
//	@Autowired
//	private LocationRepository locationRepo;
//	@Autowired
//	private JobsRepository jobsRepo;
//	@Autowired
//	private UsersRepository usersRepository;
//	@Autowired
//	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
//		 String email = "ssaranartist@gmail.com"; // Change this to the email you want to check
//	        Users existingUser = usersRepository.findByEmail(email);
//	        
//	        if (existingUser == null) {
//	            // User does not exist, create a new one
//	            Users user = new Users();
//	            user.setName("saravanan");
//	            user.setUsername("vklsaravanan");
//	            user.setRole("ROLE_ADMIN");
//	            user.setEmail("ssaranartist@gmail.com");
//	            user.setPhone("7373580007");
//	            user.setAbout("nothing is impossible, this is not a about the meaning of the man in the middle of the age in the age we know whate we are doing but we know what we are done in this case we are not same");
//	            user.setPassword(passwordEncoder.encode("Thisismypassword123"));
//	            usersRepository.save(user);
//	        } else {
//	            // User already exists, skip the process
//	            System.out.println("User with email " + email + " already exists. Skipping creation process.");
//	        }
	}
	
}
