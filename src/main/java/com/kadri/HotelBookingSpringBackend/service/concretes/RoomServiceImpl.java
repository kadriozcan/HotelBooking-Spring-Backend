package com.kadri.HotelBookingSpringBackend.service.concretes;

import com.kadri.HotelBookingSpringBackend.model.Room;
import com.kadri.HotelBookingSpringBackend.repository.RoomRepository;
import com.kadri.HotelBookingSpringBackend.service.abstracts.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository repository;

    @Override
    public Room add(MultipartFile file, String roomType, BigDecimal roomPrice) throws IOException, SQLException {
        Room room = new Room();
        room.setRoomType(roomType);
        room.setRoomPrice(roomPrice);

        if (!file.isEmpty()) {
            byte[] photoBytes = file.getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);
            room.setPhoto(photoBlob);
        }
        return repository.save(room);
    }

    @Override
    public List<String> getAllRoomTypes() {
        return repository.findRoomTypes();
    }

    @Override
    public List<Room> getAllRooms() {
        return repository.findAll();
    }

    @Override
    public Room getRoomById(Long id) {
        Optional<Room> optionalRoom = repository.findById(id);
        return optionalRoom.orElse(null);
    }

    @Override
    public void deleteRoom(Long roomId) {
        Optional<Room> roomToDelete = repository.findById(roomId);
        if (roomToDelete.isPresent()) {
            repository.deleteById(roomId);
        }
    }


}
