package com.govtech.dojo;

import com.govtech.dojo.model.User;
import com.govtech.dojo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Matches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DojoApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {

	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;


	private List<User> userList;

	@Before
	public void setUp() { }
	
	@Test
	public void testHome() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType("text/html;charset=UTF-8"))
		.andExpect(view().name("user/login"))
		.andExpect(content().string(containsString("Welcome to the Login Form Demo!")));
	}
	
	@Test
	public void testHomeSignup() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/signup"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType("text/html;charset=UTF-8"))
		.andExpect(view().name("user/signup"))
		.andExpect(content().string(containsString("Signup for an account")));}

	@Test
	public void testCreateAccount() throws Exception {
		User user = new User();
		mockMvc.perform(post("/signup").contentType(MediaType.APPLICATION_FORM_URLENCODED).with(csrf()).content(
				"{\"email\":\"demo@example.com\",\"firstName\":\"xxx\",\"lastName\":\"xxx\",\"password\":\"xxx\"}"))
				.andDo(print()).andExpect(status().isCreated()).andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(content().string(containsString("Signup for an account")));
				//.andExpect(content().source());
	}

	@Test
	public void testWelcomePage() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/users/welcome"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType("text/html;charset=UTF-8"))
		.andExpect(view().name("user/welcome"))
		.andExpect(content().string(containsString("welcome to coding dojo")));
	}

	@Test
	public void testLoginPage() throws Exception {
		User user = new User();
		user.setEmail("email@example.com");
		user.setPassword("xxx");
		userService.saveUser(user);

		mockMvc.perform(post("/signin").contentType(MediaType.APPLICATION_FORM_URLENCODED).content(
				"email=email@example.com&password=xxx"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(content().string(containsString("welcome to coding dojo")));
	}

	@Test
	public void testLogout() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/users/logout"))
				.andDo(print())
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/?successMsg=Successfully+logged+out"));
	}
}