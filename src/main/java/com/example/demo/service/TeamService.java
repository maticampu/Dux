package com.example.demo.service;

import com.example.demo.dto.TeamDto;
import com.example.demo.entity.TeamEntity;
import com.example.demo.exception.TeamNotCreatedException;
import com.example.demo.exception.TeamNotFoundException;
import com.example.demo.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService (TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamDto> getTeams() throws TeamNotFoundException {
        List<TeamEntity> teams = teamRepository.findAll();
        if (teams.isEmpty()) {
            throw new TeamNotFoundException();
        }
        return toDto(teams);

    }

    public TeamDto getTeamById(Long id) throws TeamNotFoundException {
            Optional<TeamEntity> team = teamRepository.findById(id);
            if (team.isPresent()) {
                return toDto(team.get());
            }
            throw new TeamNotFoundException();
    }

    public List<TeamDto> getTeamByName(String name) throws TeamNotFoundException {
        List<TeamEntity> teams = teamRepository.findByName(name);
        if (teams.isEmpty()) {
            throw new TeamNotFoundException();
        }
        return toDto(teams);
    }

    public TeamDto createTeam(TeamDto teamDto) throws TeamNotCreatedException {
        if (teamRepository.existsByName(teamDto.getName())) {
            throw new TeamNotCreatedException();
        }

        TeamEntity savedTeam = teamRepository.save(toEntity(teamDto));
        return toDto(savedTeam);
    }


    public TeamDto updateTeam(Long id, TeamDto updatedTeam) throws TeamNotCreatedException {
        if (!teamRepository.existsById(id)) {
            throw new TeamNotCreatedException();
        }
        TeamEntity savedTeam = teamRepository.save(toEntity(updatedTeam, id));
        return toDto(savedTeam);
//        Optional<TeamEntity> team = teamRepository.findById(id);
//        if (team.isPresent()) {
//            TeamEntity savedTeam = teamRepository.save(TeamEntity.builder()
//                    .id(id)
//                    .name(updatedTeam.getName())
//                    .country(updatedTeam.getCountry())
//                    .league(updatedTeam.getLeague())
//                    .build());
//            return getTeamById(savedTeam.getId());
//        }
//        return null;
    }

    public void deleteTeam(Long id) throws TeamNotFoundException {
        TeamEntity team = teamRepository.findById(id).orElseThrow(TeamNotFoundException::new);
        teamRepository.delete(team);
    }

    private TeamEntity toEntity(TeamDto teamDto) {
        return TeamEntity.builder()
                .id(teamDto.getId())
                .name(teamDto.getName())
                .country(teamDto.getCountry())
                .league(teamDto.getLeague())
                .build();
    }

    private TeamEntity toEntity(TeamDto teamDto, Long teamId) {
        return TeamEntity.builder()
                .id(teamId)
                .name(teamDto.getName())
                .country(teamDto.getCountry())
                .league(teamDto.getLeague())
                .build();
    }

    private TeamDto toDto(TeamEntity teamEntity) {
        return TeamDto.builder()
                .id(teamEntity.getId())
                .name(teamEntity.getName())
                .country(teamEntity.getCountry())
                .league(teamEntity.getLeague())
                .build();
    }


    private List<TeamDto> toDto(List<TeamEntity> teamEntities) {
        return teamEntities.stream()
                .map(this::toDto)
                .toList();
    }

}


