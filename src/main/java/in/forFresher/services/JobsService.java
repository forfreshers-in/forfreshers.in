
package in.forFresher.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.forFresher.component.CurrentUserProvider;
import in.forFresher.dto.JobsDtoMyJobsList;
import in.forFresher.entity.Company;
import in.forFresher.entity.Jobs;
import in.forFresher.exception.IncompleteDataException;
import in.forFresher.exception.InvalidDataException;
import in.forFresher.repository.CategoryRepository;
import in.forFresher.repository.CompanyRepository;
import in.forFresher.repository.JobsRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class JobsService {
	@Autowired
	private JobsRepository jobsRepository;
	
	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private PositionService positionService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private TypeService typeService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private final CurrentUserProvider currentUserProvider;

	public static final Pattern VALID_ORG_EMAIL_ADDRESS_REGEX = Pattern.compile(
			"^(?!.*@gmail\\.com$)([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$", Pattern.CASE_INSENSITIVE);

	public static final Pattern VALID_URL_REGEX = Pattern.compile("((((https?)://))([-%()_.!~*';/?:@&=+$,A-Za-z0-9])+)",
			Pattern.CASE_INSENSITIVE);

	@Autowired
	public JobsService(JobsRepository jobsRepository, CurrentUserProvider currentUserProvider) {
		this.jobsRepository = jobsRepository;
		this.currentUserProvider = currentUserProvider;
	}

	public Jobs saveDraftByMap(Map<String, Object> jobData) {
		return null;
	}

	public Jobs saveAndPublish(Map<String, Object> jobData) throws Exception {

		validateTitle(jobData);
		validateCompany(jobData);
		validatePositions(jobData);
		validateLocations(jobData);
		validateTypes(jobData);
		validateQualifications(jobData);
		validateCategory(jobData);
		validateDescription(jobData);
		validateExperience(jobData);
		validateBatch(jobData);
		validateSalary(jobData);
		validateDates(jobData);
		validateLink(jobData);

		Jobs newJob = collectData(jobData);
		newJob.setPublished((byte) 1);

		return jobsRepository.save(newJob);
	}

	private void validateTitle(Map<String, Object> jobData) throws InvalidDataException {
		String title = (String) jobData.get("title");
		if (title == null || title.trim().isEmpty()) {
			throw new InvalidDataException("Title is required");
		}
		if (title.length() < 10 || title.length() > 100) {
			throw new InvalidDataException("Title length must be between 10 and 100 characters");
		}
		if (jobsRepository.existsByTitle(title)) {
			throw new InvalidDataException("Title is already exist please change unique");
		}

	}

	private void validateCompany(Map<String, Object> jobData) throws IncompleteDataException {
		String company = (String) jobData.get("company");
		if (company == null || company.trim().isEmpty()) {
			throw new IncompleteDataException("Company name is required");
		}
	}

	private void validatePositions(Map<String, Object> jobData) throws IncompleteDataException {
		List<String> positions = (List<String>) jobData.get("positions");
		if (positions == null || positions.isEmpty()) {
			throw new IncompleteDataException("At least one position is required");
		}
	}

	private void validateLocations(Map<String, Object> jobData) throws IncompleteDataException {
		List<String> locations = (List<String>) jobData.get("locations");
		if (locations == null || locations.isEmpty()) {
			throw new IncompleteDataException("At least one location is required");
		}
	}

	private void validateTypes(Map<String, Object> jobData) throws IncompleteDataException {
		List<String> types = (List<String>) jobData.get("types");
		if (types == null || types.isEmpty()) {
			throw new IncompleteDataException("At least one type is required");
		}
	}

	private void validateQualifications(Map<String, Object> jobData) throws IncompleteDataException {
		List<String> qualifications = (List<String>) jobData.get("qualifications");
		if (qualifications == null || qualifications.isEmpty()) {
			throw new IncompleteDataException("At least one qualification is required");
		}
	}

	private void validateCategory(Map<String, Object> jobData) throws IncompleteDataException {
		String category = (String) jobData.get("category");
		if (category == null || category.trim().isEmpty()) {
			throw new IncompleteDataException("Job category is required");
		}
	}

	private void validateDescription(Map<String, Object> jobData) throws IncompleteDataException {
		String description = (String) jobData.get("description");
		return;
//		if (description == null || description.trim().isEmpty()) {
//			throw new IncompleteDataException("Job description is required");
//		}
	}

	private void validateExperience(Map<String, Object> jobData) {
		String experienceFromStr = (String) jobData.get("experienceFrom");
		String experienceToStr = (String) jobData.get("experienceTo");

	}

	private void validateBatch(Map<String, Object> jobData) {
		String batchFromStr = (String) jobData.get("batchFrom");
		String batchToStr = (String) jobData.get("batchTo");
		// Validate batch values...
	}

	private void validateSalary(Map<String, Object> jobData) throws InvalidDataException {
		String salaryFromStr = (String) jobData.get("salaryFrom");
		String salaryToStr = (String) jobData.get("salaryTo");
		// Validate salary values
		Float a = 0F;
		if (!salaryFromStr.isEmpty()) {
			a = Float.valueOf(salaryFromStr);
			if (a <= 0.4) {
				throw new InvalidDataException("salary From must be equal or above 0.5 LPA");
			}
		}
		if (!salaryToStr.isEmpty()) {
			Float b = Float.valueOf(salaryToStr);
			if (a > b) {
				throw new InvalidDataException("salary To is must be above " + a + "(salary From value)");
			}
		}
	}

	/*
	 * this may be null this values filled only if that vacancies conducting in any
	 * specific dates
	 */
	private void validateDates(Map<String, Object> jobData) throws InvalidDataException {
		String startDateStr = (String) jobData.get("startDate");
		String endDateStr = (String) jobData.get("endDate");
		String expiryStr = (String) jobData.get("expiry");
		// Validate date values...
		if (expiryStr.isBlank()) {
			throw new InvalidDataException("expiry date is not valid");
		}
	}

	private void validateLink(Map<String, Object> jobData) throws InvalidDataException {

		String applyLink = jobData.get("applyLink").toString().strip();
		String hrMail = jobData.get("hrMail").toString().strip();

		Matcher emailRegex = VALID_ORG_EMAIL_ADDRESS_REGEX.matcher(hrMail);
		Matcher urlRegex = VALID_URL_REGEX.matcher(applyLink);

		// Check if the email matches the regex pattern
		if (!applyLink.isEmpty()) {
			if (!urlRegex.matches()) {
				throw new InvalidDataException("invalid apply link");
			}
		}
		if (!hrMail.isEmpty()) {
			if (!emailRegex.matches()) {
				throw new InvalidDataException("Email is not looks like organization mail");
			}
		}

	}

	/*
	 * collect Data
	 * 
	 * @return jobs
	 * 
	 */
	private Jobs collectData(Map<String, Object> jobData) throws InvalidDataException {
		Jobs newJob = new Jobs();

		newJob.setTitle((String) jobData.get("title"));

		String companyName = (String) jobData.get("company");
		// create company if not exist
		Company company = companyRepository.findByName(companyName);
		if (company == null) {
			company = companyRepository.save(new Company(companyName));
		}
//		company.setJobs(null);
		newJob.setCompany(company);

		newJob.setPositions(positionService.getOrCreatePositions((List<String>) jobData.get("positions")));

		newJob.setLocations(locationService.getOrCreateLocations((List<String>) jobData.get("locations")));

		newJob.setTypes(typeService.getTypes((List<String>) jobData.get("types")));

		newJob.setQualification(
				((List<String>) jobData.get("qualifications")).toString().replace("[", "").replace("]", ""));

		newJob.setCategory(categoryService.getCategory((String) jobData.get("category")));

		// Here need verification for script input restrictions
		newJob.setDescription((String) jobData.get("description"));
		// setting From experiance
		String tempInputStr = (String) jobData.get("experienceFrom");
		if (tempInputStr.isEmpty()) {
			newJob.setExperience_from(null);
		} else {
			try {
				newJob.setExperience_from(Float.valueOf(tempInputStr));
			} catch (Exception e) {
				newJob.setExperience_from(null);

				throw new InvalidDataException("experiace from is getting error");
			}
		}

		// setting To experiance
		tempInputStr = (String) jobData.get("experienceTo");
		if (tempInputStr.isEmpty()) {
			newJob.setExperience_to(null);
		} else {
			try {
				newJob.setExperience_to(Float.valueOf(tempInputStr));
			} catch (Exception e) {
				newJob.setExperience_to(null);

				throw new InvalidDataException("experiace To is  getting error");
			}
		}

		// setting from batch
		tempInputStr = (String) jobData.get("batchFrom");
		if (tempInputStr.isEmpty()) {
			newJob.setFromBatch(null);
		} else {
			try {
				newJob.setFromBatch(Integer.valueOf(tempInputStr));
			} catch (Exception e) {
				newJob.setFromBatch(null);
				throw new InvalidDataException("from batch is getting error");
			}
		}

		// setting To batch
		tempInputStr = (String) jobData.get("batchTo");
		if (tempInputStr.isEmpty()) {
			newJob.setToBatch(null);
		} else {
			try {
				newJob.setToBatch(Integer.valueOf(tempInputStr));
			} catch (Exception e) {
				newJob.setToBatch(null);
				throw new InvalidDataException("To batch is getting error");
			}
		}

		// setting Min Salary
		tempInputStr = (String) jobData.get("salaryFrom");
		if (tempInputStr.isEmpty()) {
			newJob.setMinSalary(null);
		} else {
			try {
				newJob.setMinSalary(Float.valueOf(tempInputStr));
			} catch (Exception e) {
				newJob.setMinSalary(null);
				throw new InvalidDataException("min salary is getting error");
			}
		}

		// setting max salary
		tempInputStr = (String) jobData.get("salaryTo");
		if (tempInputStr.isEmpty()) {
			newJob.setMaxSalary(null);
		} else {
			try {
				newJob.setMaxSalary(Float.valueOf(tempInputStr));
			} catch (Exception e) {
				newJob.setMaxSalary(null);
				throw new InvalidDataException("Max salary is getting error");
			}
		}

		// getting start date
		tempInputStr = (String) jobData.get("startDate");
		if (tempInputStr.isEmpty()) {
			newJob.setDateAndTime(null);
		} else {
			try {
				Timestamp dateAndTime = Timestamp.valueOf(LocalDateTime.parse(tempInputStr));
				newJob.setDateAndTime(dateAndTime);

			} catch (Exception e) {
				newJob.setDateAndTime(null);
				throw new InvalidDataException("from date is getting error");
			}
		}

		// getting end date
		tempInputStr = (String) jobData.get("endDate");
		if (tempInputStr.isEmpty()) {
			newJob.setLastDate(null);
		} else {
			try {
				Timestamp dateAndTime = Timestamp.valueOf(LocalDateTime.parse(tempInputStr));
				newJob.setLastDate(dateAndTime);

			} catch (Exception e) {
				newJob.setLastDate(null);
				throw new InvalidDataException("to date is getting error");
			}
		}

		// getting exp date
		tempInputStr = (String) jobData.get("expiry");
		if (tempInputStr.isEmpty()) {
			newJob.setExpiredAt(null);
		} else {
			try {
				Timestamp expiry = Timestamp.valueOf(LocalDateTime.parse((tempInputStr).concat("T00:00")));
				newJob.setExpiredAt(expiry);

			} catch (Exception e) {
				newJob.setExpiredAt(null);
				throw new InvalidDataException("to date is getting error");
			}
		}

		newJob.setApplyLink((String) jobData.get("applyLink"));
		newJob.setHrMail((String) jobData.get("hrMail"));
		newJob.setAuthor(currentUserProvider.getCurrentUser());
		return newJob;
	}

	/*
	 * get jobs list by author id using when myjobs list Access required: author
	 */
	public List<JobsDtoMyJobsList> findAllJobsByCurrentUser() {
		List<Jobs> jobs = jobsRepository.findAllJobsByAuthor((currentUserProvider.getCurrentUser().getId()));
		return jobs.stream().map(job -> {
			JobsDtoMyJobsList jobsDto = new JobsDtoMyJobsList();
			jobsDto.setId(job.getId());
			jobsDto.setTitle(job.getTitle());
			Company company = job.getCompany();
			jobsDto.setCompany(company.getName());
			jobsDto.setCreatedAt(job.getCreatedAt().toGMTString());
			jobsDto.setUpdatedAt(job.getUpdatedAt().toGMTString());
			jobsDto.setExpiredAt(job.getExpiredAt().toGMTString());
			jobsDto.setPublished(job.getPublished());
			return jobsDto;
		}).collect(Collectors.toList());
	}

	/*
	 * Method for jobs description page to anonymous user
	 */
	public Jobs getJobByTitleAndId(String jobTitle, String jobId) {
		Long jobIdLong = Long.valueOf(jobId);
		Jobs job = jobsRepository.findByTitleAndId(jobTitle, jobIdLong);

		return job;

	}

	/*
	 * load more jobs for load more button
	 */

	public List<Jobs> findJobs(LocalDateTime initialLoadTime, int offset, int limit, String location, String company, String type) {
        Pageable pageable = PageRequest.of(offset / limit, limit);
        Page<Jobs> jobPage = jobsRepository.findJobs(initialLoadTime, location, company, type, pageable);
        return jobPage.getContent();
    }
}
