package com.slibs.slibs;

import org.springframework.boot.SpringApplication;

public class TestSlibsApplication {

	public static void main(String[] args) {
		SpringApplication.from(SlibsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
