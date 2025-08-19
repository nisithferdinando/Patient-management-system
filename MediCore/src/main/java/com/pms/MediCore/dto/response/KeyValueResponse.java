package com.pms.MediCore.dto.response;


public class KeyValueResponse {

    private Long id;
    private String keyValue;
    private Long value;
    private String valueName;
    private Long active;

    public KeyValueResponse() {

    }
    public KeyValueResponse(Long id, String keyValue, Long value, String valueName, Long active) {
        this.id = id;
        this.keyValue = keyValue;
        this.value = value;
        this.valueName = valueName;
        this.active = active;
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
    public Long getActive() {
        return active;
    }
    public void setActive(Long active) {
        this.active = active;
    }
}
