package pl.sd.repos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the Repos application.
 * This class initializes and starts the Spring Boot application.
 */
@SpringBootApplication
public class ReposApplication {
	/**
	 * The main method to start the Repos Spring Boot application.
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ReposApplication.class, args);
	}

}
