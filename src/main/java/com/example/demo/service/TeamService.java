package com.example.demo.service;

import com.example.demo.dto.TeamDto;
import com.example.demo.entity.TeamEntity;
import com.example.demo.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService (TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamDto> getTeams() {
        List<TeamEntity> teams = teamRepository.findAll();
        return teams.stream()
                .map(team -> TeamDto.builder()
                        .id(team.getId())
                        .name(team.getName())
                        .country(team.getCountry())
                        .league(team.getLeague())
                        .build())
                .collect(Collectors.toList());

    }

    public TeamDto getTeamById(Long id) {
            TeamEntity team = teamRepository.findById(id).orElse(null);
            if (team != null) {
                return TeamDto.builder()
                        .id(team.getId())
                        .name(team.getName())
                        .country(team.getCountry())
                        .league(team.getLeague())
                        .build();
            }
            return null;
    }
}


