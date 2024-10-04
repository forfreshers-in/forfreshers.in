package in.forFresher.apiController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import in.forFresher.services.JobsService;
import in.forFresher.dto.JobDto;
import in.forFresher.dto.JobsDtoJobCard;
import in.forFresher.entity.Jobs;

@RestController
@RequestMapping("/api/jobs")
public class JobsApiController {

	private final JobsService jobsService;

	private PasswordEncoder password;

	public JobsApiController(@Lazy JobsService jobsSerivice) {
		this.jobsService = jobsSerivice;
	}

	/*
	 * @return if savedraft return job id/data
	 */
	@PostMapping(value = "/savedraft")
	public ResponseEntity<Object> savedraft(@RequestBody Map<String, Object> jsonData) {

		if (jsonData == null) {
			return ResponseEntity.badRequest().build();
		}

		try {
			jobsService.saveAndPublish(jsonData);
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}

		return null;

	}

	/*
	 * @return if successfully published return job id/data
	 */
	@PostMapping(value = "/publish")
	public ResponseEntity<Object> publishJob(@RequestBody Map<String, Object> jsonData) {
		if (jsonData == null) {
			return ResponseEntity.badRequest().build();
		}
		try {
			Jobs job = jobsService.saveAndPublish(jsonData);
			if (job instanceof Jobs) {
				return ResponseEntity.ok().build();
			} else {
				throw new Exception("Unexpected error");
			}
		} catch (Exception e) {
			// for debug
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	/*
	 * use when admin click MY JOBSfindAllJobsByCurrentUser page it will loaded
	 */
	@GetMapping(value = "/myjobs", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> getAllJobsByCurrentUser() {
		try {
			return ResponseEntity.ok(jobsService.findAllJobsByCurrentUser());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
	}

	public ResponseEntity<Jobs> saveDraft(@RequestBody Jobs job) {

		return null;
	}
	
	/*
	 * Accepts the initial load time, offset, limit, and optional filters like location, company, and type.
	 */
	@GetMapping(value = "/getjobs", produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<JobDto>> findJobs(
			 @RequestParam("initialLoadTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime initialLoadTime,
			 @RequestParam("offset") int offset,
			 @RequestParam("limit") int limit,
		        @RequestParam(value = "location", required = false) String location,
		        @RequestParam(value = "company", required = false) String company,
		        @RequestParam(value = "type", required = false) String type
			){
		
		List<JobDto> jobs = jobsService.findJobs(initialLoadTime,offset, limit,  location, company, type);
        return ResponseEntity.ok(jobs);
		
	}
	
}
