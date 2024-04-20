package in.forFresher.entity;

import java.sql.Timestamp;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Jobs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	
	@ManyToOne(targetEntity = Company.class)
	@JoinColumn(name = "company")
	private Company company;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "job_location", joinColumns = @JoinColumn(name = "job_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "location_id", referencedColumnName = "id"))
	private Set<Location> locations;

	//category - IT/software / finance
	@ManyToOne
	private Category category;
	
	// If fill this column for walk-in-drive
	@Column(name = "date_and_time")
	private Timestamp dateAndTime;
	
	// If fill this column for walk-in-drive
	@Column(name = "last_date")
	private Timestamp lastDate;
	
	//this was optional if link is exist
	@Column(length = 600, name = "apply_link")
	private String applyLink;

	//this was optional if mail id is exist
	@Column( name = "hr_mail")
	private String hrMail;
	
	//must be not null
	private int fromBatch;
	
	private int toBatch;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="job_position", joinColumns = @JoinColumn( name = "job_id", referencedColumnName = "id" ), inverseJoinColumns = @JoinColumn(name ="position_id", referencedColumnName = "id"))
	private Set<Position> positions;

	//write down if any role responsibility, and other vacancy informations
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Column(name = "qualification")
	private String qualification;
	
	@Column(nullable = false, name = "min_salary")
	private int minSalary;

	@Column(name = "max_salary")
	private int maxSalary;

	@Column(name = "expired_at")
	private int expiredAt;
	
	@CreationTimestamp
	@Column(name = "created_at")
	private Timestamp createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	
	@ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
	private Users author;
	
	//published status - Draft(-1), Unpublished(0), published(1)
	@Column(nullable = false)
	private byte published;
}
