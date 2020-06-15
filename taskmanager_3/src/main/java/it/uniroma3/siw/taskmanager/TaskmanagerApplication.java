package it.uniroma3.siw.taskmanager;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import it.uniroma3.siw.taskmanager.model.Credentials;
import it.uniroma3.siw.taskmanager.model.User;
import it.uniroma3.siw.taskmanager.service.CredentialsService;

@SpringBootApplication
public class TaskmanagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerApplication.class, args);
	}

	/** A reference to the environment of properties defined through the application.properties file.
	 * It is automatically wired before launch. */
	@Autowired
	private Environment environment;
	@Autowired
	CredentialsService cs;
	
	@Bean
	public InitializingBean populateDatabaseIfEmpty() {
	    return () -> {
	        // Users
	    	User user=new User("Eduard","Ursu");
	        Credentials cred = new Credentials();
	        cred.setUser(user);
	        cred.setUserName("admin");
	        cred.setPassword("admin1234");
	        cred.setRole("ADMIN");
	        cs.saveCredentials(cred);
	    };
	}
}
