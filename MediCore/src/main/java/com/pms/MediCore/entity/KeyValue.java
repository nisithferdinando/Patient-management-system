package com.pms.MediCore.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "keyvalues", schema = "hospital")
public class KeyValue {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "key_value")
    private String keyValue;

    @Column(name = "value")
    private Long value;

    @Column(name = "value_name")
    private String valueName;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Long active;

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    public KeyValue() {

    }
    public KeyValue(Long id, String keyValue, Long value, String valueName, String description, Long active, Date lastModifiedDate) {
        this.id = id;
        this.keyValue = keyValue;
        this.value = value;
        this.valueName = valueName;
        this.description = description;
        this.active = active;
        this.lastModifiedDate = lastModifiedDate;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getKeyValue() {
        return keyValue;
    }
    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }
    public Long getValue() {
        return value;
    }
    public void setValue(Long value) {
        this.value = value;
    }
    public String getValueName() {
        return valueName;
    }
    public void setValueName(String valueName) {
        this.valueName = valueName;
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
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}
