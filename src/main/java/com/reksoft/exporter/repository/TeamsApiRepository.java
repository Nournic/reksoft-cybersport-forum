package com.reksoft.exporter.repository;

import com.reksoft.exporter.properties.ApiProperties;
import com.reksoft.exporter.repository.dto.TeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@RequiredArgsConstructor
@Repository
public class TeamsApiRepository {

    private final RestTemplate restTemplate;
    private final ApiProperties apiProperties;

    public List<TeamDto> getTeams() {
        ResponseEntity<List<TeamDto>> response = restTemplate.exchange(
                apiProperties.getBaseUrl() + "/api/Teams",
                GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }
}
