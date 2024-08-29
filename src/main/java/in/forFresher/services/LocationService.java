package in.forFresher.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.forFresher.entity.Location;
import in.forFresher.entity.Position;
import in.forFresher.repository.LocationRepository;

@Service
public class LocationService {
	
	@Autowired
	private LocationRepository locationRepository;
	
	public List<String> getAllCities(){
		return locationRepository.findAll().stream().map(Location::getCity).toList();
	}
	
	public Set<Location> getOrCreateLocations(List<String> cityNames) {
        Set<Location> locations = new HashSet<>();
        for (String name : cityNames) {
            Location location = locationRepository.findByCity(name);
            if (location == null) {
            	location = new Location();
            	location.setCity(name);
            	location = locationRepository.save(location);
            }
//            location.setJobs(null);
            locations.add(location);
        }
        return locations;
	}
}
