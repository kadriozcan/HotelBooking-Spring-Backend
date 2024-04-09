package com.kadri.HotelBookingSpringBackend.mapper;

import com.kadri.HotelBookingSpringBackend.model.Room;
import com.kadri.HotelBookingSpringBackend.response.RoomResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.Blob;
import java.sql.SQLException;

@Mapper
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "roomType", target = "roomType")
    @Mapping(source = "roomPrice", target = "roomPrice")
    @Mapping(source = "photo", target = "photo", qualifiedByName = "blobToString")
    RoomResponse toRoomResponse(Room room);

    @Named("blobToString")
    default String map(Blob photo) {
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
