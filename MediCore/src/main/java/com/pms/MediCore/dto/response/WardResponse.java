package com.pms.MediCore.dto.response;

public class WardResponse {

    private Long wardId;
    private String wardName;
    private String description;
    private Long active;

    public WardResponse() {

    }
    public WardResponse(Long wardId, String wardName, String description, Long active) {
        this.wardId = wardId;
        this.wardName = wardName;
        this.description = description;
        this.active = active;
    }
    public Long getWardId() {
        return wardId;
    }
    public void setWardId(Long wardId) {
        this.wardId = wardId;
    }
    public String getWardName() {
        return wardName;
    }
    public void setWardName(String wardName) {
        this.wardName = wardName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Long getActive() {
        return active;
    }
    public void setActive(Long active) {
        this.active = active;
    }
}
