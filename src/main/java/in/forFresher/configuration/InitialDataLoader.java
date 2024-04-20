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
		  // Create locations
        Location location1 = new Location();
        location1.setCity("City1");
        Location location2 = new Location();
        location2.setCity("City2");

        // Save locations
        locationRepo.save(location1);
        locationRepo.save(location2);

        // Create jobs
        Jobs job1 = new Jobs();
        job1.setTitle("Job1");
        Jobs job2 = new Jobs();
        job2.setTitle("Job2");

        // Associate locations with jobs
        Set<Location> locationsForJob1 = new HashSet<>();
        locationsForJob1.add(location1);
        job1.setLocations(locationsForJob1);

        Set<Location> locationsForJob2 = new HashSet<>();
        locationsForJob2.add(location2);
        job2.setLocations(locationsForJob2);

        // Save jobs
        jobsRepo.save(job1);
        jobsRepo.save(job2);
	}
	
}
