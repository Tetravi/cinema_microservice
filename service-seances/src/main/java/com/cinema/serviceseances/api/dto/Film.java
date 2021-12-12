package com.cinema.serviceseances.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public final class Film {
    private String title;
    private Integer duration;
    private String description;
    private String startDate;
    private String finishDate;
}
