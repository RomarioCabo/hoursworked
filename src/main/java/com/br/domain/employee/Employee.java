package com.br.domain.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private String name;
    private LocalTime entryTime;
    private LocalTime departureTime;
    private Long quantityMinutes;
    private Float quantityHours;
}
