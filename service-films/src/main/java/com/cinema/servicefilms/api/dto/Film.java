package com.cinema.servicefilms.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Film {
    private String title;
    private Integer duration;
    private String description;
    private String startDate;
    private String finishDate;
}
