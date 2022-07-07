package utils.exceptions;

public class NewInstanceException extends RuntimeException {
    public NewInstanceException(String message, Class classType) {
        System.out.println(message + classType.getClass());
    }
}
