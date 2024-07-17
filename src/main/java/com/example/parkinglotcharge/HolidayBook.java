package com.example.parkinglotcharge;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class HolidayBook {
    static boolean isHoliday(LocalDate today) {
        return List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(today.getDayOfWeek());
    }
}