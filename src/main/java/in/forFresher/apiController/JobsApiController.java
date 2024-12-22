package in.forFresher.apiController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import in.forFresher.services.JobsService;
import in.forFresher.services.UserService;
import in.forFresher.dto.JobDto;
import in.forFresher.entity.Jobs;
import in.forFresher.exception.EntityNotFoundException;

@RestController
@RequestMapping("/api/jobs")
public class JobsApiController {

	private final JobsService jobsService;
	
	private final UserService userService;

	public JobsApiController(@Lazy JobsService jobsSerivice, UserService userService) {
		this.jobsService = jobsSerivice;
		this.userService = userService;
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
	
 	/**
	 * This method used to update the existing job
	 * @author Saravanan Raja
	 * @since 1.0
	 * @param jsonData
	 */
	@PutMapping(value = "/update")
	public ResponseEntity<Object> updateJob(@RequestBody Map<String, Object> jsonData) {
		if( !userService.authenticatedWithRole("ROLE_ADMIN") ) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Your not allowed to process");
		}
		if ((jsonData == null) || (jsonData.get("id") == null)) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("your request not fulfill our required data for process");
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
		ZonedDateTime initialLoadTimeUTC = initialLoadTime.atZone(ZoneId.of("UTC"));
		// converting to IST
		ZonedDateTime initialLoadDateAndTimeWithZone = initialLoadTimeUTC.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));
		
		List<JobDto> jobs = jobsService.findJobs(initialLoadDateAndTimeWithZone.toLocalDateTime() ,offset, limit,  location, company, type);
        return ResponseEntity.ok(jobs);
		
	}
	
	/**
	 * get job by using id
	 * @return <b>jobDto</b>
	 * @return Jobs belongs to user who is the author
	 * @author Saravanan_Raja
	 * @param Job id
	 * @since 1.0
	 */
	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/getjob", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> getMyJobById( @RequestParam("id") String id){
		
		try {
			JobDto jobDto = jobsService.getJobByid(Long.valueOf(id));
			if(userService.isAuthenticatedUser(jobDto.getAuthor_id())) {
				return ResponseEntity.ok(jobDto);				
			}
			else { 
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Job Not belongs to you.");
			}
		}catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found with id "+id);
		}catch(NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Id");
		}
		catch (Exception e) {
			return ResponseEntity.internalServerError().body("Server Error");
		}		
	}
	
}
