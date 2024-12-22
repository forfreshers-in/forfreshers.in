package in.forFresher.configuration;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import in.forFresher.entity.Users;


public class UserInfoDetails implements UserDetails{

	private Long userId;
	
	private String username;

	private String password;
	
	private String firstname;
	
	private String email;

	private List<GrantedAuthority> roles;

	public UserInfoDetails(Users user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.firstname = user.getName();
		this.email = user.getEmail();
		this.roles = Arrays.stream(user.getRole().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		this.userId = user.getId();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		return this.password;
	}
	

	public Long getUserId() {
		return userId;
	}


	public List<GrantedAuthority> getRoles() {
		return roles;
	}

	@Override
	public String getUsername() {
		return this.username;
	}
	
	public String getFirstname() {
		return this.firstname;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
