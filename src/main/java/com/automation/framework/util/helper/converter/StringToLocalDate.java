package com.automation.framework.util.helper.converter;

import com.creditdatamw.zerocell.converter.Converter;

import java.time.LocalDate;

public class StringToLocalDate implements Converter<LocalDate> {


    //TODO: Need to add more date formats
    @Override
    public LocalDate convert(String value, String columnName, int row) {
        return LocalDate.parse(value);
    }
}
