package com.pms.MediCore.service;

import com.pms.MediCore.dto.request.NameSearchRequest;
import com.pms.MediCore.dto.response.NameSearchResponse;

import java.util.List;

public interface NameSearchService {

    List<NameSearchResponse> getAllNames(NameSearchRequest nameSearchRequest);
}
