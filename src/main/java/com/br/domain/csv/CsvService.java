package com.br.domain.csv;

import com.br.domain.employee.Employee;

import java.util.List;

public interface CsvService {
    List<Employee> readEmployeesFromCsv(final String absolutePath);
}
