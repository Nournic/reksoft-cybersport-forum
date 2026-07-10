package com.reksoft.exporter.model;

import lombok.Data;

@Data
public class Player {
    private Integer id;
    private String combinedName;
    private String nickname;
    private Integer country;
    private String teamName;
}
