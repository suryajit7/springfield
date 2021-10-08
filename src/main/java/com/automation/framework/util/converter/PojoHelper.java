package com.automation.framework.util.converter;

import br.com.six2six.fixturefactory.Fixture;

public final class PojoHelper {

    private PojoHelper() {}

    public static <T> T getObject(String label, Class<T> type) {
        return type.cast(Fixture.from(type).gimme(label));
    }

}
