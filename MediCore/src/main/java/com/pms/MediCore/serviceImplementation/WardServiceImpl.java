package com.pms.MediCore.serviceImplementation;

import com.pms.MediCore.dto.response.WardResponse;
import com.pms.MediCore.entity.Ward;
import com.pms.MediCore.repository.WardRepository;
import com.pms.MediCore.service.WardService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WardServiceImpl implements WardService {

    private final WardRepository wardRepository;
    private final ModelMapper modelMapper;

    public WardServiceImpl(WardRepository wardRepository, ModelMapper modelMapper) {
        this.wardRepository = wardRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<WardResponse> getAllWards(){
        List<Ward> ward=wardRepository.findAllByActiveOrderByWardIdAsc(1L);
        return ward.stream().map(w-> modelMapper.map(w, WardResponse.class)).collect(Collectors.toList());
    }
}
