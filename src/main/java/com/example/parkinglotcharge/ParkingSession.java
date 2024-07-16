package com.example.parkinglotcharge;

import java.time.LocalDateTime;

public record ParkingSession(LocalDateTime start, LocalDateTime end) {
}