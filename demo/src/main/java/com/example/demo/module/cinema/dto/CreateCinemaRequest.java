package com.example.demo.module.cinema.dto;

import lombok.Data;

@Data
public class CreateCinemaRequest {
    private String name;
    private String address;
    private Long cityId;
}