package in.forFresher.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
                // Secure the /admin/** paths
                .requestMatchers("/admin/**").hasRole("ADMIN")
                // Permit access to static resources
                .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                // Permit all other requests
                .anyRequest().permitAll())
            .httpBasic(Customizer.withDefaults())
            .formLogin(form -> form
                .loginPage("/login").permitAll().defaultSuccessUrl("/admin/dashboard"))
            .csrf(AbstractHttpConfigurer::disable);
        return http.build();
	}
	

	@Bean
	public UserDetailsService UserDetailsService() {
		return new UserInfoDetailsService();
	}

	@Bean
	public AuthenticationProvider authendicationProvider() {
		DaoAuthenticationProvider daoAuthPro = new DaoAuthenticationProvider();
		daoAuthPro.setUserDetailsService(UserDetailsService());
		daoAuthPro.setPasswordEncoder(passwordEncoder());
		return daoAuthPro;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// debugging for serialization error
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		return mapper;
	}

}
