package com.reksoft.exporter.service.report;

import com.opencsv.CSVReader;
import com.reksoft.exporter.model.Team;
import com.reksoft.exporter.repository.dto.PlayerDto;
import com.reksoft.exporter.service.TeamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamCsvReportServiceTest {

    @Mock
    private TeamService teamService;

    @InjectMocks
    private TeamCsvReportService teamCsvReportService;

    @Test
    void shouldGenerateReport() throws Exception {
        PlayerDto player1 = new PlayerDto();
        player1.setName("John");
        player1.setSurname("Doe");

        PlayerDto player2 = new PlayerDto();
        player2.setName("Jane");
        player2.setSurname("Smith");

        Team team = new Team();
        team.setId(1);
        team.setTeamName("Alpha");
        team.setPlayers(List.of(player1, player2));

        when(teamService.getTeams()).thenReturn(List.of(team));

        String tempDir = System.getProperty("java.io.tmpdir");
        String filePath = tempDir + File.separator + "team_report_test.csv";

        File result = teamCsvReportService.generateReport(filePath);

        assertThat(result).exists();

        try (CSVReader reader = new CSVReader(new FileReader(result))) {
            List<String[]> lines = reader.readAll();
            assertThat(lines).hasSize(2); // header + 1 row

            assertThat(lines.get(0)).containsExactly("ID", "TeamName", "Players");
            assertThat(lines.get(1)).containsExactly("1", "Alpha", "JohnDoe, JaneSmith");
        } finally {
            result.delete();
        }
    }
}