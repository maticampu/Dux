package com.example.demo.controller;

import com.example.demo.dto.TeamDto;
import com.example.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipos")

public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<TeamDto> getTeams() {
        return teamService.getTeams();
    }

    @GetMapping(value = "/{id}")
    public TeamDto getTeamById(@PathVariable Long id) {
        if (teamService.getTeamById(id) == null){
            return TeamDto.builder().name("NO HAY EQUIPO CON ESE NUMERO").build();
        }
        return teamService.getTeamById(id);
    }
}
