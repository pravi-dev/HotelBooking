package com.m3bi.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.m3bi.api.Exception.RoomNotFound;
import com.m3bi.api.Exception.UserNotFoundException;
import com.m3bi.api.enums.BookingStatusEnum;
import com.m3bi.api.model.Room;
import com.m3bi.api.model.Users;
import com.m3bi.api.services.RoomServices;
import com.m3bi.api.services.UserServices;

@RestController
public class RoomBookingController {

	@Autowired
	UserServices userService;

	@Autowired
	RoomServices roomService;

	@GetMapping("/getUserDetails")
	public List<Users> getUserDetails() {

		return userService.getAllUsers();
	}

	@GetMapping("/getUserById")
	public Users getUserById(@RequestHeader int userId) throws UserNotFoundException {

		Optional<Users> optional = userService.getUserById(userId);
		if (!optional.isPresent()) {

			throw new UserNotFoundException("User not avialble, please register user");
		}

		return optional.get();
	}

	@GetMapping("/getAvailableRooms")
	public List<Room> getAvailableRooms() throws RoomNotFound {

		List<Room> roomList = roomService.getAvaliableRoom(BookingStatusEnum.AVAILABLE.name());

		if (roomList.size() <= 0) {

			throw new RoomNotFound("No rooms available");
		}

		return roomList;
	}

	@PutMapping("/updateBonusPoints")
	public String updateBonusPoints(@RequestParam int userId, @RequestParam int bounsPoints)
			throws UserNotFoundException, RoomNotFound {

		String status = "Bonus updated sucessfully";
		Optional<Users> optionalUser = userService.getUserById(userId);
		if (!optionalUser.isPresent()) {

			throw new UserNotFoundException("User not avialble, please register user");
		}

		Users user = optionalUser.get();
		int bonusPoints = user.getBonusPoints() + bounsPoints;

		List<Room> roomList = roomService.getApprovalPendingRooms(userId, BookingStatusEnum.PENDING_APPROVAL.name());

		if (roomList.size() != 0) {
			for (Room room : roomList) {

				if (bonusPoints >= room.getRmPrice()) {

					room.setRmStatus(BookingStatusEnum.BOOKED.name());

					bonusPoints = bonusPoints - room.getRmPrice();
				}
			}
			roomService.updateRoomRecord(roomList);

			status = "Room has been booked";
		}

		userService.updateBonus(user);

		return status;

	}

	@PutMapping("/bookRoom")
	public String bookRoom(@RequestParam int userID, @RequestParam int roomId)
			throws UserNotFoundException, RoomNotFound {
		String status = "room booking";
		Optional<Users> optionalUser = userService.getUserById(userID);
		if (!optionalUser.isPresent()) {

			throw new UserNotFoundException("User not avialble, please register user");
		}

		Users user = optionalUser.get();

		Optional<Room> optionalRoom = roomService.getRoomDetails(roomId);

		if (!optionalRoom.isPresent()) {

			throw new RoomNotFound("No rooms available");
		}

		Room room = optionalRoom.get();

		List<Room> roomList = roomService.getAvaliableRoom(BookingStatusEnum.AVAILABLE.name());
		if (roomList.size() <= 0) {

			if (room.getRmStatus().equals(BookingStatusEnum.PENDING_APPROVAL.name())
					&& user.getBonusPoints() >= room.getRmPrice()) {
				room.setRmStatus(BookingStatusEnum.BOOKED.name());
				room.setUserid(user.getUserid());
				user.setBonusPoints(user.getBonusPoints() - room.getRmPrice());
				status = "Congratulations Room has been booked";
			} else {

				status = "No rooms available for booking, you can select room which is pending for approval.";
			}

		} else {

			if (room.getRmStatus().equals(BookingStatusEnum.AVAILABLE.name())
					&& user.getBonusPoints() >= room.getRmPrice()) {
				room.setRmStatus(BookingStatusEnum.BOOKED.name());
				room.setUserid(user.getUserid());
				user.setBonusPoints(user.getBonusPoints() - room.getRmPrice());
				status = "Congratulations Room has been booked";

			} else {

				room.setUserid(user.getUserid());
				room.setRmStatus(BookingStatusEnum.PENDING_APPROVAL.name());
				status = "Booking is pending for approval";
			}

		}

		roomService.bookRoom(room);
		userService.updateBonus(user);
		return status;
	}
}
