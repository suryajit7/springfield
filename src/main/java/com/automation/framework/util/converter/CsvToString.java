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

public class CsvToString {


    public  <T> T csvToString(Path filepath, Class<T> type) throws IOException {

        CsvSchema csvSchema = CsvSchema.builder()
                .addColumn("id")
                .addColumn("employeeId")
                .addColumn("username")
                .addColumn("deptId")
                .addColumn("employeeName")
                .addColumn("userRole")
                .addColumn("status")
                .build().withHeader();

        ObjectReader reader = new CsvMapper().readerFor(Employee.class).with(csvSchema);
        List<Employee> employeeList = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(filepath.toAbsolutePath().toString())) {
            try (MappingIterator<Employee> iterator = reader.readValues(inputStream)) {
               iterator.forEachRemaining(employeeList::add);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return employeeList;
    }








}
