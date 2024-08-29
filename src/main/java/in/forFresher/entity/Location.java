package in.forFresher.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String city;

	@ManyToMany(mappedBy = "locations", fetch = FetchType.LAZY)
	private Set<Jobs> jobs;

	public Location() {
		super();
	}

	public Location(String city) {
		super();
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	public Set<Jobs> getJobs() {
		return jobs;
	}
	
	public void setJobs(Set<Jobs> jobs) {
		this.jobs = jobs;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", city=" + city + "]";
	}

}
