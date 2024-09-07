package com.br.domain.csv.impl;

import com.br.domain.csv.CsvService;
import com.br.domain.employee.Employee;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CsvServiceImpl implements CsvService {

    @Override
    public List<Employee> readEmployeesFromCsv(String absolutePath) {
        List<Employee> employees = new ArrayList<>();

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(';')
                .build();

        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(absolutePath))
                .withCSVParser(parser)
                .build()) {

            List<String[]> records = csvReader.readAll();

            for (int i = 1; i < records.size(); i++) {
                String[] record = records.get(i);

                if (!record[0].isEmpty() && !record[1].isEmpty() && !record[2].isEmpty()) {
                    Employee employee = Employee.builder()
                            .name(record[0])
                            .entryTime(LocalTime.parse(record[1]))
                            .departureTime(LocalTime.parse(record[2]))
                            .quantityHours(null)
                            .build();

                    employees.add(employee);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return employees;
    }
}
