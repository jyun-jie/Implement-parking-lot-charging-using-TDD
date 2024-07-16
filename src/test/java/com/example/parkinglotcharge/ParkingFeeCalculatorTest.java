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
        given_parking_starts_at("2024-01-02" + "T00:00:00");
        given_parking_ends_at("2024-01-02" + "T02:30:01");

        when_calculate();


        then_should_pay(150L);
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
        given_parking_starts_at("2024-01-02" + "T00:00:00");
        given_parking_ends_at("2024-01-02" + "T00:15:00");

        when_calculate();


        then_should_pay(0L);
    }

    @Test
    void over_15_minute_Not_Free(){
        given_parking_starts_at("2024-01-02" + "T00:00:00");
        given_parking_ends_at("2024-01-02" + "T00:15:01");

        when_calculate();


        then_should_pay(30L);
    }

    @Test
    void over_30_minute_then_pay_60(){
        given_parking_starts_at("2024-01-02" + "T00:00:00");
        given_parking_ends_at("2024-01-02" + "T00:30:01");

        when_calculate();


        then_should_pay(60L);
    }

    @Test
    void over_60_minute_then_pay_90(){
        given_parking_starts_at("2024-01-02" + "T00:00:00");
        given_parking_ends_at("2024-01-02" + "T01:00:01");

        when_calculate();


        then_should_pay(90L);
    }


    @Test
    void two_whole_days(){
        given_parking_starts_at("2024-01-02" + "T00:00:00");
        given_parking_ends_at("2024-01-04" + "T00:00:00");

        when_calculate();


        then_should_pay(300L);
    }

    @Test
    void partial_day_then_whole_days(){
        given_parking_starts_at("2024-01-02" + "T23:50:00");
        given_parking_ends_at("2024-01-04" + "T00:00:00");

        when_calculate();


        then_should_pay(30L+150L);
    }


}
