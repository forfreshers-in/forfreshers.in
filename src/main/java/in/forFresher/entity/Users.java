package in.forFresher.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false, unique = true, length = 15)
	private String phone;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String role;

	@Column(length = 600)
	private String about;
	
	@ManyToOne
    @JoinColumn(name = "created_by_id")
	private Users addedBy;
	
	@Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;
	
	private Boolean is_activated ;
	
	private String activation_token ;
	
	private Timestamp token_expiration ;
	
	private Boolean active_status;
	
	private String resetToken;
	 
	private LocalDateTime resetTokenExpiry;
	
	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Users getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(Users addedBy) {
		this.addedBy = addedBy;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getIs_activated() {
		return is_activated;
	}

	public void setIs_activated(Boolean is_activated) {
		this.is_activated = is_activated;
	}

	public String getActivation_token() {
		return activation_token;
	}

	public void setActivation_token(String activation_token) {
		this.activation_token = activation_token;
	}

	public Timestamp getToken_expiration() {
		return token_expiration;
	}

	public void setToken_expiration(Timestamp token_expiration) {
		this.token_expiration = token_expiration;
	}
	
	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public LocalDateTime getResetTokenExpiry() {
		return resetTokenExpiry;
	}

	public void setResetTokenExpiry(LocalDateTime resetTokenExpiry) {
		this.resetTokenExpiry = resetTokenExpiry;
	}

	public Boolean getActive_status() {
		return active_status;
	}

	public void setActive_status(Boolean active_status) {
		this.active_status = active_status;
	}
	
	
	
}
 