package in.forFresher.dto;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import in.forFresher.entity.Jobs;
import in.forFresher.entity.Location;
import in.forFresher.entity.Position;
import in.forFresher.entity.Qualification;
import in.forFresher.entity.Type;

public class JobDto {

	private Long id;
	private String title;
	private String company; // Use the company name or ID as needed
	private Set<String> locations; // You might want to expose location names instead of Location entities
	private String category; // Use category name or ID as needed
	private Timestamp dateAndTime;
	private Timestamp lastDate;
	private String applyLink;
	private String hrMail;
	private Integer fromBatch;
	private Integer toBatch;
	private Set<String> positions; // You might want to expose position names instead of Position entities
	private String description;
	private Set<String> qualifications;
	private Float minSalary;
	private Float maxSalary;
	private Float experienceFrom;
	private Float experienceTo;
	private Timestamp expiredAt;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Set<String> types; // Expose job types by name or ID
	private String author; // You might expose author name
	private Long author_id; // You might expose author  ID
	private Byte published;

	// Getters and Setters

	public JobDto(Long id, String title, String company, String category, Float minSalary, Float maxSalary,
			Timestamp createdAt,       Timestamp expiredAt, String author,
			Byte published) {
		this.id = id;
		this.title = title;
		this.company = company;
		this.category = category;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
		this.createdAt = createdAt;

		// Converting comma-separated location names to a Set<String>
//		this.locations = new HashSet<>(Arrays.asList(locationsString.split(",")));
//
//		// Converting comma-separated type names to a Set<String>
//		this.types = new HashSet<>(Arrays.asList(typesString.split(",")));

		this.expiredAt = expiredAt;
		this.author = author;
		this.published = published;
	}

	// Constructor for converting Jobs to JobDto
    public JobDto(Jobs job) {
        this.id = job.getId();
        this.title = job.getTitle();
        this.company = job.getCompany() != null ? job.getCompany().getName() : null; // Assuming Company has getName()
        this.locations = job.getLocations() != null ? job.getLocations().stream()
                .map(Location::getCity) // Assuming Location has getName()
                .collect(Collectors.toSet()) : null;
        this.category = job.getCategory() != null ? job.getCategory().getName() : null; // Assuming Category has getName()
        this.dateAndTime = job.getDateAndTime();
        this.lastDate = job.getLastDate();
        this.applyLink = job.getApplyLink();
        this.hrMail = job.getHrMail();
        this.fromBatch = job.getFromBatch();
        this.toBatch = job.getToBatch();
        this.positions = job.getPositions() != null ? job.getPositions().stream()
                .map(Position::getName) // Assuming Position has getName()
                .collect(Collectors.toSet()) : null;
        this.description = job.getDescription();
        this.qualifications = job.getQualification() != null ? job.getQualification().stream()
                .map(Qualification::getName) // Assuming Qualification has getName()
                .collect(Collectors.toSet()) : null;
        this.minSalary = job.getMinSalary();
        this.maxSalary = job.getMaxSalary();
        this.experienceFrom = job.getExperience_from();
        this.experienceTo = job.getExperience_to();
        this.expiredAt = job.getExpiredAt();
        this.createdAt = job.getCreatedAt();
        this.updatedAt = job.getUpdatedAt();
        this.types = job.getTypes() != null ? job.getTypes().stream()
                .map(Type::getName) // Assuming Type has getName()
                .collect(Collectors.toSet()) : null;
        this.author = job.getAuthor() != null ? job.getAuthor().getName() : null; // Assuming Users has getName()
        this.author_id = job.getAuthor() != null? job.getAuthor().getId() : null;
        this.published = job.getPublished();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Set<String> getLocations() {
		return locations;
	}

	public void setLocations(String locations) {
		// Converting comma-separated location names to a Set<String>
		this.locations = new HashSet<>(Arrays.asList(locations.split(",")));
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Timestamp getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Timestamp dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public Timestamp getLastDate() {
		return lastDate;
	}

	public void setLastDate(Timestamp lastDate) {
		this.lastDate = lastDate;
	}

	public String getApplyLink() {
		return applyLink;
	}

	public void setApplyLink(String applyLink) {
		this.applyLink = applyLink;
	}

	public String getHrMail() {
		return hrMail;
	}

	public void setHrMail(String hrMail) {
		this.hrMail = hrMail;
	}

	public Integer getFromBatch() {
		return fromBatch;
	}

	public void setFromBatch(Integer fromBatch) {
		this.fromBatch = fromBatch;
	}

	public Integer getToBatch() {
		return toBatch;
	}

	public void setToBatch(Integer toBatch) {
		this.toBatch = toBatch;
	}

	public Set<String> getPositions() {
		return positions;
	}

	public void setPositions(Set<String> positions) {
		this.positions = positions;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<String> getQualification() {
		return qualifications;
	}

	public void setQualification(Set<String> qualification) {
		this.qualifications = qualification;
	}

	public Float getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(Float minSalary) {
		this.minSalary = minSalary;
	}

	public Float getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(Float maxSalary) {
		this.maxSalary = maxSalary;
	}

	public Float getExperienceFrom() {
		return experienceFrom;
	}

	public void setExperienceFrom(Float experienceFrom) {
		this.experienceFrom = experienceFrom;
	}

	public Float getExperienceTo() {
		return experienceTo;
	}

	public void setExperienceTo(Float experienceTo) {
		this.experienceTo = experienceTo;
	}

	public Timestamp getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(Timestamp expiredAt) {
		this.expiredAt = expiredAt;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Set<String> getTypes() {
		return types;
	}

	public void setTypes(String types) {
		// Converting comma-separated type names to a Set<String>
				this.types = new HashSet<>(Arrays.asList(types.split(",")));
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	

	public Set<String> getQualifications() {
		return qualifications;
	}

	public void setQualifications(Set<String> qualifications) {
		this.qualifications = qualifications;
	}

	public Long getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(Long author_id) {
		this.author_id = author_id;
	}

	public void setLocations(Set<String> locations) {
		this.locations = locations;
	}

	public void setTypes(Set<String> types) {
		this.types = types;
	}

	public Byte getPublished() {
		return published;
	}

	public void setPublished(Byte published) {
		this.published = published;
	}
}
