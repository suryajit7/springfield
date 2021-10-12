package com.automation.framework.util.converter;

import com.automation.framework.data.entity.app.ems.Employee;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;

public class CsvToJson {


    public List<Employee> csvToJson(Path filepath) throws IOException {

        Builder csvSchemaBuilder = CsvSchema.builder();

        Arrays.stream(Employee.class.getDeclaredFields())
                        .forEachOrdered(sas -> {
                            csvSchemaBuilder.addColumn(sas.getName());
                        });
        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();

        ObjectReader reader = new CsvMapper().readerFor(Employee.class).with(csvSchema);
        List<Employee> financialDataExports = new ArrayList<>();
        /*try (FileInputStream inputStream = new FileInputStream(new File(filepath.toAbsolutePath().toString()))) {
            try (MappingIterator<Employee> iterator = reader.readValues(inputStream)) {
                iterator.forEachRemaining(financialDataExports::add);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        CsvMapper mapper = new CsvMapper();
        FileInputStream inputStream = new FileInputStream(filepath.toAbsolutePath().toFile());
        MappingIterator<List<Employee>> it = mapper
                .readerForListOf(Employee.class)
                .with(CsvParser.Feature.WRAP_AS_ARRAY)
                .readValues(inputStream.toString());
        return financialDataExports;
    }

    public String getFieldDataType(Field field) {
        return field.getType().getTypeName().substring(field.getType().getTypeName().lastIndexOf(".")+1);
    }

 /*   public ColumnType mapCsvColumnTypeToFieldType(Field field) {
        String fieldType = getFieldDataType(field);



    }*/





}
