package io.nuevedejun.htmxtest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
						.requestMatchers("/admin/**")
						.hasRole("ADMIN")
						.requestMatchers("/anonymous*")
						.anonymous()
						.requestMatchers("/login*", "/")
						.permitAll()
						.anyRequest()
						.authenticated())

				.formLogin(Customizer.withDefaults())

				.logout(logout -> logout
						.logoutUrl("/perform_logout")
						.deleteCookies("JSESSIONID"));
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static final String TEST_USERNAME = "username";

	// TODO real implementation should query db (create dedicated schema, eventually use dedicated service)
	@Bean
	public UserDetailsService userDetailsService() {
		final UserDetails user = User.withUsername(TEST_USERNAME)
				.password(passwordEncoder().encode("password"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
}
