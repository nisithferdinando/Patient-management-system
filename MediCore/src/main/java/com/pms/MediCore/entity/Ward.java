package com.pms.MediCore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pms_h_wards", schema = "hospital")
public class Ward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ward_id")
    private Long wardId;

    @Column(name = "ward_name")
    private String wardName;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Long active;

    public Ward(){

    }
    public Ward( Long wardId, String wardName, String description, Long active) {
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
