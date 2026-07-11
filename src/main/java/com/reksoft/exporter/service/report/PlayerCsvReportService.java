package com.reksoft.exporter.service.report;

import com.reksoft.exporter.model.Player;
import com.reksoft.exporter.service.PlayerService;
import com.reksoft.exporter.util.CsvGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PlayerCsvReportService implements ReportService {

    private final PlayerService playerService;

    @Override
    public File generateReport(String filePath) throws IOException {
        List<Player> players = playerService.getPlayers();
        String[] header = {"ID", "Combined Name", "Nickname", "Team Name", "Country", "Fullname"};

        return CsvGeneratorUtil.generateCsv(filePath, header, players, player -> new String[]{
                String.valueOf(player.getId()),
                player.getCombinedName(),
                player.getNickname(),
                player.getTeamName(),
                player.getCountry() != null ? player.getCountry() : "",
                player.getFullName()
        });
    }
}
