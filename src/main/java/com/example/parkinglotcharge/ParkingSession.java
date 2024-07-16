package com.example.parkinglotcharge;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public final class ParkingSession {
    private final LocalDateTime start;
    private final LocalDateTime end;


    List<Duration> getDailyDurations() {
        List<Duration> dailyDurations = new ArrayList<>();
        LocalDateTime todayStart = getStart().toLocalDate().atStartOfDay();
        while(todayStart.isBefore(getEnd())){

            LocalDateTime tomorrowStart = todayStart.plusDays(1L);

            LocalDateTime todaySessionStart = getStart().isAfter(todayStart)
                    ? getStart()
                    : todayStart;
            LocalDateTime todaySessionEnd = getEnd().isBefore(tomorrowStart)
                    ? getEnd()
                    : tomorrowStart;

            Duration todayDuration = Duration.between(todaySessionStart, todaySessionEnd);
            dailyDurations.add(todayDuration);

//            long todayFee = getRegularFee(todayDuration);
//            totalFee += Math.min(todayFee,150L);


            todayStart = tomorrowStart;
        }
        return dailyDurations;
    }
}