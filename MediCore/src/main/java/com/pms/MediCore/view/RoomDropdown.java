package com.pms.MediCore.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
public class RoomDropdown {

    @Id
    private Long roomId;
    private Long roomCategory;
    private String roomCategoryName;
    private Long roomType;
    private String roomTypeName;
    private Long wardNo;
    private Long roomNo;
    private Long bedNo;

    public RoomDropdown() {
    }

    public RoomDropdown(Long roomId, Long roomCategory, String roomCategoryName, Long roomType, String roomTypeName, Long wardNo, Long roomNo, Long bedNo) {
        this.roomId = roomId;
        this.roomCategory = roomCategory;
        this.roomCategoryName = roomCategoryName;
        this.roomType = roomType;
        this.roomTypeName = roomTypeName;
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
