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

    @Override
    public void writeEmployeesToCsv(final List<Employee> employees, final String outputPath) {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outputPath), StandardCharsets.UTF_8);
             CSVWriter csvWriter = new CSVWriter(writer, CSV_DELIMITER, CSVWriter.NO_QUOTE_CHARACTER,
                     CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {

            // Escreve o BOM para UTF-8 (marca de ordem de byte)
            writer.write('\ufeff');

            // Escreve o cabeçalho
            String[] header = {"Nome do Funcionário", "Horário de Entrada", "Horário de Saída",
                    "Quantidade de minutos 20%)"};
            csvWriter.writeNext(header);

            // Escreve os dados dos empregados
            for (Employee employee : employees) {
                String[] record = {
                        employee.getName(),
                        employee.getEntryTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                        employee.getDepartureTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                        employee.getQuantityHours() != null ? employee.getQuantityHours().toString() : ""
                };
                csvWriter.writeNext(record);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error writing to CSV file", e);
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
                .name(record[0].trim())
                .entryTime(LocalTime.parse(record[1]))
                .departureTime(LocalTime.parse(record[2]))
                .quantityHours(null)
                .build();
    }
}
