package com.kadri.HotelBookingSpringBackend.mapper;

import com.kadri.HotelBookingSpringBackend.model.Booking;
import com.kadri.HotelBookingSpringBackend.dto.response.BookingResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Mapper
public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    BookingResponse toDto(Booking booking);

    List<BookingResponse> toDtoList(List<Booking> bookings);

    default String blobToString(Blob photo) {
        if (photo != null) {
            try {
                byte[] photoBytes = photo.getBytes(1, (int) photo.length());
                return Base64.encodeBase64String(photoBytes);
            } catch (SQLException e) {
                throw new RuntimeException("Failed to convert Blob to String", e);
            }
        }
        return null;
    }
}
