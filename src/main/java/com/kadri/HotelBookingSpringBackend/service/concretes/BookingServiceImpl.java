package com.kadri.HotelBookingSpringBackend.service.concretes;

import com.kadri.HotelBookingSpringBackend.exception.InvalidBookingRequestException;
import com.kadri.HotelBookingSpringBackend.model.Booking;
import com.kadri.HotelBookingSpringBackend.model.Room;
import com.kadri.HotelBookingSpringBackend.repository.BookingRepository;
import com.kadri.HotelBookingSpringBackend.service.abstracts.BookingService;
import com.kadri.HotelBookingSpringBackend.service.abstracts.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final RoomService roomService;

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> getAllByRoomId(Long roomId) {
        return bookingRepository.findByRoomId(roomId);
    }

    @Override
    public Booking getByConfirmationCode(String confirmationCode) {
        return bookingRepository.findByConfirmationCode(confirmationCode);
    }

    @Override
    public String save(Booking booking) {
        if (booking.getCheckOutDate().isBefore(booking.getCheckInDate())) {
            throw new InvalidBookingRequestException("Check-in date must come before check-out date");
        }
        Room room = roomService.getRoomById(booking.getId());
        List<Booking> existingBookings = room.getBookings();
        boolean roomIsAvailable = roomIsAvailable(booking, existingBookings);
        if (roomIsAvailable) {
            room.addBooking(booking);
            bookingRepository.save(booking);
        } else {
            throw new InvalidBookingRequestException("This room is not available for selected dates!");
        }
        return booking.getConfirmationCode();
    }

    @Override
    public void cancelBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    private boolean roomIsAvailable(Booking booking, List<Booking> existingBookings) {
        return existingBookings.stream()
                .noneMatch(existingBooking ->
                        booking.getCheckInDate().equals(existingBooking.getCheckInDate())
                                || booking.getCheckOutDate().isBefore(existingBooking.getCheckOutDate())
                                || (booking.getCheckInDate().isAfter(existingBooking.getCheckInDate())
                                && booking.getCheckInDate().isBefore(existingBooking.getCheckOutDate()))
                                || (booking.getCheckInDate().isBefore(existingBooking.getCheckInDate())

                                && booking.getCheckOutDate().equals(existingBooking.getCheckOutDate()))
                                || (booking.getCheckInDate().isBefore(existingBooking.getCheckInDate())

                                && booking.getCheckOutDate().isAfter(existingBooking.getCheckOutDate()))

                                || (booking.getCheckInDate().equals(existingBooking.getCheckOutDate())
                                && booking.getCheckOutDate().equals(existingBooking.getCheckInDate()))

                                || (booking.getCheckInDate().equals(existingBooking.getCheckOutDate())
                                && booking.getCheckOutDate().equals(booking.getCheckInDate()))
                );
    }
}
