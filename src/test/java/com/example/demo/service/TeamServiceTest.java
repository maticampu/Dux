package com.example.demo.service;

import com.example.demo.dto.TeamDto;
import com.example.demo.entity.TeamEntity;
import com.example.demo.exception.TeamNotCreatedException;
import com.example.demo.exception.TeamNotFoundException;
import com.example.demo.repository.TeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;




    @Test
    public void CreateTeamTest() throws TeamNotCreatedException {
        //Given
        TeamDto teamGiven = TeamDto.builder().name("team").country("country").league("league").build();
        TeamDto teamExpected = TeamDto.builder().id(1L).name("team").country("country").league("league").build();
        Mockito.when(teamRepository.save(Mockito.any(TeamEntity.class))).thenReturn(toEntity(teamExpected));

        //When
        TeamDto teamCreated = teamService.createTeam(teamGiven);

        //Then
        Mockito.verify(teamRepository).save(Mockito.any(TeamEntity.class));
        Assertions.assertEquals(teamExpected.getId(), teamCreated.getId());
        Assertions.assertEquals(teamExpected.getName(), teamCreated.getName());
        Assertions.assertEquals(teamExpected.getCountry(), teamCreated.getCountry());
        Assertions.assertEquals(teamExpected.getLeague(), teamCreated.getLeague());
    }

    @Test
    public void getTeamByIdTestSuccessed() throws TeamNotFoundException {
        //Given
        Long teamId = 1L;
        TeamDto teamExpected = TeamDto.builder().id(1L).name("team").country("country").league("league").build();
        Mockito.when(teamRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(toEntity(teamExpected)));

        //When
        TeamDto teamFounded = teamService.getTeamById(teamId);

        //THen
        Assertions.assertNotNull(teamFounded);
        Assertions.assertEquals(teamExpected.getId(), teamFounded.getId());
        Assertions.assertEquals(teamExpected.getName(), teamFounded.getName());
        Assertions.assertEquals(teamExpected.getCountry(), teamFounded.getCountry());
        Assertions.assertEquals(teamExpected.getLeague(), teamFounded.getLeague());

    }

    @Test
    public void getTeamByIdTestFailed() throws TeamNotFoundException {
        //Given
        Long teamId = 1L;
        Mockito.when(teamRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());

        //When, Then
        Assertions.assertThrows(TeamNotFoundException.class, () -> {
            teamService.getTeamById(teamId);
        });
    }

    private TeamEntity toEntity(TeamDto teamDto) {
        return TeamEntity.builder()
                .id(teamDto.getId())
                .name(teamDto.getName())
                .country(teamDto.getCountry())
                .league(teamDto.getLeague())
                .build();
    }


}
