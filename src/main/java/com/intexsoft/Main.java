package main.java.com.intexsoft;

import main.java.com.intexsoft.model.JsonNode;
import main.java.com.intexsoft.model.JsonObject;
import main.java.com.intexsoft.parser.Adress;
import main.java.com.intexsoft.parser.JsonDeserializer;
import main.java.com.intexsoft.parser.Mapper;
import main.java.com.intexsoft.parser.User;
import main.java.com.intexsoft.serializer.JsonSerializer;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/intexsoft/text.txt");
        Adress str = new Adress();
        str.setCity(Adress.City.Mogilev);
        str.setHouseNumber(12);
        str.setStreet("Soviet");
        JsonNode jsonNode = JsonDeserializer.parse(file);

    }
}