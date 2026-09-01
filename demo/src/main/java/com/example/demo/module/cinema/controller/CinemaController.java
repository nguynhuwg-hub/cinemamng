package com.example.demo.module.cinema.controller;

import com.example.demo.module.cinema.dto.CinemaResponse;
import com.example.demo.module.cinema.dto.CreateCinemaRequest;
import com.example.demo.module.cinema.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cinemas")
@RequiredArgsConstructor
public class CinemaController {

    private final CinemaService cinemaService;

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<CinemaResponse>> getCinemasByCity(@PathVariable Long cityId) {
        return ResponseEntity.ok(cinemaService.getCinemasByCity(cityId));
    }

    @PostMapping
    public ResponseEntity<CinemaResponse> createCinema(@RequestBody CreateCinemaRequest request) {
        return ResponseEntity.ok(cinemaService.createCinema(request));
    }
}
