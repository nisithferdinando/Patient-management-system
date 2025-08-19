package com.pms.MediCore.service;

import com.pms.MediCore.dto.response.KeyValueResponse;

import java.util.List;

public interface KeyValueService {

    List<KeyValueResponse> getValueByKey(String value);
}
