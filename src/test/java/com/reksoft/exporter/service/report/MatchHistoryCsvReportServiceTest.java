package com.reksoft.exporter.service.report;

import com.opencsv.CSVReader;
import com.reksoft.exporter.model.Match;
import com.reksoft.exporter.service.MatchHistoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileReader;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MatchHistoryCsvReportServiceTest {

    @Mock
    private MatchHistoryService matchHistoryService;

    @InjectMocks
    private MatchHistoryCsvReportService matchHistoryCsvReportService;

    @Test
    void shouldGenerateReport() throws Exception {
        Match match = new Match();
        match.setId(1);
        match.setDate(OffsetDateTime.parse("2025-03-24T10:00:00Z"));
        match.setWinner("TeamA");
        match.setLoser("TeamB");

        when(matchHistoryService.getMatchHistory()).thenReturn(List.of(match));

        String tempDir = System.getProperty("java.io.tmpdir");
        String filePath = tempDir + File.separator + "match_report_test.csv";

        File result = matchHistoryCsvReportService.generateReport(filePath);

        assertThat(result).exists();

        try (CSVReader reader = new CSVReader(new FileReader(result))) {
            List<String[]> lines = reader.readAll();
            assertThat(lines).hasSize(2);

            assertThat(lines.get(0)).containsExactly("ID", "Date", "Winner", "Loser");


            String dateStr = lines.get(1)[1];
            OffsetDateTime parsedDate = OffsetDateTime.parse(dateStr, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            assertThat(parsedDate).isEqualTo(OffsetDateTime.parse("2025-03-24T10:00:00Z"));
            assertThat(lines.get(1)[0]).isEqualTo("1");
            assertThat(lines.get(1)[2]).isEqualTo("TeamA");
            assertThat(lines.get(1)[3]).isEqualTo("TeamB");
        } finally {
            result.delete();
        }
    }
}