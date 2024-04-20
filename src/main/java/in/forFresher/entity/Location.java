package in.forFresher.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

	@Entity
	public class Location {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column(nullable = false)
		private String city;
		
		@ManyToMany(mappedBy = "locations")
		private Set<Jobs> jobs;
		
		

	public Location() {
		super();
	}

	public Location(String city) {
		super();
		this.city = city;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", city=" + city + "]";
	}

	
	
	
}
