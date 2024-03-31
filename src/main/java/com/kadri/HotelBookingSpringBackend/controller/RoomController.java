package com.kadri.HotelBookingSpringBackend.controller;

import com.kadri.HotelBookingSpringBackend.mapper.RoomMapper;
import com.kadri.HotelBookingSpringBackend.model.Room;
import com.kadri.HotelBookingSpringBackend.response.RoomResponse;
import com.kadri.HotelBookingSpringBackend.service.abstracts.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/add")
    public ResponseEntity<RoomResponse> addNewRoom(@RequestParam("photo") MultipartFile file,
                                                   @RequestParam("roomType") String roomType,
                                                   @RequestParam("roomPrice") BigDecimal roomPrice) throws SQLException, IOException {
        Room savedRoom = roomService.add(file, roomType, roomPrice);

        return ResponseEntity.ok(RoomMapper.INSTANCE.toRoomResponse(savedRoom));
    }
}
