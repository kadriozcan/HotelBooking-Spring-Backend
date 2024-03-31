package com.kadri.HotelBookingSpringBackend.repository;

import com.kadri.HotelBookingSpringBackend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
