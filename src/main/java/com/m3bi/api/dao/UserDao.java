package com.m3bi.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.m3bi.api.model.Users;

@Repository
public interface UserDao extends JpaRepository<Users, Integer> {

}