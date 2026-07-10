package com.reksoft.exporter.repository.mapper;

import com.reksoft.exporter.model.Player;
import com.reksoft.exporter.repository.dto.PlayerViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    @Mapping(target = "combinedName", source = "combinedName")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "nickname", source = "nickName")
    @Mapping(target = "country", source = "country")
    @Mapping(target = "fullName", expression = "java(buildFullname(dto.getNickName(), dto.getName(), dto.getSurname()))")
    Player map(PlayerViewDto dto);

    default String buildFullname(String nickname, String name, String surname){
        StringBuilder fullName = new StringBuilder();
        fullName.append(surname);
        fullName.append(" '");
        fullName.append(nickname);
        fullName.append("' ");
        fullName.append(name);
        return fullName.toString();
    }

}
