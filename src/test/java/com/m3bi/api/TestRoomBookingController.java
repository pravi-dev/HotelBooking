package com.m3bi.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.m3bi.api.controller.RoomBookingController;
import com.m3bi.api.model.Users;
import com.m3bi.api.services.UserServices;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = TestRoomBookingController.class)
public class TestRoomBookingController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserServices userService;

	@MockBean
	Users user;

	private List<Users> userList;

	@InjectMocks
	private RoomBookingController roomBookController;

	@BeforeEach
	void setUp() {

		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(roomBookController).setControllerAdvice(new Exception()).build();

		user.setUserid(1);
		user.setFirstName("Pradip");
		user.setLastName("Chinchodkar");
		user.setGender("male");
		user.setAge(30);
		user.setBonusPoints(50000);
		this.userList = new ArrayList<>();
		this.userList.add(user);

	}

	@Test
	public void shouldFetchAllUsers() throws Exception {

		Mockito.when(userService.getAllUsers()).thenReturn(userList);

		this.mockMvc.perform(get("/getUserDetails")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(userList.size())));
	}

	@Test
	public void testGetUserById() throws Exception {
		Optional<Users> optional = Optional.of(user);
		Mockito.when(userService.getUserById(Mockito.anyInt())).thenReturn(optional);

		this.mockMvc.perform(get("/getUserById").header("userId", 1)).andExpect(status().isOk())
				.andExpect(jsonPath("$.userid", is(user.getUserid())))
				.andExpect(jsonPath("$.lastName", is(user.getLastName())))
				.andExpect(jsonPath("$.firstName", is(user.getFirstName())));
	}
}
