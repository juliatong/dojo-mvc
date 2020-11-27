package com.govtech.dojo;

import com.govtech.dojo.configuration.SecurityConfiguration;
import com.govtech.dojo.controller.UserController;
import com.govtech.dojo.model.User;
import com.govtech.dojo.service.UserService;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes=DojoApplication.class)
//@AutoConfigureMockMvc
@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("test")
@WithMockUser
public class UserControllerTest {

	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private List<User> userList;

	@Before
	public void setUp() {
		this.userList = new ArrayList<>();
		this.userList.add(new User());
		this.userList.add(new User());
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.apply(springSecurity())
				.build();
	}
	
	@Test
	public void testHome() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType("text/html;charset=UTF-8"))
		.andExpect(view().name("user/login"));
	}
	
	@Test
	public void testHomeSignup() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/signup"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType("text/html;charset=UTF-8"))
		.andExpect(view().name("user/welcome"));	}

	@Test
	public void testCreateAccount() throws Exception {
		User user = new User();
		mockMvc.perform(post("/signup").contentType(MediaType.APPLICATION_FORM_URLENCODED).with(csrf()).content(
				"{\"userName\":\"testUserDetails\",\"firstName\":\"xxx\",\"lastName\":\"xxx\",\"password\":\"xxx\"}"))
				.andDo(print()).andExpect(status().isCreated()).andExpect(content().contentType("text/html;charset=UTF-8"));
	}

	@Test
	public void testWelcomePage() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/users/welcome"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType("text/html;charset=UTF-8"))
		.andExpect(view().name("user/welcome"));	}

	@Test
	@Ignore("Not implemented yet")
	public void testLoginPage() throws Exception {
		User user = new User();
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_FORM_URLENCODED).content(
				"{\"userName\":\"testUserDetails\",\"firstName\":\"xxx\",\"lastName\":\"xxx\",\"password\":\"xxx\"}"))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("text/html;charset=UTF-8"));
	}

	@Test
	@Ignore("Not implemented yet")
	public void testLogout() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/users/logout"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(view().name("home/index"));	}

}