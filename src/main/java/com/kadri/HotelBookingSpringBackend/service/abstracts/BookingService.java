package com.kadri.HotelBookingSpringBackend.service.abstracts;

import com.kadri.HotelBookingSpringBackend.dto.request.BookingRequest;
import com.kadri.HotelBookingSpringBackend.dto.response.BookingResponse;
import com.kadri.HotelBookingSpringBackend.model.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> getAll();

    List<Booking> getAllByRoomId(Long roomId);

    Booking getByConfirmationCode(String confirmationCode);

    String save(Booking booking);

    void cancelBooking(Long bookingId);
}
