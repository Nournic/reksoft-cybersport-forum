package com.reksoft.exporter.repository.mapper;

import com.reksoft.exporter.model.Player;
import com.reksoft.exporter.repository.dto.PlayerViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    @Mapping(target = "nickname", source = "nickName")
    @Mapping(target = "fullName", source = "." , qualifiedByName = "fullname-builder")
    Player map(PlayerViewDto dto);

    @Named("fullname-builder")
    static String buildFullname(PlayerViewDto dto){
        if (dto == null) {
            return null;
        }

        StringBuilder fullName = new StringBuilder();
        fullName.append(dto.getSurname());
        fullName.append(" “");
        fullName.append(dto.getNickName());
        fullName.append("” ");
        fullName.append(dto.getName());
        return fullName.toString();
    }

}
