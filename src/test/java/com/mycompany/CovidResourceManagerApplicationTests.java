package com.mycompany;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;
import com.mycompany.controller.HomeController;
import com.mycompany.controller.PostController;
import com.mycompany.controller.UserController;
import com.mycompany.dao.IPostFunctionDAO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
public class CovidResourceManagerApplicationTests {
	

	public void contextLoads() {

	}
	

	
}