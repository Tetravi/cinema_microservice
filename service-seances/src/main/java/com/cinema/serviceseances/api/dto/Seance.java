package com.cinema.serviceseances.api.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public final class Seance {
    private Long film;
    private Long hall;
    private String time;
}
