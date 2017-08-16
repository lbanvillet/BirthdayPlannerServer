package com.tbd.birthdayplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring boot application and configuration.
 *
 * @author lb185112
 */
@SpringBootApplication
public class BirthdayPlannerApplication {

    /**
     * Main method. Supports Spring boot command line arguments that can override application properties (yml).
     *
     * @param args arguments for the application
     */
	public static void main(String[] args) {
		SpringApplication.run(BirthdayPlannerApplication.class, args);
	}
}
