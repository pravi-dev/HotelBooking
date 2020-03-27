package com.m3bi.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.m3bi.api.model.Room;

@Repository
public interface RoomDao extends JpaRepository<Room, Long> {

	// @Query("select u from Room u where u.rmstatus = ?0")

	// @Query(value = "SELECT * FROM ROOM WHERE RMSTATUS = ?1", nativeQuery = true)
	List<Room> findByrmStatus(String rmstatus);

	Room findByrmid(int rmid);

	// List<Room> findByRmstatus(String available);
}
