package com.br.domain.employee.service.impl;

import com.br.domain.employee.Employee;
import com.br.domain.employee.service.EmployeeService;
import com.br.domain.nighthours.service.NightHoursService;
import com.br.domain.nighthours.service.impl.NightHoursServiceImpl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final NightHoursService nightHoursService = new NightHoursServiceImpl();

    @Override
    public List<Employee> mapperHoursWorked(final List<Employee> employees) {
        return employees.stream()
                .map(employee -> Employee.builder()
                        .name(employee.getName())
                        .entryTime(employee.getEntryTime())
                        .departureTime(employee.getDepartureTime())
                        .quantityMinutes(returnMinutes(nightHoursService.calculate(buildTime(employee.getEntryTime()), buildTime(employee.getDepartureTime()))))
                        .quantityHours(returnHours(nightHoursService.calculate(buildTime(employee.getEntryTime()), buildTime(employee.getDepartureTime()))))
                        .build())
                .toList();
    }

    private Long returnMinutes(final long total) {
        return total >= 60 ? null : total;
    }

    private Float returnHours(final long total) {
        return total >= 60 ? (float) (total / 60.0) : null;
    }

    private LocalDateTime buildTime(final LocalTime time) {
        return LocalDateTime.now().with(time).plusDays(checkTime(time));
    }

    private static long checkTime(LocalTime time) {
        LocalTime startTime = LocalTime.of(1, 0);
        LocalTime endTime = LocalTime.of(5, 0);

        if (time.equals(startTime) || (time.isAfter(startTime) && !time.isAfter(endTime))) {
            return 1L;
        } else {
            return 0L;
        }
    }
}
