package com.example.parkinglotcharge;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
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


        Duration duration = parkingSession.getTotalDuration();

        if (isShort(duration)) {
            return 0L;
        }
        //lock of domain knowledge
        // each iteration in the loop
        //calculate daily duration          => parking behavior
        //calculate fee with daily duraiton => charging behavior

        //透過加上假日的領域邏輯，把算帳的領域物件逼出來


        List<DailySession> dailySessions = parkingSession.getDailySessions();

        long totalFee = 0L;

        for (DailySession dailySession : dailySessions) {

            long todayFee = getRegularFee(dailySession.getTodayDuration(),dailySession.getToday());
            totalFee += Math.min(todayFee,150L);
        }
        return totalFee;


    }

    private long getRegularFee(Duration duration, LocalDate today) {
        long periods = BigDecimal.valueOf(duration.toNanos())
                .divide(BigDecimal.valueOf(THIRTY_MINUTES.toNanos()), RoundingMode.UP)
                .longValue();
        int unitPrice = DayOfWeek.SATURDAY.equals(today.getDayOfWeek())
                ?50
                :30;

        long fee = periods * unitPrice;
        return fee;
    }

    private boolean isShort(Duration duration) {
        return duration.compareTo(FIFTEEN_MINUTES) <= 0;
    }


}
