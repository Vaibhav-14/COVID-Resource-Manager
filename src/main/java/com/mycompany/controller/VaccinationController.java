package com.mycompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class VaccinationController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/vaccination")
	public ResponseEntity<Object> getDetailsByPin(@RequestParam String pincode, @RequestParam String date) {
		String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode="+ pincode + "&date=" + date;
		ResponseEntity<Object> vaccines = restTemplate.getForEntity(url, Object.class);
		return vaccines;
	}
	
}
