package com.example.demo.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class TeamNotCreatedDto {
        @JsonProperty("mensaje")
        private String message;

        @JsonProperty("codigo")
        private Long code;
}
