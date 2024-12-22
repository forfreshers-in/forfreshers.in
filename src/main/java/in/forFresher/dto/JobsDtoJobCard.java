package in.forFresher.dto;

import java.util.Set;

import in.forFresher.entity.Location;

public class JobsDtoJobCard {
	private Long id;
	private String title;
	private String company;
	private Set<Location> locations;
	
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
	public Set<Location> getLocations() {
		return locations;
	}
	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}
	
	
}
  