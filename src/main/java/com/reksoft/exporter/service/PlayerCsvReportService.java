package com.reksoft.exporter.service;

import com.opencsv.CSVWriter;
import com.reksoft.exporter.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PlayerCsvReportService implements ReportService{

    private final PlayerService playerService;
    private static final Map<Integer, String> COUNTRY_MAP = Map.ofEntries(
            Map.entry(0, "Неизвестно"),
            Map.entry(1, "Болгария"),
            Map.entry(2, "Канада"),
            Map.entry(3, "Китай"),
            Map.entry(4, "Дания"),
            Map.entry(5, "Эстония"),
            Map.entry(6, "Финляндия"),
            Map.entry(7, "Франция"),
            Map.entry(8, "Германия"),
            Map.entry(9, "Греция"),
            Map.entry(10, "Израиль"),
            Map.entry(11, "Иордания"),
            Map.entry(12, "Ливан"),
            Map.entry(13, "Северная Македония"),
            Map.entry(14, "Малайзия"),
            Map.entry(15, "Молдова"),
            Map.entry(16, "Норвегия"),
            Map.entry(17, "Пакистан"),
            Map.entry(18, "Филиппины"),
            Map.entry(19, "Польша"),
            Map.entry(20, "Россия"),
            Map.entry(21, "Сингапур"),
            Map.entry(22, "Словакия"),
            Map.entry(23, "Швеция"),
            Map.entry(24, "Таиланд"),
            Map.entry(25, "США"),
            Map.entry(26, "Словакия")
    );

    public File generateReport(String filePath) throws IOException {
        List<Player> players = playerService.getPlayers();

        File file = new File(filePath);
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            String[] header = {"ID", "Combined Name", "Nickname", "Team Name", "Country", "Fullname"};
            writer.writeNext(header);

            for (Player player : players) {
                String combinedName = player.getCombinedName();
                String nickName = player.getNickname();
                String[] names = combinedName.split(" ", 2);
                StringBuilder fullName = new StringBuilder();
                fullName.append(names[0]);
                fullName.append(" '");
                fullName.append(nickName);
                fullName.append("' ");
                fullName.append(names[1]);
                String[] line = {
                        String.valueOf(player.getId()),
                        combinedName,
                        nickName,
                        player.getTeamName(),
                        player.getCountry() != null ? COUNTRY_MAP.getOrDefault(player.getCountry(), "") : "",
                        fullName.toString()
                };
                writer.writeNext(line);
            }
        }

        return file;
    }
}
