package com.reksoft.exporter.repository.dto.view;

import lombok.Data;

@Data
public class TournamentParticipantInfoViewDto {
    private Integer id;
    private Integer standing;
    private Integer place;
    private Integer teamId;
    private String teamName;
}
