package com.automation.framework.util.helper.converter;

import com.creditdatamw.zerocell.converter.Converter;

public class StringToBoolean implements Converter<Boolean> {

    @Override
    public Boolean convert(String value, String columnName, int row) {
        return value.equalsIgnoreCase("true");
    }

}
