package com.reksoft.exporter.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Match {
    private Integer id;
    private Integer winnerId;
    private Integer loserId;
    private Integer tournamentId;
    private OffsetDateTime date;
    private String winner;
    private String loser;
}
