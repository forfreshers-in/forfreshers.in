package in.forFresher.apiController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.forFresher.services.CompanyService;

@RestController
@RequestMapping("/api")
public class CompanyApiController {
	
	private CompanyService companyService;
	
	//Constructor
	public CompanyApiController(CompanyService companyService) {
		this.companyService = companyService;
	}

	/*
	 * Authorize with Admin
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/companies", name = "getAllCompanies")
	public ResponseEntity<Object> getAllCompanies() {
		try {
			
			return ResponseEntity.ok(companyService.getAllName());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
