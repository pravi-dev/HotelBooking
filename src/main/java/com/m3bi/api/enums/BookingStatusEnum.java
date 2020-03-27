package com.m3bi.api.enums;

public enum BookingStatusEnum {

	BOOKED, PENDING_APPROVAL, AVAILABLE;

	@Override
	public String toString() {
		return this.name();
	}
}
