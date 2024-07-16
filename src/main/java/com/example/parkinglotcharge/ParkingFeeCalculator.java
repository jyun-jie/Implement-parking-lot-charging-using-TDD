package com.example.parkinglotcharge;

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
        long minBetween = ChronoUnit.MINUTES.between(start,end);

        if(minBetween < 15){
            return 0L;
        }

        long regularFee =   getRegularFee(minBetween);

        return Math.min(regularFee,150L);

    }

    private static long getRegularFee(long minBetween) {
        long periods = minBetween /30;
        return  (periods+1) * 30;
    }



}
