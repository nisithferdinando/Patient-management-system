package com.pms.MediCore.serviceImplementation;

import com.pms.MediCore.dto.response.KeyValueResponse;
import com.pms.MediCore.entity.KeyValue;
import com.pms.MediCore.repository.KeyValueRepository;
import com.pms.MediCore.service.KeyValueService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeyValueServiceImpl implements KeyValueService {

    private final KeyValueRepository keyValueRepository;
    private final ModelMapper modelMapper;


    public KeyValueServiceImpl(KeyValueRepository keyValueRepository,ModelMapper modelMapper) {
        this.keyValueRepository = keyValueRepository;
        this.modelMapper = modelMapper;
    }
    @Override
     public List<KeyValueResponse> getValueByKey(String value){
        List<KeyValue> keyValue=keyValueRepository.findByKeyValue(value);
        return keyValue.stream().filter(key->key.getActive()!= null && key.getActive()==1L)
                .map(key->modelMapper.map(key, KeyValueResponse.class))
                .collect(Collectors.toList());
    }
}
