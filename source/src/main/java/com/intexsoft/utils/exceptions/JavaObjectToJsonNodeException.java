package com.intexsoft.utils.exceptions;

public class JavaObjectToJsonNodeException extends RuntimeException {

    public JavaObjectToJsonNodeException(Exception e, String message, Class classType) {
        System.out.println(message + classType);
    }
}
