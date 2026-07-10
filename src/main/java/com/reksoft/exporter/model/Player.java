package com.reksoft.exporter.model;

import lombok.Data;

@Data
public class Player {
    private Integer id;
    private String surname;
    private String name;
    private String combinedName;
    private String nickname;
    private String country;
    private String teamName;
    private String fullName;
}
