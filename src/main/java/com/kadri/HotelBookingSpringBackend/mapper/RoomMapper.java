package com.kadri.HotelBookingSpringBackend.mapper;

import com.kadri.HotelBookingSpringBackend.model.Room;
import com.kadri.HotelBookingSpringBackend.response.RoomResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "roomType", target = "roomType")
    @Mapping(source = "roomPrice", target = "roomPrice")
    RoomResponse toRoomResponse(Room room);
}
