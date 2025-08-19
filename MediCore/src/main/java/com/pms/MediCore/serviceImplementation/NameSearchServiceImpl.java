package com.pms.MediCore.serviceImplementation;

import com.pms.MediCore.dto.request.NameSearchRequest;
import com.pms.MediCore.dto.response.NameSearchResponse;
import com.pms.MediCore.service.NameSearchService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NameSearchServiceImpl implements NameSearchService {


    private final JdbcTemplate jdbcTemplate;

    public NameSearchServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<NameSearchResponse> getAllNames(NameSearchRequest nameSearchRequest){
        String sql = "SELECT * FROM get_full_name_list_by_table(?, ?, ?)";
        return jdbcTemplate.query(
                sql,
                new Object[]{nameSearchRequest.getSchema(), nameSearchRequest.getTableName(), nameSearchRequest.getSearch() == null ? "" : nameSearchRequest.getSearch()},
                (rs, rowNum) -> new NameSearchResponse(
                        rs.getLong("id"),
                        rs.getString("full_name")
                )
        );
    }
}
