package com.pms.MediCore.serviceImplementation;

import com.pms.MediCore.dto.response.CategoryResponse;
import com.pms.MediCore.entity.Category;
import com.pms.MediCore.repository.CategoryRepository;
import com.pms.MediCore.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categoryList=categoryRepository.findAll();
        return categoryList.stream()
                .map(cat->modelMapper.map(cat, CategoryResponse.class))
                .collect(Collectors.toList());

    }
}
