package com.mycompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mycompany.entity.CenterDetails;
import com.mycompany.entity.Session;

@RestController
public class VaccinationController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/vaccination")
	public String getDetailsByPin(@RequestParam String pincode, @RequestParam String date, Model model) {
		Session sess = new Session();
		CenterDetails detail = new CenterDetails();
		
		String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode="+ pincode + "&date=" + date;
		CenterDetails vaccines = restTemplate.getForEntity(url, CenterDetails.class).getBody();
		detail.setSessions(vaccines.getSessions());
		model.addAttribute("sessions", vaccines);
		System.out.println(vaccines);
		return "vaccineslots";
		
		
//		try {
//		       RestTemplate restTemplate = new RestTemplate();
//
//		       CenterDetails[] forObject =
//		                    restTemplate.getForEntity("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode=411030&date=16/08/2021", CenterDetails[].class).getBody();
//		          for (CenterDetails currencyDTO1 : forObject) {
//		                for (Session rate : currencyDTO1.getSessions()) {
//		                    Session currency = new Session(rate.getCenterId(), rate.getName(), rate.getAddress(), rate.getStateName(),
//		                    		rate.getDistrictName(), rate.getBlockName(), rate.getPincode(), rate.getFrom(),
//		                    		rate.getTo(), rate.getLat(), rate.getLong(), rate.getFeeType(), 
//		                    		rate.getSessionId(), rate.getDate(), rate.getAvailableCapacity(), rate.getAvailableCapacityDose1(),
//		                    		rate.getAvailableCapacityDose2(),rate.getFee() ,rate.getMinAgeLimit(), rate.isAllowAllAge(), rate.getVaccine(),
//		                    		rate.getSlots());
//		                    //you code to put the object inside the db
//		                    System.out.println(currency);
//		                }
//		            }
//		        }catch (Exception ex) {
//		            System.out.println(ex.getMessage());
//		        }
//		return "ok";
//	}
	
	}
}