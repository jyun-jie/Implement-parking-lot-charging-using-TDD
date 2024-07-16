package com.example.parkinglotcharge;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
public final class ParkingSession {
    private final LocalDateTime start;
    private final LocalDateTime end;


}