package com.intexsoft;

import com.intexsoft.model.JsonNode;
import com.intexsoft.parser.Adress;
import com.intexsoft.utils.FilesIO;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/intexsoft/text.txt");
        Adress str = new Adress();
        str.setCity(Adress.City.Mogilev);
        str.setHouseNumber(12);
        str.setStreet("Soviet");
        FilesIO filesIO = new FilesIO();
        JsonNode jsonNode = filesIO.parse(file);

    }
}