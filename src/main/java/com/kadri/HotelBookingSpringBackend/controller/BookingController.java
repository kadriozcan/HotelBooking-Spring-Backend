package com.kadri.HotelBookingSpringBackend.controller;

import com.kadri.HotelBookingSpringBackend.dto.request.BookingRequest;
import com.kadri.HotelBookingSpringBackend.exception.InvalidBookingRequestException;
import com.kadri.HotelBookingSpringBackend.exception.ResourceNotFoundException;
import com.kadri.HotelBookingSpringBackend.dto.response.BookingResponse;
import com.kadri.HotelBookingSpringBackend.mapper.BookingMapper;
import com.kadri.HotelBookingSpringBackend.model.Booking;
import com.kadri.HotelBookingSpringBackend.service.abstracts.BookingService;
import com.kadri.HotelBookingSpringBackend.service.abstracts.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;
    private final RoomService roomService;
    private final BookingMapper bookingMapper;

    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAll() {
        List<Booking> bookings = bookingService.getAll();
        return ResponseEntity.ok(BookingMapper.INSTANCE.toDtoList(bookingService.getAll()));
    }

    @GetMapping("/confirmation/{confirmationCode}")
    public ResponseEntity getByConfirmationCode(@PathVariable String confirmationCode) {
        try {
            Booking booking = bookingService.getByConfirmationCode(confirmationCode);
            return ResponseEntity.ok(booking);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity save(@RequestBody Booking booking) {
        try {
            String confirmationCode = bookingService.save(booking);
            return ResponseEntity.ok("Room booked successfully! Your confirmation code is: " + confirmationCode);
        } catch (InvalidBookingRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{bookingId}")
    public void cancelBooking(Long bookingId) {
        bookingService.cancelBooking(bookingId);
    }
}
