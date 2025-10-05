package com.pms.MediCore.serviceImplementation;

import com.pms.MediCore.dto.request.PatientRoomSearchRequest;
import com.pms.MediCore.dto.request.RoomDropdownRequest;
import com.pms.MediCore.dto.request.RoomRequest;
import com.pms.MediCore.dto.response.PatientRoomSearchResponse;
import com.pms.MediCore.dto.response.RoomDropdownResponse;
import com.pms.MediCore.dto.response.RoomResponse;
import com.pms.MediCore.entity.PatientRoom;
import com.pms.MediCore.entity.Room;
import com.pms.MediCore.repository.PatientRoomRepository;
import com.pms.MediCore.repository.PatientRoomSearchRepository;
import com.pms.MediCore.repository.RoomDropdownRepository;
import com.pms.MediCore.repository.RoomRepository;
import com.pms.MediCore.service.RoomService;
import com.pms.MediCore.view.PatientRoomSearch;
import com.pms.MediCore.view.RoomDropdown;
import org.hibernate.jdbc.Expectation;
import org.hibernate.jdbc.Expectations;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final PatientRoomRepository patientRoomRepository;
    private final PatientRoomSearchRepository patientRoomSearchRepository;
    private final RoomDropdownRepository roomDropdownRepository;

    public RoomServiceImpl(RoomRepository roomRepository, ModelMapper modelMapper, PatientRoomRepository patientRoomRepository, PatientRoomSearchRepository patientRoomSearchRepository, RoomDropdownRepository roomDropdownRepository) {
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
        this.patientRoomRepository = patientRoomRepository;
        this.patientRoomSearchRepository = patientRoomSearchRepository;
        this.roomDropdownRepository = roomDropdownRepository;
    }

    @Override
    public List<RoomRequest> addRoom(List<RoomRequest> roomRequest) {
        List<Room> room = roomRequest.stream().map(r -> {
            Room rooms = (r.getRoomId() != null) ? roomRepository.findById(r.getRoomId()).orElse(new Room())
                    : new Room();
            modelMapper.map(r, rooms);

            if (rooms.getRoomId() == null) {
                rooms.setCreatedDate(new Date());

            }
            rooms.setUpdatedDate(new Date());
            rooms.setActive(1L);
            return rooms;

        }).collect(Collectors.toList());
        List<Room> savedRoom = roomRepository.saveAll(room);
        savedRoom.forEach(r -> {
            if (r.getRoomNo() != null) {
                PatientRoom patientRoom = patientRoomRepository.findByRoomNo(r.getRoomNo()).orElse(null);
                if (patientRoom != null) {
                    patientRoom.setRoomStatus(2L);
                    patientRoom.setUpdatedOn(new Date());
                    patientRoomRepository.save(patientRoom);
                }
            }
        });
        return savedRoom.stream().map(r -> modelMapper.map(r, RoomRequest.class)).collect(Collectors.toList());
    }

    @Override
    public List<RoomResponse> getRoomByPatientId(Long id) {
        List<Room> room = roomRepository.findByPatientIdAndActive(id, 1L);
        return room.stream().map(r -> modelMapper.map(r, RoomResponse.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteRoom(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        room.setActive(2L);
        room.setUpdatedDate(new Date());
        roomRepository.save(room);
    }

    @Override
    public List<RoomResponse> updateRoom(List<RoomRequest> roomRequest) {
        List<Room> rooms = roomRequest.stream().map(req -> {
            Room room = roomRepository.findById(req.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found"));
            Date existingCreatedDate=room.getCreatedDate();
            modelMapper.map(req, room);
            room.setCreatedDate(existingCreatedDate);
            room.setUpdatedDate(new Date());
            return room;
        }).collect(Collectors.toList());
        List<Room> savedRoom = roomRepository.saveAll(rooms);
        savedRoom.forEach(room -> {
            if (room.getRoomNo() != null) {
                PatientRoom patientRoom = patientRoomRepository.findByRoomNo(room.getRoomNo()).orElse(null);
                if (patientRoom != null) {
                    if (room.getRoomStatus() != null) {
                        if (room.getRoomStatus() == 1L) {
                            patientRoom.setRoomStatus(1L);
                            patientRoom.setUpdatedOn(new Date());
                        } else if (room.getRoomStatus() == 2L) {
                            patientRoom.setRoomStatus(1L);
                            patientRoom.setUpdatedOn(new Date());
                        }
                    }
                    patientRoomRepository.save(patientRoom);
                }
            }
        });
        return savedRoom.stream().map(r -> modelMapper.map(r, RoomResponse.class)).collect(Collectors.toList());
    }

    @Override
    public List<PatientRoomSearchResponse> searchRoom(PatientRoomSearchRequest patientRoomSearchRequest){
        try{
            List<PatientRoomSearch> patientRoomSearch= patientRoomSearchRepository.searchPatientRoomsByFilters(
                    patientRoomSearchRequest.getPatientRegNo()==null? "" : patientRoomSearchRequest.getPatientRegNo(),
                    patientRoomSearchRequest.getWard()==null? -2L : patientRoomSearchRequest.getWard(),
                    patientRoomSearchRequest.getRoomType()==null? -2L : patientRoomSearchRequest.getRoomType(),
                    patientRoomSearchRequest.getRoomCategory()==null? -2L : patientRoomSearchRequest.getRoomCategory()
            );
            return patientRoomSearch.stream().map(p->modelMapper.map(p, PatientRoomSearchResponse.class)).collect(Collectors.toList());
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RoomDropdownResponse> getRoomDropdown(RoomDropdownRequest roomDropdownRequest) {
        List<RoomDropdown> roomDropdown = roomDropdownRepository.getRoomsByCategory(
                roomDropdownRequest.getRoomCategory(),
                roomDropdownRequest.getRoomType(),
                roomDropdownRequest.getWardNo()
        );
        return roomDropdown.stream().map(r->modelMapper.map(r, RoomDropdownResponse.class)).collect(Collectors.toList());
    }

    @Override
    public List<RoomResponse> getRoomByPatientRegNo(Long roomStatus){
        List<Room> room= roomRepository.findByRoomStatus(2L);
        return room.stream().map(rooms-> modelMapper.map(rooms, RoomResponse.class)).collect(Collectors.toList());
    }
}
