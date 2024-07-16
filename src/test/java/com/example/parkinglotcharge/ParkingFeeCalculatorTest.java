package com.example.parkinglotcharge;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


public class ParkingFeeCalculatorTest {

    @Test
    void _15_minute_Free(){
        ParkingFeeCalculator cal = new ParkingFeeCalculator();

        LocalDateTime start = LocalDateTime.of(2024,1,1,0,0,0);
        LocalDateTime end = LocalDateTime.of(2024,1,1,0,0,0);

        long actual = cal.calcualte(start,end);

        Assertions.assertThat( actual ).isEqualTo(0L);
    }

    @Test
    void over_15_minute_Not_Free(){
        ParkingFeeCalculator cal = new ParkingFeeCalculator();

        long actual = cal.calcualte(
                LocalDateTime.parse("2024-01-01T00:00:00"),
                LocalDateTime.parse("2024-01-01T00:15:00")
        );


        Assertions.assertThat( actual ).isEqualTo(30L);
    }

    @Test
    void over_30_minute_then_pay_60(){
        ParkingFeeCalculator cal = new ParkingFeeCalculator();

        long actual = cal.calcualte(
                LocalDateTime.parse("2024-01-01T00:00:00"),
                LocalDateTime.parse("2024-01-01T00:30:00")
        );


        Assertions.assertThat( actual ).isEqualTo(60L);
    }

    @Test
    void over_60_minute_then_pay_90(){
        ParkingFeeCalculator cal = new ParkingFeeCalculator();

        long actual = cal.calcualte(
                LocalDateTime.parse("2024-01-01T00:00:00"),
                LocalDateTime.parse("2024-01-01T01:00:00")
        );


        Assertions.assertThat( actual ).isEqualTo(90L);
    }

    @Test
    void over_150_minute_then_pay_150(){
        ParkingFeeCalculator cal = new ParkingFeeCalculator();

        long actual = cal.calcualte(
                LocalDateTime.parse("2024-01-01T00:00:00"),
                LocalDateTime.parse("2024-01-01T02:30:00")
        );


        Assertions.assertThat( actual ).isEqualTo(150L);
    }
}
