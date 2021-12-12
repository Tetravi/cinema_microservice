package com.cinema.serviceusers.api.dto;

import com.cinema.serviceusers.repo.model.Type;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public final class User {
    private String name;
    private Integer age;
    private Type type;
}
