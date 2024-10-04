package in.forFresher.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Jobs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String title;
	
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "company")
	private Company company;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "job_location", joinColumns = @JoinColumn(name = "job_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "location_id", referencedColumnName = "id"))
	private Set<Location> locations;

	//category - IT/software / finance
	@ManyToOne
	@JoinColumn(name = "category")
	private Category category;
	
	// If fill this column for walk-in-drive
	@Column(name = "date_and_time")
	private Timestamp dateAndTime;
	
	// If fill this column for walk-in-drive
	@Column(name = "last_date")
	private Timestamp lastDate;
	
	//this was optional if link is exist
	@Column(length = 1000, name = "apply_link")
	private String applyLink;

	//this was optional if mail id is exist
	@Column( name = "hr_mail")
	private String hrMail;
	
	//must be not null
	private Integer fromBatch;
	
	private Integer toBatch;
	
	@ManyToMany(cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
	@JoinTable(name="job_position", joinColumns = @JoinColumn( name = "job_id", referencedColumnName = "id" ), inverseJoinColumns = @JoinColumn(name ="position_id", referencedColumnName = "id"))
	private Set<Position> positions;

	//write down if any role responsibility, and other vacancy informations
	@Column(columnDefinition = "TEXT")
	private String description;
	

	@ManyToMany(cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
	@JoinTable(name="job_qualification", joinColumns = @JoinColumn( name = "job_id", referencedColumnName = "id" ), inverseJoinColumns = @JoinColumn(name ="qualification_id", referencedColumnName = "id"))
	private Set<Qualification> qualification;
	
	@Column(name = "min_salary")
	private Float  minSalary;

	@Column(name = "max_salary")
	private Float maxSalary;

	@Column(name = "experience_from")
	private Float experienceFrom;
	
	@Column(name = "experience_to")
	private Float experienceTo;

	@Column(name = "expired_at")
	private Timestamp expiredAt;
	
	@CreationTimestamp
	@Column(name = "created_at")
	private Timestamp createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	
	@ManyToMany(cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
	@JoinTable(name="job_type", joinColumns = @JoinColumn( name = "job_id", referencedColumnName = "id" ), inverseJoinColumns = @JoinColumn(name ="type_id", referencedColumnName = "id"))
	private Set<Type> types;
	
	@ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
	private Users author;
	
	//published status - Draft(-1), Unpublished(0), Published(1)
	@Column(nullable = false)
	private Byte published;

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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Set<Location> getLocations() {
		return locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
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

	public Set<Position> getPositions() {
		return positions;
	}

	public void setPositions(Set<Position> positions) {
		this.positions = positions;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Qualification> getQualification() {
		return qualification;
	}

	public void setQualification(Set<Qualification> qualification) {
		this.qualification = qualification;
	}

	public Float getMinSalary() {
		return minSalary;
	}

	public Float getMaxSalary() {
		return maxSalary;
	}
	
	public Float getExperience_from() {
		return experienceFrom;
	}

	public void setExperience_from(Float experienceFrom) {
		this.experienceFrom = experienceFrom;
	}

	public Float getExperience_to() {
		return experienceTo;
	}

	public void setExperience_to(Float experienceTo) {
		this.experienceTo = experienceTo;
	}

	public void setMinSalary(Float minSalary) {
		this.minSalary = minSalary;
	}

	public void setMaxSalary(Float maxSalary) {
		this.maxSalary = maxSalary;
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

	public Set<Type> getTypes() {
		return types;
	}

	public void setTypes(Set<Type> types) {
		this.types = types;
	}

	public Users getAuthor() {
		return author;
	}

	public void setAuthor(Users author) {
		this.author = author;
	}

	public Byte getPublished() {
		return published;
	}

	public void setPublished(Byte published) {
		this.published = published;
	}

	@Override
	public String toString() {
		return "Jobs [id=" + id + ", title=" + title + ", company=" + company + ", locations=" + locations
				+ ", category=" + category + ", dateAndTime=" + dateAndTime + ", lastDate=" + lastDate + ", applyLink="
				+ applyLink + ", hrMail=" + hrMail + ", fromBatch=" + fromBatch + ", toBatch=" + toBatch
				+ ", positions=" + positions + ", description=" + description + ", qualification=" + qualification
				+ ", minSalary=" + minSalary + ", maxSalary=" + maxSalary + ", experienceFrom=" + experienceFrom
				+ ", experienceTo=" + experienceTo + ", expiredAt=" + expiredAt + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", types=" + types + ", author=" + author + ", published=" + published
				+ "]";
	}
}
