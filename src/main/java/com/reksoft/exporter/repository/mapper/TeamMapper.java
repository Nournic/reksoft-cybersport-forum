package com.reksoft.exporter.repository.mapper;

import com.reksoft.exporter.model.Team;
import com.reksoft.exporter.repository.dto.TeamDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    @Mapping(target = "teamName", source = "name")
    Team map(TeamDto dto);
}
