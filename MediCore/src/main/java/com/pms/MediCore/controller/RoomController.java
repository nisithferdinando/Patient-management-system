package com.pms.MediCore.controller;

import com.pms.MediCore.dto.request.PatientRoomSearchRequest;
import com.pms.MediCore.dto.request.RoomDropdownRequest;
import com.pms.MediCore.dto.request.RoomRequest;
import com.pms.MediCore.dto.response.PatientRoomSearchResponse;
import com.pms.MediCore.dto.response.RoomDropdownResponse;
import com.pms.MediCore.dto.response.RoomResponse;
import com.pms.MediCore.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/add")
    public ResponseEntity<List<RoomRequest>> addRoom(@RequestBody List<RoomRequest> roomRequest) {
        List<RoomRequest> rooms=roomService.addRoom(roomRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<RoomResponse>>getByPatientId(@PathVariable("id") Long id){
        List<RoomResponse> roomResponse=roomService.getRoomByPatientId(id);
        return ResponseEntity.status(HttpStatus.OK).body(roomResponse);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable("id") Long id){
        roomService.deleteRoom(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/update")
    public ResponseEntity<List<RoomResponse>> updateRoom(@RequestBody List<RoomRequest> roomRequest){
        List<RoomResponse> rooms=roomService.updateRoom(roomRequest);
        return ResponseEntity.status(HttpStatus.OK).body(rooms);
    }
    @PostMapping("/search")
    public ResponseEntity<List<PatientRoomSearchResponse>>searchRoom(@RequestBody PatientRoomSearchRequest patientRoomSearchRequest){
        List<PatientRoomSearchResponse> patientRoomSearchResponse=roomService.searchRoom(patientRoomSearchRequest);
        return ResponseEntity.status(HttpStatus.OK).body(patientRoomSearchResponse);
    }

    @PostMapping("/dropdown")
    public ResponseEntity<List<RoomDropdownResponse>>getDropdown(@RequestBody RoomDropdownRequest roomDropdownRequest){
        List<RoomDropdownResponse> dropdownResponse=roomService.getRoomDropdown(roomDropdownRequest);
        return ResponseEntity.status(HttpStatus.OK).body(dropdownResponse);
    }


}
