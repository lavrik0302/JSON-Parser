package utils.exceptions;



public class MappingObjectException extends RuntimeException {

    public MappingObjectException(Exception exception, String message, Class classType) {
        System.out.println(exception + " " + message + " Class Type is " + classType);
    }
}
