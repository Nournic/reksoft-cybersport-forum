package com.reksoft.exporter.service.report;

import com.reksoft.exporter.model.Team;
import com.reksoft.exporter.service.TeamService;
import com.reksoft.exporter.util.CsvGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamCsvReportService implements ReportService {
    private final TeamService teamService;

    @Override
    public File generateReport(String filePath) throws IOException {
        List<Team> teams = teamService.getTeams();
        String[] header = {"ID", "TeamName", "Players"};

        return CsvGeneratorUtil.generateCsv(filePath, header, teams, team -> new String[]{
                String.valueOf(team.getId()),
                team.getTeamName(),
                team.getPlayers() != null ? team.getPlayers().stream()
                        .map(x -> x.getName() + x.getSurname())
                        .collect(Collectors.joining(", ")) : ""
        });
    }
}
