package com.example.parkinglotcharge;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public final class ParkingSession {
    private final String plate;
    private final LocalDateTime start;
    private LocalDateTime end;


    List<DailySession> getDailySessions() {
        List<Duration> dailyDurations = new ArrayList<>();
        List<DailySession> dailySessions = new ArrayList<>();

        LocalDate today = getStart().toLocalDate();
        LocalDateTime todayStart = today.atStartOfDay();
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

            dailySessions.add(
                    new DailySession(today,todayDuration)
            );

//            long todayFee = getRegularFee(todayDuration);
//            totalFee += Math.min(todayFee,150L);


            todayStart = tomorrowStart;
        }
        return dailySessions;
    }

    Duration getTotalDuration() {
        return Duration.between(getStart(), getEnd());
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}