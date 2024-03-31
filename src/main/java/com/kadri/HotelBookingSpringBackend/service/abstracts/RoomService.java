package com.kadri.HotelBookingSpringBackend.service.abstracts;

import com.kadri.HotelBookingSpringBackend.mapper.RoomMapper;
import com.kadri.HotelBookingSpringBackend.model.Room;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

public interface RoomService {
    Room add(MultipartFile file, String roomType, BigDecimal roomPrice) throws IOException, SQLException;
}
