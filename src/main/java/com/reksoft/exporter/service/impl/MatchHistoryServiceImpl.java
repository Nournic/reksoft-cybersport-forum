package com.reksoft.exporter.service.impl;

import com.reksoft.exporter.model.Match;
import com.reksoft.exporter.repository.MatchHistoryApiRepository;
import com.reksoft.exporter.repository.dto.view.MatchHistoryViewDto;
import com.reksoft.exporter.repository.mapper.MatchMapper;
import com.reksoft.exporter.service.MatchHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchHistoryServiceImpl implements MatchHistoryService {

    private final MatchHistoryApiRepository matchHistoryApiRepository;
    private final MatchMapper mapper;

    @Override
    public List<Match> getMatchHistory(){
        List<MatchHistoryViewDto> matchHistoryViewDtos = matchHistoryApiRepository.getMatchHistory();
        return matchHistoryViewDtos.stream().map(mapper::map).toList();
    }
}
