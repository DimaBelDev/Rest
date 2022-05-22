package ru.example.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.example.rest.controllers.UserController;
import ru.example.rest.entity.User;
import ru.example.rest.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class RestApplicationTests {

	@MockBean
	private UserService userService;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void shouldCreateUser() throws Exception {
		User user = new User(1L,"Ivan", "Ivanov", "14.01.1987", 3);
		mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isCreated())
				.andDo(print());
	}
	@Test
	void shouldReturnUser() throws Exception {
		long id = 1L;
		User user = new User(1L,"Ivan", "Ivanov", "14.01.1987", 3);
		when(userService.findById(id)).thenReturn(user);
		mockMvc.perform(get("/api/users/{id}", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.firstname").value(user.getFirstname()))
				.andExpect(jsonPath("$.lastname").value(user.getLastname()))
				.andExpect(jsonPath("$.data").value(user.getData()))
				.andExpect(jsonPath("$.party").value(user.getParty()))
				.andDo(print());
	}
	@Test
	void shouldReturnNotFoundUser() throws Exception {
		long id = 1L;
		when(userService.findById(id)).thenReturn(null);
		mockMvc.perform(get("/api/users/{id}", id))
				.andExpect(status().isNotFound())
				.andDo(print());
	}
	@Test
	void shouldReturnListOfUsers() throws Exception {
		List<User> users = new ArrayList<>(
				Arrays.asList(new User(1L,"Ivan", "Ivanov", "14.01.1987", 3),
						new User(2L,"Vana", "Vanov", "14.01.1997", 2),
						new User(3L,"Sasha", "Seregin", "14.01.1999", 4)));
		when(userService.findALL()).thenReturn(users);
		mockMvc.perform(get("/api/users"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(users.size()))
				.andDo(print());
	}
	@Test
	void shouldUpdateUser() throws Exception {
		long id = 1L;
		User user = new User(1L,"Ivan", "Ivanov", "14.01.1987", 3);
		User updateUser = new User(id, "Update", "Update", "000000", 2);
		when(userService.findById(id)).thenReturn(user);
		when(userService.save(any(User.class))).thenReturn(1);
		mockMvc.perform(put("/api/users/{id}", id).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(updateUser)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstname").value(updateUser.getFirstname()))
				.andExpect(jsonPath("$.lastname").value(updateUser.getLastname()))
				.andExpect(jsonPath("$.data").value(updateUser.getData()))
				.andExpect(jsonPath("$.party").value(updateUser.getParty()))
				.andDo(print());
	}

}
