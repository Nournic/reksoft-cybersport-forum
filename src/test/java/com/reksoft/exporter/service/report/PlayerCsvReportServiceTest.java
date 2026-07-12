package com.reksoft.exporter.service.report;

import com.opencsv.CSVReader;
import com.reksoft.exporter.model.Player;
import com.reksoft.exporter.service.PlayerService;
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
class PlayerCsvReportServiceTest {

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerCsvReportService playerCsvReportService;

    @Test
    void shouldGenerateReport() throws Exception {
        Player player1 = new Player();
        player1.setId(1);
        player1.setCombinedName("John Doe");
        player1.setNickname("JD");
        player1.setTeamName("TeamA");
        player1.setCountry("USA");
        player1.setFullName("John 'JD' Doe");

        Player player2 = new Player();
        player2.setId(2);
        player2.setCombinedName("Jane Smith");
        player2.setNickname("JS");
        player2.setTeamName("TeamB");
        player2.setCountry("Canada");
        player2.setFullName("Jane 'JS' Smith");

        when(playerService.getPlayers()).thenReturn(List.of(player1, player2));

        String tempDir = System.getProperty("java.io.tmpdir");
        String filePath = tempDir + File.separator + "player_report_test.csv";

        File result = playerCsvReportService.generateReport(filePath);

        assertThat(result).exists();

        try (CSVReader reader = new CSVReader(new FileReader(result))) {
            List<String[]> lines = reader.readAll();
            assertThat(lines).hasSize(3); // header + 2 rows


            assertThat(lines.get(0)).containsExactly(
                    "ID", "Combined Name", "Nickname", "Team Name", "Country", "Fullname"
            );

            assertThat(lines.get(1)).containsExactly(
                    "1", "John Doe", "JD", "TeamA", "USA", "John 'JD' Doe"
            );

            assertThat(lines.get(2)).containsExactly(
                    "2", "Jane Smith", "JS", "TeamB", "Canada", "Jane 'JS' Smith"
            );
        } finally {
            result.delete();
        }
    }
}