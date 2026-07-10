package com.reksoft.exporter.service;

import com.reksoft.exporter.model.Team;
import com.reksoft.exporter.repository.TeamsApiRepository;
import com.reksoft.exporter.repository.dto.TeamDto;
import com.reksoft.exporter.repository.mapper.TeamMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService{
    private final TeamsApiRepository teamsApiRepository;
    private final TeamMapper teamMapper;

    @Override
    public List<Team> getTeams() {
        List<TeamDto> listTeamDto = teamsApiRepository.getTeams();
        return listTeamDto.stream().map(teamMapper::map).toList();
    }
}
