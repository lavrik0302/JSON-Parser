package main.java.com.intexsoft.utils.exceptions;


public class InvalidJsonException extends RuntimeException {

    public InvalidJsonException(String message, int cursor) {
        System.out.println(message + cursor);

    }

}
