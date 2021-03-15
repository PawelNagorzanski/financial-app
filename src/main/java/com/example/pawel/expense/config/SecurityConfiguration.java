package com.example.pawel.expense.config;

import com.example.pawel.expense.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.test.OAuth2ContextConfiguration.Password;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails userDetails = User.withDefaultPasswordEncoder()
				.username("user")
				.password("user1")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(userDetails);
	}
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user")
			.password("pass")
			.roles("USER");
	}

	public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().disable().authorizeRequests()
				.antMatchers("/").permitAll()
			.antMatchers("/login").permitAll()
				.antMatchers("/registration").permitAll()
        .anyRequest().authenticated()
        .and().csrf().disable().formLogin()
        //.oauth2Login()
        .loginPage("/login").failureUrl("/login?error=true")
		.usernameParameter("user_name")
		.passwordParameter("password")
        .and()
        .logout().logoutSuccessUrl("/");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
				.ignoring()
				.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
