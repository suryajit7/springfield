package com.automation.framework.util.converter;

import com.automation.framework.data.entity.app.ems.Employee;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;

public class CsvToJson {


    public List<Employee> csvToJson(Path filepath) throws IOException {
        CsvMapper mapper = new CsvMapper();
        Builder csvSchemaBuilder = CsvSchema.builder();

/*        Arrays.stream(Employee.class.getDeclaredFields())
                .forEachOrdered(sas -> {
                    csvSchemaBuilder.addColumn(sas.getName());
                });*/

        //CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();

        CsvSchema csvSchema = CsvSchema.builder()
                .addColumn("id")
                .addColumn("employeeId")
                .addColumn("deptId")
                .addColumn("username")
                .addColumn("employeeName")
                .addColumn("userRole")
                .addColumn("status")
                .build().withHeader();

        FileInputStream inputStream = new FileInputStream(filepath.toAbsolutePath().toString());

        MappingIterator<Employee> it = mapper
                .readerForListOf(Employee.class)
                .with(csvSchema)
                .readValues(inputStream.toString());

        return it.readAll();

/*        ObjectReader objReader = new CsvMapper().readerFor(Employee.class).with(csvSchema);
        List<Employee> employees = new ArrayList<>();

        try (FileInputStream inputStream = new FileInputStream(new File(filepath.toAbsolutePath().toString()))) {
            try (MappingIterator<Employee> iterator = objReader.readValues(inputStream)) {
                iterator.forEachRemaining(employees::add);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

/*            Reader newReader = new FileReader(filepath.toAbsolutePath().toFile());

            MappingIterator<List<Employee>> it = mapper
                .readerForListOf(Employee.class)
                .with(csvSchema)
                .readValues(newReader);*/

                /*try (FileInputStream inputStream = new FileInputStream(new File(filepath.toAbsolutePath().toString()))) {
            try (MappingIterator<Employee> iterator = reader.readValues(inputStream)) {
                iterator.forEachRemaining(financialDataExports::add);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        //return employees;
    }

/*    public String getFieldDataType(Field field) {
        return field.getType().getTypeName().substring(field.getType().getTypeName().lastIndexOf(".")+1);
    }*/

 /*   public ColumnType mapCsvColumnTypeToFieldType(Field field) {
        String fieldType = getFieldDataType(field);



    }*/

 }
