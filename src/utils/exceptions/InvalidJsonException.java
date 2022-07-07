package utils.exceptions;

public class InvalidJsonException extends IllegalArgumentException {
    public InvalidJsonException(String message, int cursor) {
        System.out.println(message + cursor);

    }

}
