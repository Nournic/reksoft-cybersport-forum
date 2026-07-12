package com.reksoft.exporter.repository.dto.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.reksoft.exporter.repository.dto.TeamDto;
import com.reksoft.exporter.repository.dto.TournamentDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
public class MatchHistoryViewDto {
    private Integer id;
    private Integer winnerId;
    private Integer loserId;
    private Integer tournamentId;
    private OffsetDateTime date;
    private String winner;
    private String loser;
}
