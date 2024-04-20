package in.forFresher.configuration;

import java.util.HashSet;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.forFresher.entity.Jobs;
import in.forFresher.entity.Location;
import in.forFresher.repository.JobsRepository;
import in.forFresher.repository.LocationRepository;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class InitialDataLoader implements CommandLineRunner{
	
	@Autowired
	private LocationRepository locationRepo;
	@Autowired
	private JobsRepository jobsRepo;

	@Override
	public void run(String... args) throws Exception {
		
	}
	
}
