package com.reksoft.exporter.repository.dto;

import com.reksoft.exporter.repository.dto.view.MatchHistoryViewDto;
import com.reksoft.exporter.repository.dto.view.RatingViewDto;
import com.reksoft.exporter.repository.dto.view.TournamentParticipantInfoViewDto;
import lombok.Data;

import java.util.List;

@Data
public class TeamDto {
    private Integer id;
    private String name;
    private List<PlayerViewDto> players;
    private List<TournamentParticipantInfoViewDto> teamTournamentResults;
    private List<RatingViewDto> teamRatings;
    private List<MatchHistoryViewDto> matchesWon;
    private List<MatchHistoryViewDto> matchesLost;
}
