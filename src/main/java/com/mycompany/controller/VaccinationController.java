package com.mycompany.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.entity.CenterDetails;
import com.mycompany.entity.Session;


@Controller
public class VaccinationController {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/vaccination")
	public ModelAndView getDetailsByPin(@RequestParam String pincode, @RequestParam String date) throws ParseException {
	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	  DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode="+ pincode + "&date="+ LocalDate.parse(date, formatter2).format(formatter);

		List<Session> vaccines = restTemplate.getForEntity(url, CenterDetails.class).getBody().getSessions();
		
		return new ModelAndView("vaccine","vaccines",vaccines);

	}
}
