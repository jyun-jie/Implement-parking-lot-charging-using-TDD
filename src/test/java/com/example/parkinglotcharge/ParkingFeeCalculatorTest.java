package com.example.parkinglotcharge;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


public class ParkingFeeCalculatorTest {
    @Test
    void over_150_minute_then_pay_150(){
        runTest("2024-01-01T00:00:00","2024-01-01T02:30:00",150L);
    }

    private static void runTest(String startText,String endText ,long expected) {
        ParkingFeeCalculator cal = new ParkingFeeCalculator();

        long actual = cal.calcualte(
                LocalDateTime.parse(startText),
                LocalDateTime.parse(endText)
        );


        Assertions.assertThat( actual ).isEqualTo(expected);
    }

    @Test
    void _15_minute_Free(){
        runTest("2024-01-01T00:00:00","2024-01-01T00:14:59",0L);
    }

    @Test
    void over_15_minute_Not_Free(){
        runTest("2024-01-01T00:00:00","2024-01-01T00:15:00",30L);
    }

    @Test
    void over_30_minute_then_pay_60(){
        runTest("2024-01-01T00:00:00","2024-01-01T00:30:00",60L);
    }

    @Test
    void over_60_minute_then_pay_90(){
        runTest("2024-01-01T00:00:00","2024-01-01T01:00:00",90L);
    }



}
