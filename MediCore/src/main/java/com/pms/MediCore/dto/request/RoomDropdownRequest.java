package com.pms.MediCore.dto.request;

public class RoomDropdownRequest {

    private Long roomCategory;
    private Long roomType;
    private Long wardNo;

    public RoomDropdownRequest(){

    }
    public RoomDropdownRequest(Long roomCategory, Long roomType, Long wardNo) {
        this.roomCategory = roomCategory;
        this.roomType = roomType;
        this.wardNo = wardNo;
    }
    public Long getRoomCategory() {
        return roomCategory;
    }
    public void setRoomCategory(Long roomCategory) {
        this.roomCategory = roomCategory;
    }
    public Long getRoomType() {
        return roomType;
    }
    public void setRoomType(Long roomType) {
        this.roomType = roomType;
    }
    public Long getWardNo() {
        return wardNo;
    }
    public void setWardNo(Long wardNo) {
        this.wardNo = wardNo;
    }

}
