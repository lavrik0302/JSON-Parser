package com.intexsoft.utils.exceptions;

import java.lang.reflect.Field;

public class NoSuchEnumValue extends RuntimeException {

    public NoSuchEnumValue(Field field, String message) {
        System.out.println(message);
        Object arr[] = field.getType().getEnumConstants();
        for (Object val : arr) {
            System.out.println(val);
        }
    }
}
