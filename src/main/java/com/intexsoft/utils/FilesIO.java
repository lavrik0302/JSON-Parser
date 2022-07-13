package main.java.com.intexsoft.utils;

import main.java.com.intexsoft.model.*;
import main.java.com.intexsoft.parser.JsonDeserializer;
import main.java.com.intexsoft.serializer.JsonSerializer;
import main.java.com.intexsoft.utils.exceptions.IOFileException;
import main.java.com.intexsoft.utils.exceptions.InvalidJsonException;
import main.java.com.intexsoft.utils.exceptions.NoSuchFileException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FilesIO {
    public JsonNode parse(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            StringBuilder stringBuilder = new StringBuilder();
            int end;
            while (true) {
                try {
                    if (!((end = fileReader.read()) != -1)) break;
                } catch (IOException e) {
                    throw new IOFileException(e, "Problems with reading from file ", file);
                }
                stringBuilder.append((char) end);
            }
            JsonDeserializer parser = new JsonDeserializer(stringBuilder.toString());
            fileReader.close();
            try {
                return parser.parseValue();
            } catch (StringIndexOutOfBoundsException e) {
                throw new InvalidJsonException("Wrong JSON at position: ", parser.cursor);
            }

        } catch (IOException e) {
            throw new NoSuchFileException(e, "No such file ", file);
        }
    }

    public void serialize(Object javaObject, File file) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            JsonSerializer jsonSerializer=new JsonSerializer();

            JsonNode jsonNode = jsonSerializer.javaObjectToJsonNode(javaObject);
            if (jsonNode.getClass().isAssignableFrom(JsonNull.class)) {
                fileWriter.write(((JsonNull) jsonNode).toString());
            } else if (jsonNode.getClass().isAssignableFrom(JsonBoolean.class)) {
                fileWriter.write(((JsonBoolean) jsonNode).toString());
            } else if (jsonNode.getClass().isAssignableFrom(JsonString.class)) {
                fileWriter.write(((JsonString) jsonNode).toString());
            } else if (jsonNode.getClass().isAssignableFrom(JsonNumber.class)) {
                fileWriter.write(((JsonNumber) jsonNode).toString());
            } else if (jsonNode.getClass().isAssignableFrom(JsonArray.class)) {
                fileWriter.write(((JsonArray) jsonNode).toString());
            } else if (jsonNode.getClass().isAssignableFrom(JsonObject.class)) {
                fileWriter.write(((JsonObject) jsonNode).toString());
            }
            fileWriter.flush();
        } catch (IOException e) {
            throw new IOFileException(e, "Problem with writing to file ", file);
        }
    }
}
