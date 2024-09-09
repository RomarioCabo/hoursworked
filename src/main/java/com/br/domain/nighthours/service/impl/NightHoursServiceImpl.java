package com.br.domain.nighthours.service.impl;

import com.br.domain.nighthours.service.NightHoursService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class NightHoursServiceImpl implements NightHoursService {

    private final static LocalDateTime TEN_HOURS = LocalDateTime.now().with(LocalTime.of(22, 0));
    private final static LocalDateTime MIDNIGHT = LocalDateTime.now().with(LocalTime.of(23, 59, 59));
    private final static LocalDateTime MIDNIGHT_ZERO = LocalDateTime.now().with(LocalTime.of(0, 0));

    @Override
    public long calculate(final LocalDateTime start, final LocalDateTime end) {

        if (start.isAfter(TEN_HOURS) && end.isAfter(TEN_HOURS)) {
            return Duration.between(start, end).toMinutes();
        }

        if (start.isBefore(TEN_HOURS) && end.isBefore(MIDNIGHT)) {
            LocalDateTime aux_end = end.equals(MIDNIGHT_ZERO) ? MIDNIGHT : end;
            int aux_plus = end.equals(MIDNIGHT_ZERO) ? 1 : 0;

            return Duration.between(TEN_HOURS, aux_end).toMinutes() + aux_plus;
        }

        if (start.isBefore(TEN_HOURS) && end.isAfter(MIDNIGHT)) {
            return Duration.between(TEN_HOURS, end).toMinutes();
        }

        if (start.isBefore(TEN_HOURS) && end.isBefore(TEN_HOURS)) {
            return 0;
        }

        return 0L;
    }
}