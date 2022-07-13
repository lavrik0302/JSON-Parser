package main.java.com.intexsoft.utils.exceptions;

import java.io.File;
import java.io.IOException;

public class IOFileException extends RuntimeException{
    public IOFileException(IOException e, String message, File file){
        System.out.println(e+"\n"+message+file);
    }
}
