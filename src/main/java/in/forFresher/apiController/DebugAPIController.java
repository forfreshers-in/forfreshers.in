package in.forFresher.apiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.forFresher.entity.Jobs;
import in.forFresher.repository.JobsRepository;

@RestController
public class DebugAPIController {
	
	@Autowired
	private JobsRepository jobsRepository;
	
	@GetMapping("/alljobs")
	public ResponseEntity<Object> name() {
		return ResponseEntity.ok(jobsRepository.findById(1L));
	}
}
