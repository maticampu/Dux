package com.example.demo.controller;

import com.example.demo.dto.TeamDto;
import com.example.demo.exception.TeamNotCreatedException;
import com.example.demo.exception.TeamNotFoundException;
import com.example.demo.service.TeamService;
import org.springframework.http.ResponseEntity;
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
    public TeamDto getTeamById(@PathVariable Long id) throws TeamNotFoundException {
        if (teamService.getTeamById(id) == null) {
            throw new TeamNotFoundException();
        }
        return teamService.getTeamById(id);
    }

    @GetMapping(value = "/buscar")
    public List<TeamDto> getTeamByName(@RequestParam("nombre") String name) throws TeamNotFoundException {
        return teamService.getTeamByName(name);
    }

    @PostMapping
    public TeamDto saveTeam(@RequestBody TeamDto newTeam) throws TeamNotCreatedException {
        return teamService.createTeam(newTeam);
    }

    @PutMapping("/{id}")
    public TeamDto updateTeam(@PathVariable Long id, @RequestBody TeamDto updatedTeam) {
        return teamService.updateTeam(id, updatedTeam);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteTeam(@PathVariable Long id) throws TeamNotFoundException {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
}
