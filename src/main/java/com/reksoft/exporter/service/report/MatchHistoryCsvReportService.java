package com.reksoft.exporter.service.report;

import com.reksoft.exporter.model.Match;
import com.reksoft.exporter.service.MatchHistoryService;
import com.reksoft.exporter.service.impl.MatchHistoryServiceImpl;
import com.reksoft.exporter.util.CsvGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@Service
public class MatchHistoryCsvReportService implements ReportService{

    private final MatchHistoryService matchHistoryService;

    @Override
    public File generateReport(String filePath) throws IOException{
        List<Match> matches = matchHistoryService.getMatchHistory();
        String[] header = {"ID", "Date", "Winner", "Loser"};

        return CsvGeneratorUtil.generateCsv(filePath, header, matches, match -> new String[]{
                String.valueOf(match.getId()),
                match.getDate().toString(),
                match.getWinner(),
                match.getLoser()
        });
    }
}
