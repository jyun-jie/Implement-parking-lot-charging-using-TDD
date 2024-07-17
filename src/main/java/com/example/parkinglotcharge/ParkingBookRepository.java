package com.example.parkinglotcharge;

public interface ParkingBookRepository {
    void save(ParkingSession parkingSession1);

    ParkingSession find();
}
