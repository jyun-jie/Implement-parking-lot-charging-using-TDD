package com.example.parkinglotcharge;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


public class ParkingFeeCalculatorTest {

    private static LocalDateTime start;
    private static LocalDateTime end;
    private static long actual;
    private static ParkingFeeCalculator cal;


    @BeforeEach
    void setUp() {
        cal = new ParkingFeeCalculator();
    }

    @Test
    void over_150_minute_then_pay_150(){
        runTest("2024-01-01T00:00:00","2024-01-01T02:30:00",150L);
    }

    private static void runTest(String startText, String endText , long expected) {
        given_parking_starts_at(startText);
        given_parking_ends_at(endText);

        when_calculate();


        then_should_pay(expected);
    }

    private static void then_should_pay(long expected) {
        Assertions.assertThat( actual ).isEqualTo(expected);
    }

    private static void given_parking_ends_at(String endText) {
        end = LocalDateTime.parse(endText);
    }

    private static void given_parking_starts_at(String startText) {
        start = LocalDateTime.parse(startText);
    }

    private static void when_calculate() {
        actual = cal.calcualte(
                start,
                end
        );
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
