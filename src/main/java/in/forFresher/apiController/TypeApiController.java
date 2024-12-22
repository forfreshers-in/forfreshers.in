package in.forFresher.apiController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.forFresher.entity.Type;
import in.forFresher.repository.TypeRepository;
import in.forFresher.services.TypeService;

@RestController
@RequestMapping("/api")
public class TypeApiController {

	@Autowired
	private TypeRepository typeRepository;

	@Autowired
	private TypeService typeService;
	
	@GetMapping("/typeNames")
	public ResponseEntity<Object> getAllTypes(){
		try {
			List<String> types = typeService.getAllTypes();
			if(types==null || types.size() == 0) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("types are not exist in database");
			}
			return ResponseEntity.ok(types);			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
}
