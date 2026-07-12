package com.reksoft.exporter.controller;

import com.reksoft.exporter.service.report.MatchHistoryCsvReportService;
import com.reksoft.exporter.service.report.PlayerCsvReportService;
import com.reksoft.exporter.service.report.TeamCsvReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.nio.file.Files;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.hamcrest.Matchers.startsWith;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReportController.class)
class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PlayerCsvReportService playerReportService;

    @MockitoBean
    private TeamCsvReportService teamReportService;

    @MockitoBean
    private MatchHistoryCsvReportService matchHistoryReportService;

    @MockitoBean
    private Clock clock;

    @Test
    void shouldDownloadPlayerReport() throws Exception {
        File tempFile = File.createTempFile("player_report_", ".csv");
        tempFile.deleteOnExit();
        Files.write(tempFile.toPath(), "ID,Combined Name,Nickname,Team Name,Country,Fullname\n1,John Doe,JD,TeamA,USA,John 'JD' Doe".getBytes());

        when(clock.instant()).thenReturn(Instant.now());
        when(clock.getZone()).thenReturn(ZoneId.systemDefault());
        when(playerReportService.generateReport(anyString())).thenReturn(tempFile);

        mockMvc.perform(get("/report/player/download"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", startsWith("attachment; filename=player_report_")))
                .andExpect(content().contentType(MediaType.parseMediaType("text/csv")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("John Doe")));
    }

    @Test
    void shouldDownloadTeamReport() throws Exception {
        File tempFile = File.createTempFile("team_report_", ".csv");
        tempFile.deleteOnExit();
        Files.write(tempFile.toPath(), "ID,TeamName,Players\n1,Alpha,JohnDoe".getBytes());

        when(clock.instant()).thenReturn(Instant.now());
        when(clock.getZone()).thenReturn(ZoneId.systemDefault());
        when(teamReportService.generateReport(anyString())).thenReturn(tempFile);

        mockMvc.perform(get("/report/team/download"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", startsWith("attachment; filename=team_report_")))
                .andExpect(content().contentType(MediaType.parseMediaType("text/csv")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Alpha")));
    }

    @Test
    void shouldDownloadMatchHistoryReport() throws Exception {
        File tempFile = File.createTempFile("match_history_report_", ".csv");
        tempFile.deleteOnExit();
        Files.write(tempFile.toPath(), "ID,Date,Winner,Loser\n1,2025-03-24T10:00:00Z,TeamA,TeamB".getBytes());

        when(clock.instant()).thenReturn(Instant.now());
        when(clock.getZone()).thenReturn(ZoneId.systemDefault());
        when(matchHistoryReportService.generateReport(anyString())).thenReturn(tempFile);

        mockMvc.perform(get("/report/match-history/download"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", startsWith("attachment; filename=match_history_report_")))
                .andExpect(content().contentType(MediaType.parseMediaType("text/csv")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("TeamA")));
    }
}