package com.cinema.servicehalls.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public final class Hall {
    private Integer lines;
    private Integer seats;
    private String screenType;
}
