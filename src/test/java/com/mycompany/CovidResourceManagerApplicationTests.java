
package com.mycompany;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;
import com.mycompany.controller.HomeController;
import com.mycompany.controller.PostController;
import com.mycompany.controller.UserController;
import com.mycompany.dao.IPostFunctionDAO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
public class CovidResourceManagerApplicationTests {
	
	@MockBean
	private IPostFunctionDAO repo;
	
	@Autowired
	private PostController postController;
	@Autowired
	private HomeController homeController;
	@Autowired
	private UserController userController;
	
	@Autowired
	private MockMvc mockMvc;


	@Test
	public void contextLoads() throws Exception {
		assertThat(postController).isNotNull();
		assertThat(homeController).isNotNull();
		assertThat(userController).isNotNull();
	}
	
	@Test
	public void checkHTTPResponse() throws Exception {
		this.mockMvc.perform(get("/user/login")).andExpect(status().isOk());
		this.mockMvc.perform(get("/user/register")).andExpect(status().isOk());
	}
	
}