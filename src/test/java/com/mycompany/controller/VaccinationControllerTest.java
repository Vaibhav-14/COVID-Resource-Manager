package com.mycompany.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.mycompany.entity.Session;

@SpringBootTest
@AutoConfigureMockMvc
public class VaccinationControllerTest {
	
	@Autowired
	private VaccinationController controller;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void contextLoads() throws Exception{
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void vaccinationController() throws Exception {
		
		MockHttpServletRequestBuilder request = get("/vaccination").param("pincode", "411030").param("date", "2021-08-19") ;
		this.mockMvc.perform(request).andDo(print()).andExpect(forwardedUrl("/WEB-INF/views/vaccine.jsp")).andExpect(view().name("vaccine")) ;
	}

}
