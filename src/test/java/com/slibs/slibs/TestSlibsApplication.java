package com.slibs.slibs;

import org.springframework.boot.SpringApplication;

import com.slibs.slibs.services.SearchServiceTests;

public class TestSlibsApplication {

	public static void main(String[] args) {
		SpringApplication.from(SlibsApplication::main).with(SearchServiceTests.class).run(args);
	}

}
