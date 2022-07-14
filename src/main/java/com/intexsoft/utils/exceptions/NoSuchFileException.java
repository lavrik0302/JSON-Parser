package com.intexsoft.utils.exceptions;

import java.io.File;

public class NoSuchFileException extends RuntimeException {
    public NoSuchFileException(Exception e, String message, File file) {
        System.out.println(e + "\n" + message + " " + file);
    }
}
