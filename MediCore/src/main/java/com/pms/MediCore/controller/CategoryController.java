package com.pms.MediCore.controller;

import com.pms.MediCore.dto.response.CategoryResponse;
import com.pms.MediCore.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    ResponseEntity<List<CategoryResponse>> getAllCategories(){
        List<CategoryResponse> categoryResponse=categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);
    }
}
