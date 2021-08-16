package com.mycompany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.entity.CenterDetails;
import com.mycompany.entity.Session;


@Controller
public class VaccinationController {

	@Autowired
	private RestTemplate restTemplate;

//	@GetMapping("/vaccination")
//	public ModelAndView getDetails(Model model1) {
//		//ModelAndView model = new ModelAndView("vaccine-input");
//		return new ModelAndView("vaccine-input");
//	}

	@GetMapping("/vaccination")
	public ModelAndView getDetailsByPin(@RequestParam String pincode, @RequestParam String date) {

		String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode="+ pincode + "&date=" + date;
		List<Session> vaccines = restTemplate.getForEntity(url, CenterDetails.class).getBody().getSessions();
		//detail.setSessions(vaccines.getSessions());
		//System.out.println(vaccines);
		//ModelAndView model = new ModelAndView("vaccine");
		//model.addObject("vaccines", vaccines);
		for (Session session : vaccines) {

			System.out.println(session.getName());

		}

		return new ModelAndView("vaccine","vaccines",vaccines);

	}
}