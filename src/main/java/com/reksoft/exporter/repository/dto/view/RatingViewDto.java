package com.reksoft.exporter.repository.dto.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.reksoft.exporter.repository.dto.TeamDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
public class RatingViewDto {
    private Integer id;
    private Integer score;
    private OffsetDateTime atMoment;
    private String teamName;
    private Integer teamId;
}
