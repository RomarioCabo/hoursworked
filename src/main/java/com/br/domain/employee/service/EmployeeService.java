package com.br.domain.employee.service;

import com.br.domain.employee.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> mapperHoursWorked(final List<Employee> employees);
}
