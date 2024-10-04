package in.forFresher.apiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.forFresher.services.QualificationService;

@RestController
@RequestMapping("/api")
public class QualificationApiController {
	
	private final QualificationService qualificationService;
	
	 @Autowired
	public QualificationApiController(QualificationService qualificationService) {
		this.qualificationService = qualificationService;
	}
	
	@GetMapping(value = "/qualifications", name = "getAllQualifications")
	public ResponseEntity<Object> getAllQualifications(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(qualificationService.getAllQualifications());			
		} 
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
		}
	}
}
