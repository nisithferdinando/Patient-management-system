package com.pms.MediCore.dto.response;

public class RoomDropdownResponse {

    private Long roomId;
    private Long roomCategory;
    private String roomCategoryName;
    private Long roomType;
    private String roomTypeName;
    private Long wardNo;
    private Long roomNo;
    private Long bedNo;

    public RoomDropdownResponse(){

    }

    public RoomDropdownResponse(Long roomId, Long roomCategory, String roomCategoryName, Long roomType, Long wardNo, Long roomNo, Long bedNo) {
        this.roomId = roomId;
        this.roomCategory = roomCategory;
        this.roomCategoryName = roomCategoryName;
        this.roomType = roomType;
        this.wardNo = wardNo;
        this.roomNo = roomNo;
        this.bedNo = bedNo;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(Long roomCategory) {
        this.roomCategory = roomCategory;
    }

    public String getRoomCategoryName() {
        return roomCategoryName;
    }

    public void setRoomCategoryName(String roomCategoryName) {
        this.roomCategoryName = roomCategoryName;
    }

    public Long getRoomType() {
        return roomType;
    }

    public void setRoomType(Long roomType) {
        this.roomType = roomType;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public Long getWardNo() {
        return wardNo;
    }

    public void setWardNo(Long wardNo) {
        this.wardNo = wardNo;
    }

    public Long getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Long roomNo) {
        this.roomNo = roomNo;
    }

    public Long getBedNo() {
        return bedNo;
    }

    public void setBedNo(Long bedNo) {
        this.bedNo = bedNo;
    }
}
