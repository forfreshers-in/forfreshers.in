package in.forFresher.entity;

import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String about; 

	@Column(nullable = false, length = 600)
	private String companyLogoLink;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private Set<Jobs> jobs;

	@Column(nullable = false)
	private Timestamp created_at;

	@Column(nullable = false)
	private Timestamp updated_at;
}
