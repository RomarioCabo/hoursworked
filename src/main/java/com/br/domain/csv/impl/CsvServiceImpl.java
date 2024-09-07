package com.br.domain.csv.impl;

import com.br.domain.csv.CsvService;
import com.br.domain.employee.Employee;
import com.opencsv.*;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CsvServiceImpl implements CsvService {

    private static final char CSV_DELIMITER = ';';
    private static final int HEADER_INDEX = 1;
    private static final String[] CSV_HEADER = {"Nome do Funcionário", "Horário de Entrada", "Horário de Saída",
            "Minutos com 20%"};
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

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
            throw new RuntimeException("Error reading CSV file: " + absolutePath, e);
        }
    }

    @Override
    public void writeEmployeesToCsv(List<Employee> employees, String outputPath) {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outputPath), StandardCharsets.UTF_8);
             CSVWriter csvWriter = createCsvWriter(writer)) {

            writer.write('\ufeff');
            csvWriter.writeNext(CSV_HEADER);
            employees.forEach(employee -> csvWriter.writeNext(mapToCsvRecord(employee)));

        } catch (Exception e) {
            throw new RuntimeException("Error writing to CSV file: " + outputPath, e);
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
            throw new RuntimeException("Error creating CSVReader for file: " + absolutePath, e);
        }
    }

    private CSVWriter createCsvWriter(OutputStreamWriter writer) {
        return new CSVWriter(writer, CSV_DELIMITER, CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
    }

    private boolean isValidRecord(String[] record) {
        return record.length >= 3 && isNotEmpty(record[0]) && isNotEmpty(record[1]) && isNotEmpty(record[2]);
    }

    private boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private Employee mapToEmployee(String[] record) {
        return Employee.builder()
                .name(record[0].trim())
                .entryTime(LocalTime.parse(record[1], TIME_FORMATTER))
                .departureTime(LocalTime.parse(record[2], TIME_FORMATTER))
                .quantityHours(null)
                .build();
    }

    private String[] mapToCsvRecord(Employee employee) {
        return new String[]{
                employee.getName(),
                employee.getEntryTime().format(TIME_FORMATTER),
                employee.getDepartureTime().format(TIME_FORMATTER),
                employee.getQuantityHours() != null ? employee.getQuantityHours().toString() : ""
        };
    }
}
