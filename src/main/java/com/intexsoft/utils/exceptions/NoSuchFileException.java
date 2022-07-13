package main.java.com.intexsoft.utils.exceptions;

import java.io.File;
import java.io.FileNotFoundException;

public class NoSuchFileException extends RuntimeException {
    public NoSuchFileException(FileNotFoundException e, String message, File file) {
        System.out.println(e + "\n" + message + " " + file);
    }
}
