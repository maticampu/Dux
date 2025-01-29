package com.example.demo.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class TeamNotFoundDto {

    @JsonProperty("mensaje")
    private String message;

    @JsonProperty("codigo")
    private Long code;
}
