package com.example.demo.config;

import com.example.demo.exception.TeamNotCreatedDto;
import com.example.demo.exception.TeamNotCreatedException;
import com.example.demo.exception.TeamNotFoundDto;
import com.example.demo.exception.TeamNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {TeamNotFoundException.class})
    protected ResponseEntity<TeamNotFoundDto> handleTeamNotFound(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TeamNotFoundDto.builder().message("Equipo no encontrado").code(404L).build());
    }

    @ExceptionHandler(value = {TeamNotCreatedException.class})
    protected ResponseEntity<TeamNotCreatedDto> handleTeamNotCreated(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(TeamNotCreatedDto.builder().message("La solicitud es invalida").code(400L).build());
    }
}
