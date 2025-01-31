package com.example.demo.controller;

import com.example.demo.dto.TeamDto;
import com.example.demo.exception.TeamNotCreatedException;
import com.example.demo.exception.TeamNotFoundException;
import com.example.demo.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipos")
@Tag(name = "Equipos", description = "Visualizar, crear, actualizar o eliminar equipos")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    @Operation(
            description = "Devuelve la lista de todos los equipos"
    )
    public List<TeamDto> getTeams() throws TeamNotFoundException {
        return teamService.getTeams();
    }

    @GetMapping(value = "/{id}")
    @Operation(
            description = "Devuelve la información del equipo correspondiente al ID proporcionado"
    )
    public TeamDto getTeamById(@PathVariable Long id) throws TeamNotFoundException {
        return teamService.getTeamById(id);
    }

    @GetMapping(value = "/buscar")
    @Operation(
            description = "Devuelve la lista de equipos cuyos nombres contienen el valor proporcionado en el parámetro de búsqueda"
    )
    public List<TeamDto> getTeamByName(@RequestParam("nombre") String name) throws TeamNotFoundException {
        return teamService.getTeamByName(name);
    }

    @PostMapping
    @Operation(
            description = "Registra el equipo enviado"
    )
    public TeamDto saveTeam(@RequestBody TeamDto newTeam) throws TeamNotCreatedException {
        return teamService.createTeam(newTeam);
    }

    @PutMapping("/{id}")
    @Operation(
            description = "Actualiza los datos del equipo deseado, siempre que esten todos los datos)"
    )
    public ResponseEntity<TeamDto> updateTeam(@PathVariable Long id, @RequestBody TeamDto updatedTeam) throws TeamNotCreatedException {
        TeamDto updated = teamService.updateTeam(id, updatedTeam);
        return ResponseEntity.status(HttpStatus.CREATED).body(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(
            description = "Elimina ele equipo indicado"
    )
    private ResponseEntity<?> deleteTeam(@PathVariable Long id) throws TeamNotFoundException {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
}
