package com.reksoft.exporter.repository.mapper;

import com.reksoft.exporter.model.Player;
import com.reksoft.exporter.repository.dto.PlayerViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    @Mapping(target = "combinedName", source = "combinedName")
    @Mapping(target = "nickname", source = "nickName")
    @Mapping(target = "country", source = "country")
    Player map(PlayerViewDto dto);

}
