package com.m3bi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m3bi.api.dao.UserDao;
import com.m3bi.api.model.Users;

@Service
public class UserServices {

	@Autowired
	UserDao userDao;

	public List<Users> getAllUsers() {
		List<Users> userList = this.userDao.findAll();
		return userList;
	}

	public Optional<Users> getUserById(int userId) {

		return this.userDao.findById(userId);

	}

	public void updateBonus(Users user) {

		userDao.save(user);
	}

}
