package in.forFresher.apiController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.forFresher.entity.Location;
import in.forFresher.repository.LocationRepository;
import in.forFresher.services.LocationService;

@RestController
@RequestMapping("/api")
public class LocationApiController {
	
	@Autowired
	private LocationRepository locationRespository;
	
	@Autowired
	private LocationService locationService;

	@GetMapping(value = "/locations", name = "GetAllLocations")
	public ResponseEntity<Object> getAllPositions() {
		try {
			List<Location> locations = locationRespository.findAll();
			if (locations == null || locations.size() == 0) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Locations are not exist in database");
			}
			return ResponseEntity.ok(locations);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/locationCities", name = "getAllCitiesFromLocation")
	public ResponseEntity<Object> getAllCitiesFromLocation(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(locationService.getAllCities());			
		} 
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
		}
	}

}
