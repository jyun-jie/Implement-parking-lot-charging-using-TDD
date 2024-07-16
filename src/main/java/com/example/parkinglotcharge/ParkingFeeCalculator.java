package com.example.parkinglotcharge;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingFeeCalculator {
    //topic : 如何製作一個 能上新聞的 汽車計算機
    //停車場
    //15分鐘免費
    //平日
    // 每日60元 (以半小時收費)
    //當日上限 150 (隔天另計)
    //假日
    //每小時 100元 (以半小時收費)
    //每日上限2400 (隔日另計)
    public long calcualte(LocalDateTime start , LocalDateTime end){

        Duration duration = Duration.between(start,end);

        if (duration.compareTo(Duration.ofMinutes(15L))<=0) {
            return 0L;
        }

        Duration thirtyMinutes = Duration.ofMinutes(30L);

        long periods = BigDecimal.valueOf(duration.toNanos())
                .divide(BigDecimal.valueOf(thirtyMinutes.toNanos()), RoundingMode.UP)
                .longValue();
        long fee = periods * 30;

        return  Math.min(fee,150L);
    }




}
