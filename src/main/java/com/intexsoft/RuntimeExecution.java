package com.intexsoft;

import com.intexsoft.model.JsonNode;
import com.intexsoft.parser.Mapper;
import com.intexsoft.utils.FilesIO;

import java.io.File;

public class RuntimeExecution {
    public static void main(String[] args) {
        File file =new File("src/main/java/com/intexsoft/text.txt");
        FilesIO filesIO=new FilesIO();
        JsonNode jsonNode=filesIO.parse(file);
        Mapper mapper=new Mapper();
        System.out.println(mapper.map(jsonNode,String.class));
    }
}
