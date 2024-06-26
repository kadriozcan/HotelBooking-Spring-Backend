package com.kadri.HotelBookingSpringBackend.service.abstracts;

import com.kadri.HotelBookingSpringBackend.model.Room;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface RoomService {
    Room add(MultipartFile file, String roomType, BigDecimal roomPrice) throws IOException, SQLException;

    List<String> getAllRoomTypes();

    List<Room> getAllRooms();

    void deleteRoom(Long roomId);

    Room getRoomById(Long id);

    byte[] getRoomPhotoByRoomId(Long roomId) throws SQLException;

    Room updateRoom(Long roomId, String roomType, BigDecimal roomPrice, byte[] photoBytes);
}
