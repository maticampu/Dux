package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name="team")
@Getter

public class TeamEntity {
    @Id
    @SequenceGenerator(
            name = "team_sequence",
            sequenceName = "team_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String name;
    private String league;
    private String country;
}
