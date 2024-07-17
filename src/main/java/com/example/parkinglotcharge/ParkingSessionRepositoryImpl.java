package com.example.parkinglotcharge;

import java.util.HashMap;
import java.util.Map;

public class ParkingSessionRepositoryImpl implements ParkingSessionRepository {
    private ParkingSession parkingSession;
    private Map<String, ParkingSession> parkingSessions = new HashMap<>();


    @Override
    public void save(ParkingSession parkingSession) {

        this.parkingSessions.put(parkingSession.getPlate(),parkingSession);


    }

    @Override
    public ParkingSession find(String plate) {

        return this.parkingSessions.get(plate);
    }
}
