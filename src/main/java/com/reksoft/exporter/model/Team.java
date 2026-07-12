package com.reksoft.exporter.model;

import com.reksoft.exporter.repository.dto.PlayerDto;
import lombok.Data;

import java.util.List;

@Data
public class Team {
    private Integer id;
    private String teamName;
    private List<PlayerDto> players;
}
