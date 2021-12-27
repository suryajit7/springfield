package com.automation.framework.util.helper.converter;

import com.creditdatamw.zerocell.converter.Converter;

public class StringToInteger implements Converter<Integer> {

    @Override
    public Integer convert(String value, String columnName, int row) {
        return Integer.parseInt(value);
    }
}
