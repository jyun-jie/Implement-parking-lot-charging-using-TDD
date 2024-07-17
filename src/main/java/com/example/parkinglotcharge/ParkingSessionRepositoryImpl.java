package com.example.parkinglotcharge;

public class ParkingSessionRepositoryImpl implements ParkingSessionRepository {
    private ParkingSession parkingSession;

    @Override
    public void save(ParkingSession parkingSession) {
        this.parkingSession = parkingSession;

    }

    @Override
    public ParkingSession find(String plate) {
        return parkingSession;
    }
}
