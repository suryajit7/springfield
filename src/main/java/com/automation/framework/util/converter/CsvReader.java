package com.automation.framework.util.converter;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public <T> List<T> readCSV(Path filepath, Class<T> type) throws IOException {

        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();

        ObjectReader reader = new CsvMapper().readerFor(type).with(csvSchema);
        List<T> genericList = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(filepath.toAbsolutePath().toString())) {
            try (MappingIterator<T> iterator = reader.readValues(inputStream)) {
                iterator.forEachRemaining(genericList::add);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return genericList;
    }

}
