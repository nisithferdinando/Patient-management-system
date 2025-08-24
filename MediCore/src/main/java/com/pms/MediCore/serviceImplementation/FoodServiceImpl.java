package com.pms.MediCore.serviceImplementation;

import com.pms.MediCore.dto.request.FoodRequest;
import com.pms.MediCore.entity.Food;
import com.pms.MediCore.repository.FoodRepository;
import com.pms.MediCore.service.FoodService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final ModelMapper modelMapper;

    public FoodServiceImpl(FoodRepository foodRepository, ModelMapper modelMapper) {
        this.foodRepository = foodRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public FoodRequest addFood(FoodRequest foodRequest) {
        Food food= modelMapper.map(foodRequest, Food.class);
        food.setCreatedDate(new Date());
        food.setUpdatedDate(new Date());
        Food newFood=foodRepository.save(food);
        return modelMapper.map(newFood, FoodRequest.class);

    }
}
