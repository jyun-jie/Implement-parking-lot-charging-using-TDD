package com.example.parkinglotcharge;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

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
    public long calcualte(LocalDateTime start , LocalDateTime end){

        //誇天
        //一個durationn 切多段 ， 一天切一段
        //國定假日
        //今天是哪天
        //一個durationn 切多段 ， 一天切一段


        Duration duration = Duration.between(start,end);

        if (isShort(duration)) {
            return 0L;
        }

        LocalDateTime todayStart = start.toLocalDate().atStartOfDay();
        long totalFee = 0L;
        while(todayStart.isBefore(end)){
            if(start.isAfter(todayStart) &&
                    !end.isBefore(todayStart.plusDays(1L))){
                LocalDateTime todaySessionStart = start;
                LocalDateTime todaySessionEnd = todayStart.plusDays(1L);

                Duration todayDuration = Duration.between(todaySessionStart, todaySessionEnd);


                long todayFee = getRegularFee(todayDuration);

                totalFee += Math.min(todayFee,150L);
            }else if( !start.isAfter(todayStart) &&
                       end.isBefore(todayStart.plusDays(1L)) ) {
                LocalDateTime todaySessionStart = todayStart;
                LocalDateTime todaySessionEnd = end;

                Duration todayDuration = Duration.between(todaySessionStart, todaySessionEnd);


                long todayFee = getRegularFee(todayDuration);

                totalFee += Math.min(todayFee,150L);
            } else if (start.isAfter(todayStart) &&
                    end.isBefore(todayStart.plusDays(1L))) {
                LocalDateTime todaySessionStart = start;
                LocalDateTime todaySessionEnd = end;

                Duration todayDuration = Duration.between(todaySessionStart, todaySessionEnd);


                long todayFee = getRegularFee(todayDuration);

                totalFee += Math.min(todayFee,150L);
            } else{
                totalFee +=150L;
            }

            todayStart = todayStart.plusDays(1L);
        }
        return totalFee;


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
