package in.forFresher.apiController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.forFresher.entity.Position;
import in.forFresher.repository.PositionRepository;
import in.forFresher.services.PositionService;

@RestController
@RequestMapping("/api")
public class PositionApiController {
	
	@Autowired
	private PositionRepository positionRepository;

	@Autowired
	private PositionService positionService;
	
	@GetMapping(value="/positions", name="GetAllPositions")
	public ResponseEntity<Object> getAllPositions(){
		try {
			List<String> positions = positionService.getAllPositionNames();
			if(positions==null || positions.size() == 0) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("positions are not exist in database");
			}
			return ResponseEntity.ok(positions);			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
}
