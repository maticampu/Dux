package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder

public class TeamDto {
    private Long id;
    private String name;
    private String league;
    private String country;
}



