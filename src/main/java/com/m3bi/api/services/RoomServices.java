package com.m3bi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m3bi.api.dao.RoomDao;
import com.m3bi.api.model.Room;

@Service
public class RoomServices {

	@Autowired
	RoomDao roomDao;

	public List<Room> getAvaliableRoom(String status) {

		return roomDao.findByrmStatus(status);
	}

	public List<Room> getApprovalPendingRooms(int userId, String status) {

		return roomDao.findByUseridAndRmStatus(userId, status);
	}

	public void updateRoomRecord(List<Room> listRoom) {

		roomDao.saveAll(listRoom);
	}

	public Optional<Room> getRoomDetails(int roomId) {

		return Optional.of(roomDao.findByrmid(roomId));
	}

	public void bookRoom(Room room) {

		roomDao.save(room);
	}

}
