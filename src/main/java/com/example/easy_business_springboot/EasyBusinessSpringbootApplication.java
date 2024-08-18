package com.example.easy_business_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EasyBusinessSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyBusinessSpringbootApplication.class, args);
	}


	//For Password Encoder
	@Configuration
	public class SecurityConfig {

		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
	}
}
