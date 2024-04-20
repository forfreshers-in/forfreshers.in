package in.forFresher.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
	public SecurityFilterChain fiterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/**").permitAll().requestMatchers("/admin")
				.hasRole("ADMIN").anyRequest().authenticated()).httpBasic(Customizer.withDefaults())
				.formLogin(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable);
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

}
