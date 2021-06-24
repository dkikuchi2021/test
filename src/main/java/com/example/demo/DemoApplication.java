package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.commons.Config;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		Config.getInstance().Init();
		SpringApplication.run(DemoApplication.class, args);
	}

}
