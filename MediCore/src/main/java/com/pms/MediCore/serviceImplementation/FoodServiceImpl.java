package com.pms.MediCore.serviceImplementation;

import com.pms.MediCore.dto.request.FoodDetailsRequest;
import com.pms.MediCore.dto.request.FoodRequest;
import com.pms.MediCore.dto.request.FoodSearchRequest;
import com.pms.MediCore.dto.response.FoodDetailsResponse;
import com.pms.MediCore.dto.response.FoodResponse;
import com.pms.MediCore.dto.response.FoodSearchResponse;
import com.pms.MediCore.dto.response.PatientFoodResponse;
import com.pms.MediCore.entity.Food;
import com.pms.MediCore.entity.PatientFood;
import com.pms.MediCore.repository.FoodDetailsRepository;
import com.pms.MediCore.repository.FoodRepository;
import com.pms.MediCore.repository.FoodSearchRepository;
import com.pms.MediCore.repository.PatientFoodRepository;
import com.pms.MediCore.service.FoodService;
import com.pms.MediCore.service.PatientFoodService;
import com.pms.MediCore.view.FoodDetails;
import com.pms.MediCore.view.FoodSearch;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    private final ModelMapper modelMapper;
    private final FoodRepository foodRepository;
    private final FoodSearchRepository foodSearchRepository;
    private final FoodDetailsRepository foodDetailsRepository;
    private final PatientFoodRepository patientFoodRepository;

    public FoodServiceImpl(FoodRepository foodRepository, ModelMapper modelMapper, FoodSearchRepository foodSearchRepository, FoodDetailsRepository foodDetailsRepository, PatientFoodRepository patientFoodRepository) {
        this.foodRepository=foodRepository;
        this.modelMapper = modelMapper;
        this.foodSearchRepository = foodSearchRepository;
        this.foodDetailsRepository=foodDetailsRepository;
        this.patientFoodRepository=patientFoodRepository;
    }

    @Override
    public FoodRequest addFood(FoodRequest foodRequest){
        Food food= modelMapper.map(foodRequest, Food.class);
        food.setCreatedDate(new Date());
        food.setUpdatedDate(new Date());
        Food savedFood= foodRepository.save(food);
        return modelMapper.map(savedFood, FoodRequest.class);
    }

    @Override
    public FoodResponse updateFood(Long id, FoodRequest foodRequest){
        Food food1=foodRepository.findById(foodRequest.getId()).orElseThrow(()-> new RuntimeException("No Food Found"));
        food1.setUpdatedDate(new Date());
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(foodRequest, food1);
        Food updatedFood= foodRepository.save(food1);
        return modelMapper.map(updatedFood, FoodResponse.class);
    }

    @Override
    public List<FoodSearchResponse> searchFood(FoodSearchRequest foodSearchRequest){
        try{
            List<FoodSearch> foodSearch= foodSearchRepository.searchPatientFood(
                    foodSearchRequest.getPatientRegNo() == null ? "" : foodSearchRequest.getPatientRegNo(),
                    foodSearchRequest.getRoomNo() == null? -2L : foodSearchRequest.getRoomNo(),
                    foodSearchRequest.getMealType()== null? -2L : foodSearchRequest.getMealType(),
                    foodSearchRequest.getMealDate()==null? null : foodSearchRequest.getMealDate(),
                    foodSearchRequest.getActive()== null? -2L : foodSearchRequest.getActive()
            );
            return foodSearch.stream().map(p->modelMapper.map(p, FoodSearchResponse.class)).collect(Collectors.toList());
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public FoodDetailsResponse getFoodDetails(FoodDetailsRequest foodDetailsRequest){
        FoodDetails food= foodDetailsRepository.findPatientFoodDetails(
                foodDetailsRequest.getId()==null? -2L : foodDetailsRequest.getId(),
                foodDetailsRequest.getPatientId()==null? -2L : foodDetailsRequest.getPatientId()
        );
        return modelMapper.map(food, FoodDetailsResponse.class);
    }

    @Override
    public List<PatientFoodResponse> getAllPatientFood(Long mealType){
        List<PatientFood> patientFood= patientFoodRepository.findAllByMealTypeAndActive(mealType, 1L);
        return patientFood.stream().map(f->modelMapper.map(f,PatientFoodResponse.class)).collect(Collectors.toList());
    }

}
