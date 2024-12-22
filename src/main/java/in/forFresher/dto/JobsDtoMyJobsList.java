package in.forFresher.dto;

import java.sql.Timestamp;

import in.forFresher.entity.Company;

public class JobsDtoMyJobsList {
	private Long id;
	
	private String title;
	
	private String company;
	
	private String expiredAt;
	
	private String createdAt;
	
	private String updatedAt;

	private byte published;

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

	public String getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(String expiredAt) {
		this.expiredAt = expiredAt;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public byte getPublished() {
		return published;
	}

	public void setPublished(byte published) {
		this.published = published;
	}

	
}
