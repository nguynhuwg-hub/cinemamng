package com.example.demo.module.cinema.service;

import com.example.demo.module.cinema.dto.CinemaResponse;
import com.example.demo.module.cinema.dto.CreateCinemaRequest;
import com.example.demo.module.cinema.entity.Cinema;
import com.example.demo.module.cinema.entity.City;
import com.example.demo.module.cinema.repository.CinemaRepository;
import com.example.demo.module.cinema.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CinemaService {

    private final CinemaRepository cinemaRepository;
    private final CityRepository cityRepository; // Bạn tạo thêm CityRepository tương tự CinemaRepository

    public List<CinemaResponse> getCinemasByCity(Long cityId) {
        return cinemaRepository.findByCityId(cityId).stream()
                .map(cinema -> CinemaResponse.builder()
                        .id(cinema.getId())
                        .name(cinema.getName())
                        .address(cinema.getAddress())
                        .cityName(cinema.getCity().getName())
                        .build())
                .collect(Collectors.toList());
    }

    public CinemaResponse createCinema(CreateCinemaRequest request) {
        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new RuntimeException("City not found"));

        Cinema cinema = new Cinema();
        cinema.setName(request.getName());
        cinema.setAddress(request.getAddress());
        cinema.setCity(city);

        Cinema saved = cinemaRepository.save(cinema);

        return CinemaResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .address(saved.getAddress())
                .cityName(saved.getCity().getName())
                .build();
    }
}
