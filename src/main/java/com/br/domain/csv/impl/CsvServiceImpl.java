package com.br.domain.csv.impl;

import com.br.domain.csv.CsvService;
import com.br.domain.employee.Employee;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.time.LocalTime;
import java.util.List;

public class CsvServiceImpl implements CsvService {

    private static final char CSV_DELIMITER = ';';
    private static final int HEADER_INDEX = 1;

    @Override
    public List<Employee> readEmployeesFromCsv(String absolutePath) {
        try (CSVReader csvReader = createCsvReader(absolutePath)) {
            List<String[]> records = csvReader.readAll();

            return records.stream()
                    .skip(HEADER_INDEX)
                    .filter(this::isValidRecord)
                    .map(this::mapToEmployee)
                    .toList();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading CSV file", e);
        }
    }

    private CSVReader createCsvReader(String absolutePath) {
        try {
            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(CSV_DELIMITER)
                    .build();

            return new CSVReaderBuilder(new FileReader(absolutePath))
                    .withCSVParser(parser)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading CSV file", e);
        }
    }

    private boolean isValidRecord(String[] record) {
        return record.length >= 3 && !record[0].isEmpty() && !record[1].isEmpty() && !record[2].isEmpty();
    }

    private Employee mapToEmployee(String[] record) {
        return Employee.builder()
                .name(record[0])
                .entryTime(LocalTime.parse(record[1]))
                .departureTime(LocalTime.parse(record[2]))
                .quantityHours(null)
                .build();
    }
}
