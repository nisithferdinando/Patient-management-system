package com.pms.MediCore.service;

import com.pms.MediCore.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse>getAllCategories();
}
