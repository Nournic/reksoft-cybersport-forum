package com.reksoft.exporter.repository.mapper;

import com.reksoft.exporter.model.Match;
import com.reksoft.exporter.repository.dto.view.MatchHistoryViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MatchMapper {

    Match map(MatchHistoryViewDto dto);

}
