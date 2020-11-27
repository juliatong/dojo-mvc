package com.govtech.dojo;

import com.govtech.dojo.controller.UserController;
import com.govtech.dojo.model.User;
import com.govtech.dojo.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("test")
@WithMockUser
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
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
	public void testLogin() throws Exception {
		User user = new User();
		mockMvc.perform(get("/login").content(
				"{\"userName\":\"testUserDetails\",\"firstName\":\"xxx\",\"lastName\":\"xxx\",\"password\":\"xxx\"}"))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("text/html;charset=UTF-8"));
	}

	@Test
	public void testHome() throws Exception {
		User user = new User();
		user.setFirstname("xxx");
		user.setLastname("xxx");
		when(userService.findUserByEmail(anyString())).thenReturn(user);
		mockMvc.perform(get("/home").content(
				"{\"userName\":\"testUserDetails\",\"firstName\":\"xxx\",\"lastName\":\"xxx\",\"password\":\"xxx\"}"))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("text/html;charset=UTF-8"));
	}

	@Test
	public void testCreateAccount() throws Exception {
		User user = new User();
		mockMvc.perform(post("/signup").contentType(MediaType.APPLICATION_FORM_URLENCODED).with(csrf()).content(
				"{\"userName\":\"testUserDetails\",\"firstName\":\"xxx\",\"lastName\":\"xxx\",\"password\":\"xxx\"}"))
				.andDo(print()).andExpect(status().isCreated()).andExpect(content().contentType("text/html;charset=UTF-8"));
	}

}