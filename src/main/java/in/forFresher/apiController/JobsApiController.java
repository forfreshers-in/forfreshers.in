package in.forFresher.apiController;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.forFresher.services.JobsService;
import in.forFresher.entity.Jobs;

@RestController
@RequestMapping("/api/jobs")
public class JobsApiController {
	
	private final JobsService jobsService;
	
	public JobsApiController(JobsService jobsSerivice) {
		this.jobsService = jobsSerivice;
	}
	
	/*
	 * @return  if savedraft return job id/data
	 */
	@PostMapping(value="/savedraft")
	public ResponseEntity<Jobs> savedraft( @RequestBody Map<String, Object> jsonData){
		if(jsonData == null) {
			return ResponseEntity.badRequest().build();
		}
		
		try {
			jobsService.saveAndPublish(jsonData); 
		}catch(Exception e) {
			
		}
		
		return null;
	}
	
	/*
	 * @return if successfully published return job id/data
	 */
	@PatchMapping(value = "/publish")
	public ResponseEntity<Jobs> upateJob(){
		return null;
	}
	
	public ResponseEntity<Jobs> saveDraft(@RequestBody Jobs job){
		System.out.println(job);
		
		return null;
	}

}
