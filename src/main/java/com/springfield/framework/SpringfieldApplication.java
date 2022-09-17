package com.springfield.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringfieldApplication {

	@GetMapping("/welcome")
	public String welcome(){
		return "Welcome to Project Springfield";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringfieldApplication.class, args);
	}

}
