package com.mycompany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.entity.CenterDetails;
import com.mycompany.entity.Session;

@RestController
public class VaccinationController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/vaccination")
	public ModelAndView getDetailsByPin(@RequestParam String pincode) {

		String date= "16-08-2021";
		String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode="+ pincode + "&date=" + date;
		List<Session> vaccines = restTemplate.getForEntity(url, CenterDetails.class).getBody().getSessions();

		return new ModelAndView("vaccine","vaccines",vaccines);

	}
	
}
