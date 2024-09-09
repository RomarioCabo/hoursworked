package com.br.domain.nighthours.service;

import java.time.LocalDateTime;

public interface NightHoursService {
    long calculate(final LocalDateTime start, final LocalDateTime end);
}
