package com.seema;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleResrController {
	
	@Value("${myname}")
//	@Value("myname")
	private String name;
	
	@GetMapping("/greeting")
	public String greeting() {
		return name+"We come to Microservices";
		
	}

}
