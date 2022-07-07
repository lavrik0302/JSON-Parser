package utils.exceptions;

public class InstantiationExceptionInMappingObject extends InstantiationException {
    public InstantiationExceptionInMappingObject(String message, Class classType) {
        System.out.println(message + classType.getClass());
    }
}
