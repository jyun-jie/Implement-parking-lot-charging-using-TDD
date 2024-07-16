package com.example.parkinglotcharge;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingFeeCalculator {

    private Duration THIRTY_MINUTES = Duration.ofMinutes(30L);
    private Duration FIFTEEN_MINUTES = Duration.ofMinutes(15L);


    //topic : 如何製作一個 汽車計算機
    //停車場
    //15分鐘免費
    //平日
    // 每日60元 (以半小時收費)
    //當日上限 150 (隔天另計)
    //假日
    //每小時 100元 (以半小時收費)
    //每日上限2400 (隔日另計)
    public long calcualte(ParkingSession parkingSession){

        //誇天
        //一個durationn 切多段 ， 一天切一段
        //國定假日
        //今天是哪天
        //一個durationn 切多段 ， 一天切一段


        Duration duration = Duration.between(parkingSession.start(), parkingSession.end());

        if (isShort(duration)) {
            return 0L;
        }
        //lock of domain knowledge
        // each iteration in the loop
        //calculate daily duration          => parking behavior
        //calculate fee with daily duraiton => charging behavior


        List<Duration> dailyDurations = getDailyDurations(parkingSession.start(), parkingSession.end());
        long totalFee = 0L;

        for (Duration dailyDuration : dailyDurations) {

            long todayFee = getRegularFee(dailyDuration);
            totalFee += Math.min(todayFee,150L);
        }
        return totalFee;


    }

    private static List<Duration> getDailyDurations(LocalDateTime start, LocalDateTime end) {
        List<Duration> dailyDurations = new ArrayList<>();
        LocalDateTime todayStart = start.toLocalDate().atStartOfDay();
        while(todayStart.isBefore(end)){

            LocalDateTime tomorrowStart = todayStart.plusDays(1L);

            LocalDateTime todaySessionStart = start.isAfter(todayStart)
                    ? start
                    : todayStart;
            LocalDateTime todaySessionEnd = end.isBefore(tomorrowStart)
                    ? end
                    : tomorrowStart;

            Duration todayDuration = Duration.between(todaySessionStart, todaySessionEnd);
            dailyDurations.add(todayDuration);

//            long todayFee = getRegularFee(todayDuration);
//            totalFee += Math.min(todayFee,150L);


            todayStart = tomorrowStart;
        }
        return dailyDurations;
    }

    private long getRegularFee(Duration duration) {
        long periods = BigDecimal.valueOf(duration.toNanos())
                .divide(BigDecimal.valueOf(THIRTY_MINUTES.toNanos()), RoundingMode.UP)
                .longValue();
        long fee = periods * 30;
        return fee;
    }

    private boolean isShort(Duration duration) {
        return duration.compareTo(FIFTEEN_MINUTES) <= 0;
    }


}
