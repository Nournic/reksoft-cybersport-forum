package com.reksoft.exporter.repository;

import com.reksoft.exporter.properties.ApiProperties;
import com.reksoft.exporter.repository.dto.MatchHistoryDto;
import com.reksoft.exporter.repository.dto.view.MatchHistoryViewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@RequiredArgsConstructor
@Repository
public class MatchHistoryApiRepository {

    private final RestTemplate restTemplate;
    private final ApiProperties apiProperties;

    public List<MatchHistoryViewDto> getMatchHistory(){
        ResponseEntity<List<MatchHistoryViewDto>> response = restTemplate.exchange(
                apiProperties.getBaseUrl() + "api/MatchHistory/1", // 1 - потому что ручка выдает результат только при единице
                GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

}

