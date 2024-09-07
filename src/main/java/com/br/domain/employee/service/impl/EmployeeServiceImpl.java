package com.br.domain.employee.service.impl;

import com.br.domain.employee.Employee;
import com.br.domain.employee.service.EmployeeService;
import com.br.domain.nighthours.service.NightHoursService;
import com.br.domain.nighthours.service.impl.NightHoursServiceImpl;

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
                        .quantityHours(nightHoursService.calculate(employee.getEntryTime(), employee.getDepartureTime()))
                        .build())
                .toList();
    }
}
