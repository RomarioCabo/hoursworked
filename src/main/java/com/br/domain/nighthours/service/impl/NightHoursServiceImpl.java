package com.br.domain.nighthours.service.impl;

import com.br.domain.nighthours.service.NightHoursService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class NightHoursServiceImpl implements NightHoursService {

    @Override
    public long calculate(LocalTime start, LocalTime end) {
        // Night period definition
        LocalTime nightStart = LocalTime.of(22, 0);
        LocalTime nightEnd = LocalTime.of(5, 0);

        // Convert start and end times to LocalDateTime
        LocalDateTime startDateTime = LocalDateTime.now().with(start);
        LocalDateTime endDateTime = LocalDateTime.now().with(end);

        // Adjust for end time after midnight
        if (end.isBefore(start)) {
            endDateTime = endDateTime.plusDays(1);
        }

        long nightMinutes = 0;

        // Calculate minutes if start is between 22:00 and 05:00
        if (!start.isBefore(nightStart)) {
            if (end.isBefore(nightEnd)) {
                nightMinutes = Duration.between(startDateTime, endDateTime).toMinutes();
            } else {
                nightMinutes = Duration.between(startDateTime, LocalDateTime.now().with(nightEnd).plusDays(1)).toMinutes();
            }
        }

        // Calculate minutes if start is before 05:00
        if (start.isBefore(nightEnd)) {
            LocalDateTime nightEndDateTime = LocalDateTime.now().with(nightEnd).plusDays(1);
            nightMinutes += Duration.between(startDateTime, nightEndDateTime).toMinutes();
        }

        // Calculate minutes if end is after 22:00
        if (!end.isBefore(nightStart)) {
            LocalDateTime nightStartDateTime = LocalDateTime.now().with(nightStart);
            nightMinutes += Duration.between(nightStartDateTime, endDateTime).toMinutes();
        }

        return nightMinutes;
    }
}