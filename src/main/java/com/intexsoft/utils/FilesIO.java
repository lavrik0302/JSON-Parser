package com.intexsoft.utils;

import com.intexsoft.model.*;
import com.intexsoft.parser.JsonDeserializer;
import com.intexsoft.serializer.JsonSerializer;
import com.intexsoft.utils.exceptions.IOFileException;
import com.intexsoft.utils.exceptions.InvalidJsonException;
import com.intexsoft.utils.exceptions.NoSuchFileException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FilesIO {
    public JsonNode parse(File file) {
        try (FileReader fileReader = new FileReader(file)) {
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
            try {
                return parser.parseValue();
            } catch (StringIndexOutOfBoundsException e) {
                throw new InvalidJsonException("Wrong JSON at position: ", parser.getCursor());
            }

        } catch (IOException e) {
            throw new NoSuchFileException(e, "No such file ", file);
        }
    }

    public void serialize(Object javaObject, File file) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            JsonSerializer jsonSerializer = new JsonSerializer();
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
        } catch (IOException e) {
            throw new IOFileException(e, "Problem with writing to file ", file);
        }
    }
}
