package it.uniroma3.siw.taskmanager.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

import static it.uniroma3.siw.taskmanager.model.Credentials.ADMIN_ROLE;

/**
 * The AuthConfiguration is a Spring Security Configuration. It extends
 * WebSecurityConfigurerAdapter, meaning that it provides the settings for Web
 * security.
 */
@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

	/**
	 * The datasource is automatically injected into the AuthConfiguration (using
	 * its getters and setters) and it is used to access the DB to get the
	 * Credentials to perform authentication and authorization
	 */
	@Autowired
	DataSource datasource;

	/**
	 * This method provides the whole authentication and authorization configuration
	 * to use.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		.authorizeRequests().antMatchers(HttpMethod.GET, "/", "/index", "/login", "/users/register").permitAll()
				.antMatchers(HttpMethod.POST, "/login", "/users/register").permitAll()
				.antMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority(ADMIN_ROLE)
				.antMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority(ADMIN_ROLE).anyRequest().authenticated()
				.and().formLogin().loginPage("/login").defaultSuccessUrl("/home").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
				.invalidateHttpSession(true).clearAuthentication(true).permitAll();

	}

	/**
	 * This method provides the SQL queries to get username and password. NOTE:
	 * field denoted in Java by camelCase convention are denoted in Postgres by
	 * snake_case convention by default (e.g. "userName" field in the Java class
	 * results in "user_name" DB column)
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(this.datasource)
				// retrieve username and role
				.authoritiesByUsernameQuery("SELECT user_name, role FROM credentials WHERE user_name=?")
				// retrieve username, password and a boolean flag specifying whether the user is
				// enabled or not (always enabled in our case)
				.usersByUsernameQuery("SELECT user_name, password, 1 as enabled FROM credentials WHERE user_name=?");
	}

	/**
	 * This method defines a "passwordEncoder" Bean. The passwordEncoder Bean is
	 * used to encrypt and decrput the Credentials passwords.
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}