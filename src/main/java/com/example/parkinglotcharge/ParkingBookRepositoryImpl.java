package com.example.parkinglotcharge;

public class ParkingBookRepositoryImpl implements ParkingBookRepository {
    private ParkingSession parkingSession;

    @Override
    public void save(ParkingSession parkingSession) {
        this.parkingSession = parkingSession;

    }

    @Override
    public ParkingSession find() {
        return parkingSession;
    }
}
