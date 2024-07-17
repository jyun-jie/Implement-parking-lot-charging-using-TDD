package com.example.parkinglotcharge;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


public class CalculateParkingFeeServiceTest {
    private String plate;
    private static long actual;
    private static CalculateParkingFeeService cal;
    private static ParkingSessionRepository parkingSessionRepository = new ParkingSessionRepositoryImpl();


    @BeforeEach
    void setUp() {
        cal = new CalculateParkingFeeService(
                new PriceBookRepositoryImpl(new PriceBook()),
                parkingSessionRepository);
    }

    @Test
    void over_150_minute_then_pay_150(){
        given_parking_starts_at("ABC-1234","2024-01-02" + "T00:00:00");
        given_car_drives_out_at("ABC-1234", "2024-01-02" + "T02:30:01");

        when_calculate("ABC-1234");


        then_should_pay(150L);
    }

    private static void then_should_pay(long expected) {
        Assertions.assertThat( actual ).isEqualTo(expected);
    }

    private static void given_car_drives_out_at(String plate, String endText) {

        ParkingSession parkingSession = parkingSessionRepository.find(plate);
        parkingSession.setEnd(LocalDateTime.parse(endText));
        parkingSessionRepository.save(parkingSession);
    }

    private static void given_parking_starts_at(String plate, String startText) {
        parkingSessionRepository.save(
                new ParkingSession(plate , LocalDateTime.parse(startText),null));
    }

    private static void when_calculate(String plate) {
        actual = cal.calcualte(plate
        );
    }

    @Test
    void _15_minute_Free(){
        given_parking_starts_at("ABC-1234", "2024-01-02" + "T00:00:00");
        given_car_drives_out_at("ABC-1234", "2024-01-02" + "T00:15:00");

        when_calculate("ABC-1234");


        then_should_pay(0L);
    }

    @Test
    void over_15_minute_Not_Free(){
        given_parking_starts_at("ABC-1234", "2024-01-02" + "T00:00:00");
        given_car_drives_out_at("ABC-1234", "2024-01-02" + "T00:15:01");

        when_calculate("ABC-1234");


        then_should_pay(30L);
    }

    @Test
    void over_30_minute_then_pay_60(){
        given_parking_starts_at("ABC-1234", "2024-01-02" + "T00:01:00");
        given_car_drives_out_at("ABC-1234", "2024-01-02" + "T00:31:01");

        when_calculate("ABC-1234");


        then_should_pay(60L);
    }

    @Test
    void over_60_minute_then_pay_90(){
        given_parking_starts_at("ABC-1234", "2024-01-02" + "T00:00:00");
        given_car_drives_out_at("ABC-1234", "2024-01-02" + "T01:00:01");

        when_calculate("ABC-1234");


        then_should_pay(90L);
    }


    @Test
    void two_whole_days(){
        given_parking_starts_at("ABC-1234", "2024-01-02" + "T00:00:00");
        given_car_drives_out_at("ABC-1234", "2024-01-04" + "T00:00:00");

        when_calculate("ABC-1234");


        then_should_pay(300L);
    }

    @Test
    void partial_day_then_whole_days(){
        given_parking_starts_at("ABC-1234", "2024-01-02" + "T23:50:00");
        given_car_drives_out_at("ABC-1234", "2024-01-04" + "T00:00:00");

        when_calculate("ABC-1234");


        then_should_pay(30L+150L);
    }

    @Test
    void whole_days_then_partial_day(){
        given_parking_starts_at("ABC-1234", "2024-01-02" + "T00:00:00");
        given_car_drives_out_at("ABC-1234", "2024-01-03" + "T00:10:00");

        when_calculate("ABC-1234");


        then_should_pay(150L+30L);
    }

    @Test
    void saturday_pay_50_per_half_hour(){
        given_parking_starts_at("ABC-1234", "2024-01-06" + "T00:00:00");
        given_car_drives_out_at("ABC-1234", "2024-01-06" + "T00:15:01");

        when_calculate("ABC-1234");


        then_should_pay(50L);
    }

    @Test
    void sunday_pay_50_per_half_hour(){
        given_parking_starts_at("ABC-1234", "2024-01-07" + "T00:00:00");
        given_car_drives_out_at("ABC-1234", "2024-01-07" + "T00:15:01");

        when_calculate("ABC-1234");


        then_should_pay(50L);
    }
    @Test
    void saturday_daily_limit_is_2400(){
        given_parking_starts_at("ABC-1234", "2024-01-06" + "T00:00:00");
        given_car_drives_out_at("ABC-1234", "2024-01-07" + "T00:00:00");

        when_calculate("ABC-1234");


        then_should_pay(2400L);
    }

    @Test
    void national_holiday_pay_50_per_half_hour(){
        given_parking_starts_at("ABC-1234", "2024-01-01" + "T00:00:00");
        given_car_drives_out_at("ABC-1234", "2024-01-01" + "T00:15:01");

        when_calculate("ABC-1234");


        then_should_pay(50L);
    }


}
