package com.example.parkinglotcharge;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PriceBook {
    private Duration THIRTY_MINUTES = Duration.ofMinutes(30L);
    private static Set<LocalDate> nationalHoliday = new HashSet<>();

    public PriceBook() {
        nationalHoliday.add(LocalDate.of(2024,1,1));
    }

    static boolean isHoliday(LocalDate today) {

        return nationalHoliday.contains(today) ||
                List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(today.getDayOfWeek());
    }

    long getDailyFee(DailySession dailySession) {
        long todayFee = getRegularFee(dailySession);

        long dailyLimit = isHoliday(dailySession.getToday())
                ?2400
                :150;

        return Math.min(todayFee,dailyLimit);
    }

    private long getRegularFee(DailySession dailySession) {
        long periods = BigDecimal.valueOf(dailySession.getTodayDuration().toNanos())
                .divide(BigDecimal.valueOf(THIRTY_MINUTES.toNanos()), RoundingMode.UP)
                .longValue();


        return periods * (isHoliday(dailySession.getToday())
                        ?50
                        :30);

    }
}