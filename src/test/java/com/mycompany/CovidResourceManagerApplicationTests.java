
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
import com.mycompany.entity.Post;
import com.mycompany.entity.User;
import com.mycompany.service.PostService;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CovidResourceManagerApplicationTests {

	@Test
	public void contextLoads() throws Exception {
		
	}
	
}