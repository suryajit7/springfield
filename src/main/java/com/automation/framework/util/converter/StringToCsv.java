package com.automation.framework.util.converter;

import com.automation.framework.data.entity.app.ems.Employee;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class StringToCsv {


    public List<Employee> csvToJson(Path filepath) throws IOException {

        CsvSchema csvSchema = CsvSchema.builder()
                .addColumn("id")
                .addColumn("employeeId")
                .addColumn("deptId")
                .addColumn("username")
                .addColumn("employeeName")
                .addColumn("userRole")
                .addColumn("status")
                .build().withHeader();

        ObjectReader reader = new CsvMapper().readerFor(Employee.class).with(csvSchema);
        List<Employee> financialDataExports = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(filepath.toAbsolutePath().toString())) {
            try (MappingIterator<Employee> iterator = reader.readValues(inputStream)) {
                iterator.forEachRemaining(financialDataExports::add);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return financialDataExports;
    }








}
