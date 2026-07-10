package com.reksoft.exporter.service;

import com.opencsv.CSVWriter;
import com.reksoft.exporter.model.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamCsvReportService implements ReportService{
    private final TeamService teamService;

    public File generateReport(String filePath) throws IOException {
        List<Team> teams = teamService.getTeams();

        File file = new File(filePath);
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            String[] header = {"ID", "TeamName", "Players"};
            writer.writeNext(header);

            for (Team team : teams) {
                String[] line = {
                        String.valueOf(team.getId()),
                        team.getTeamName(),
                        team.getPlayers() != null ? team.getPlayers().stream()
                                .map((x)->x.getName() + x.getSurname())
                                .collect(Collectors.joining(", ")) : ""
                };
                writer.writeNext(line);
            }
        }

        return file;
    }
}
