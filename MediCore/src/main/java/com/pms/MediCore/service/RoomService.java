package com.pms.MediCore.service;

import com.pms.MediCore.dto.request.RoomRequest;
import com.pms.MediCore.dto.response.RoomResponse;

import java.util.List;

public interface RoomService {

    List<RoomRequest> addRoom(List<RoomRequest> roomRequest);
    List<RoomResponse>getRoomByPatientId(Long id);
    void deleteRoom(Long id);
    List<RoomResponse> updateRoom(List<RoomRequest> roomRequest);

}
