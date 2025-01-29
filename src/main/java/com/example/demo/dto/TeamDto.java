package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder

public class TeamDto {
    private Long id;

    @JsonProperty("nombre")
    private String name;

    @JsonProperty("liga")
    private String league;

    @JsonProperty("pais")
    private String country;
}



