package com.govtech.dojo;

import com.govtech.dojo.controller.UserController;
import com.govtech.dojo.model.User;
import com.govtech.dojo.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("test")
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	private List<User> userList;

	@Before
	public void setUp() {
		this.userList = new ArrayList<>();
		this.userList.add(new User());
		this.userList.add(new User());

	}

	@Test
	public void testLogin() throws Exception {
		User user = new User();
		mockMvc.perform(get("/login").content(
				"{\"userName\":\"testUserDetails\",\"firstName\":\"xxx\",\"lastName\":\"xxx\",\"password\":\"xxx\"}"))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType(MediaType.TEXT_HTML));
	}

	@Test
	public void testHome() throws Exception {
		User user = new User();
		mockMvc.perform(get("/home").content(
				"{\"userName\":\"testUserDetails\",\"firstName\":\"xxx\",\"lastName\":\"xxx\",\"password\":\"xxx\"}"))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType(MediaType.TEXT_HTML));
	}

	@Test
	public void testCreateAccount() throws Exception {
		User user = new User();
		mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_FORM_URLENCODED).content(
				"{\"userName\":\"testUserDetails\",\"firstName\":\"xxx\",\"lastName\":\"xxx\",\"password\":\"xxx\"}"))
				.andDo(print()).andExpect(status().isCreated()).andExpect(content().contentType(MediaType.TEXT_HTML));
	}

}