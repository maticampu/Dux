package com.example.demo.controller;

import com.example.demo.dto.TeamDto;
import com.example.demo.exception.TeamNotCreatedException;
import com.example.demo.exception.TeamNotFoundException;
import com.example.demo.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Devuelve la lista de todos los equipos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    public List<TeamDto> getTeams() throws TeamNotFoundException {
        return teamService.getTeams();
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Devuelve la información del equipo correspondiente al ID proporcionado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    public TeamDto getTeamById(@PathVariable Long id) throws TeamNotFoundException {
        return teamService.getTeamById(id);
    }

    @GetMapping(value = "/buscar")
    @Operation(summary = "Devuelve la lista de equipos cuyos nombres contienen el valor proporcionado en el parámetro de búsqueda")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    public List<TeamDto> getTeamByName(@RequestParam("nombre") String name) throws TeamNotFoundException {
        return teamService.getTeamByName(name);
    }

    @PostMapping
    @Operation(summary = "Registra el equipo enviado")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public TeamDto saveTeam(@RequestBody TeamDto newTeam) throws TeamNotCreatedException {
        return teamService.createTeam(newTeam);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza los datos del equipo deseado, siempre que esten todos los datos)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)

    })
    public ResponseEntity<TeamDto> updateTeam(@PathVariable Long id, @RequestBody TeamDto updatedTeam) throws TeamNotCreatedException {
        TeamDto updated = teamService.updateTeam(id, updatedTeam);
        return ResponseEntity.status(HttpStatus.CREATED).body(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina ele equipo indicado")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    private ResponseEntity<?> deleteTeam(@PathVariable Long id) throws TeamNotFoundException {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
}
