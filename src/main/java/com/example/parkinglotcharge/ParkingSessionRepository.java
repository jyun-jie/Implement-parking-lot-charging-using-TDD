package com.example.parkinglotcharge;

public interface ParkingSessionRepository {
    void save(ParkingSession parkingSession1);

    ParkingSession find();
}
