package utils.exceptions;

import java.lang.reflect.Field;

public class NoFieldException extends RuntimeException {
    private Field field;

    public Field getField() {
        return field;
    }

    public NoFieldException(String message, Field field) {
        System.out.println(message + field);
    }
}
