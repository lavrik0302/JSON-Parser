package utils.exceptions;

import java.lang.reflect.Field;

public class NoFieldInMappingObjectException extends NoSuchFieldException {
    private Field field;

    public Field getField() {
        return field;
    }

    public NoFieldInMappingObjectException(String message, Field field) {
        System.out.println(message + field);
    }
}
