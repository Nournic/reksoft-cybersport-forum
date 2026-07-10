package com.reksoft.exporter.service;

import com.opencsv.CSVWriter;
import com.reksoft.exporter.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PlayerCsvReportService implements ReportService{

    private final PlayerService playerService;

    public File generateReport(String filePath) throws IOException {
        List<Player> players = playerService.getPlayers();

        File file = new File(filePath);
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            String[] header = {"ID", "Combined Name", "Nickname", "Team Name", "Country", "Fullname"};
            writer.writeNext(header);

            for (Player player : players) {
                String[] line = {
                        String.valueOf(player.getId()),
                        player.getCombinedName(),
                        player.getNickname(),
                        player.getTeamName(),
                        player.getCountry() != null ?  player.getCountry() : "",
                        player.getFullName()
                };
                writer.writeNext(line);
            }
        }

        return file;
    }
}
