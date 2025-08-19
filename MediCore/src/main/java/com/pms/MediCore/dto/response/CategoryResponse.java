package com.pms.MediCore.dto.response;

public class CategoryResponse {

    private Long categoryId;
    private String categoryName;
    private String description;

    public CategoryResponse(){

    }
    public CategoryResponse(Long categoryId, String categoryName, String description) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
