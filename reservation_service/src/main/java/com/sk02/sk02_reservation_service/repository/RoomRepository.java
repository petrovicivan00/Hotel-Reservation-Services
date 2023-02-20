package com.sk02.sk02_reservation_service.repository;

import com.sk02.sk02_reservation_service.domain.Hotel;
import com.sk02.sk02_reservation_service.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findRoomByRoomNumberAndHotel(int roomNumber, Hotel hotel);
    Room findRoomByRoomNumberAndHotelId(int roomNumber, Long hotelId);
}
