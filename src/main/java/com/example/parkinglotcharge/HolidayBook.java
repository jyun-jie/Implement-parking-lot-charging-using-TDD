package com.example.parkinglotcharge;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HolidayBook {

    private static Set<LocalDate> nationalHoliday = new HashSet<>();

    public HolidayBook() {
        nationalHoliday.add(LocalDate.of(2024,1,1));
    }

    static boolean isHoliday(LocalDate today) {

        return nationalHoliday.contains(today) ||
                List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(today.getDayOfWeek());
    }
}