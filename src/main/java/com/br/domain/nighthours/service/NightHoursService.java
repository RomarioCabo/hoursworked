package com.br.domain.nighthours.service;

import java.time.LocalTime;

public interface NightHoursService {
    long calculate(LocalTime start, LocalTime end);
}
