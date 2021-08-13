package com.mycompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.exception.IncorrectUserException;
import com.mycompany.service.NotificationService;

@Controller
@RequestMapping("notifications")
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;

	@GetMapping("/") 
	public String showNotifications(Model model) {
		model.addAttribute("notifications", notificationService.getNotifications());
		return "notifications";
	}
	
	@RequestMapping("/delete/{id}") 
	public String deleteNotifications(@PathVariable int id) throws IncorrectUserException {
		notificationService.deleteNotification(id);
		return "redirect:/notifications/";
	}
	
	@GetMapping("/post/{id}") 
	public String showPost(@PathVariable int id) {
		return "redirect:/post/"+id;
	}
}
