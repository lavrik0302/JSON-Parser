package test.java.com.intexsoft.serializer;

import main.java.com.intexsoft.parser.Adress;
import main.java.com.intexsoft.serializer.JsonSerializer;
import main.java.com.intexsoft.model.*;
import main.java.com.intexsoft.utils.FilesIO;
import main.java.com.intexsoft.utils.exceptions.IOFileException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonSerializerTest {
    JsonSerializer jsonSerializer = new JsonSerializer();
    FilesIO filesIO = new FilesIO();

    @Test
    public void javaBooleanToJsonNodeTest() {
        JsonBoolean jsonBoolean = new JsonBoolean();
        jsonBoolean.setJsonBoolean(true);
        JsonBoolean expected = (JsonBoolean) jsonSerializer.javaObjectToJsonNode(true);
        Assert.assertTrue(expected.equals(jsonBoolean));
    }

    @Test
    public void javaBooleanToJsonNodeFailTest() {
        JsonBoolean jsonBoolean = new JsonBoolean();
        jsonBoolean.setJsonBoolean(false);
        JsonBoolean expected = (JsonBoolean) jsonSerializer.javaObjectToJsonNode(true);
        Assert.assertFalse(expected.equals(jsonBoolean));
    }

    @Test
    public void javaNumberToJsonNodeTest() {
        JsonNumber jsonNumber = new JsonNumber();
        jsonNumber.setJsonNumber(12);
        JsonNumber expected = (JsonNumber) jsonSerializer.javaObjectToJsonNode(12);
        Assert.assertTrue(expected.equals(jsonNumber));
    }

    @Test
    public void javaNumberToJsonNodeFailTest() {
        JsonNumber jsonNumber = new JsonNumber();
        jsonNumber.setJsonNumber(113);
        JsonNumber expected = (JsonNumber) jsonSerializer.javaObjectToJsonNode(12);
        Assert.assertFalse(expected.equals(jsonNumber));
    }

    @Test
    public void javaStringToJsonNodeTest() {
        JsonString jsonString = new JsonString();
        jsonString.setJsonString("text for test");
        JsonString expected = (JsonString) jsonSerializer.javaObjectToJsonNode("text for test");
        Assert.assertTrue(expected.equals(jsonString));
    }

    @Test
    public void javaStringToJsonNodeFailTest() {
        JsonString jsonString = new JsonString();
        jsonString.setJsonString("text   for         test");
        JsonString expected = (JsonString) jsonSerializer.javaObjectToJsonNode("text for test");
        Assert.assertFalse(expected.equals(jsonString));
    }

    @Test
    public void javaNullToJsonNodeTest() {
        JsonNull jsonNull = new JsonNull();
        jsonNull.setJsonNull(null);
        JsonNull expected = (JsonNull) jsonSerializer.javaObjectToJsonNode(null);
        Assert.assertTrue(expected.equals(jsonNull));
    }

    @Test
    public void javaArrayToJsonNodeTest() {
        JsonNumber jsonNumber = new JsonNumber();
        JsonString jsonString = new JsonString();
        JsonBoolean jsonBoolean = new JsonBoolean();
        JsonNull jsonNull = new JsonNull();
        JsonArray jsonArray = new JsonArray();
        jsonBoolean.setJsonBoolean(true);
        jsonNumber.setJsonNumber(12);
        jsonString.setJsonString("text for test");
        jsonNull.setJsonNull(null);
        jsonArray.add(jsonBoolean);
        jsonArray.add(jsonNumber);
        jsonArray.add(jsonString);
        jsonArray.add(jsonNull);
        Object[] actual = {true, 12, "text for test", null};
        JsonArray expected = (JsonArray) jsonSerializer.javaObjectToJsonNode(actual);
        Assert.assertTrue(expected.equals(jsonArray));
    }

    @Test
    public void javaEmptyArrayToJsonNodeTest() {
        JsonArray jsonArray = new JsonArray();
        Object[] actual = {};
        JsonArray expected = (JsonArray) jsonSerializer.javaObjectToJsonNode(actual);
        Assert.assertTrue(expected.equals(jsonArray));
    }

    @Test
    public void javaArrayToJsonNodeFailTest() {
        JsonNumber jsonNumber = new JsonNumber();
        JsonString jsonString = new JsonString();
        JsonBoolean jsonBoolean = new JsonBoolean();
        JsonNull jsonNull = new JsonNull();
        JsonArray jsonArray = new JsonArray();
        jsonBoolean.setJsonBoolean(false);
        jsonNumber.setJsonNumber(1222);
        jsonString.setJsonString("text    for      test");
        jsonNull.setJsonNull(null);
        jsonArray.add(jsonBoolean);
        jsonArray.add(jsonNumber);
        jsonArray.add(jsonString);
        jsonArray.add(jsonNull);
        Object[] actual = {true, 12, "text for test", null};
        JsonArray expected = (JsonArray) jsonSerializer.javaObjectToJsonNode(actual);
        Assert.assertFalse(expected.equals(jsonArray));
    }

    @Test
    public void serializeNullTest() {
        Assert.assertTrue(jsonSerializer.serialize(null).equals("null"));
    }

    @Test
    public void serializeNullFailTest() {
        Assert.assertFalse(jsonSerializer.serialize(null).equals("nnnnull"));
    }

    @Test
    public void serializeStringTest() {
        Assert.assertTrue(jsonSerializer.serialize("text for test").equals("\"text for test\""));
    }

    @Test
    public void serializeEmptyStringTest() {
        Assert.assertTrue(jsonSerializer.serialize("").equals("\"\""));
    }

    @Test
    public void serializeNullStringTest() {
        String nullString = null;
        Assert.assertTrue(jsonSerializer.serialize(nullString).equals("null"));
    }

    @Test
    public void serializeFailStringTest() {
        Assert.assertFalse(jsonSerializer.serialize("text   for   test").equals("\"text for test\""));
    }

    @Test
    public void serializeBooleanTest() {
        Assert.assertTrue(jsonSerializer.serialize(true).equals("true"));
    }

    @Test
    public void serializeBooleanFailTest() {
        Assert.assertFalse(jsonSerializer.serialize(false).equals("true"));
    }

    @Test
    public void serializeNumberTest() {
        Assert.assertTrue(jsonSerializer.serialize(123).equals("123"));
    }

    @Test
    public void serializeNullNumberTest() {
        Integer nullInteger = null;
        Assert.assertTrue(jsonSerializer.serialize(nullInteger).equals("null"));
    }

    @Test
    public void serializeNumberFailTest() {
        Assert.assertFalse(jsonSerializer.serialize(123.1).equals("123"));
    }

    @Test
    public void serializeArrayTest() {
        Object array[] = {true, "text", 3, null};
        Assert.assertTrue(jsonSerializer.serialize(array).equals("[true, \"text\", 3, null]"));
    }

    @Test
    public void serializeNullArrayTest() {
        Object array[] = {};
        Assert.assertTrue(jsonSerializer.serialize(array).equals("[]"));
    }

    @Test
    public void serializeArrayFailTest() {
        Object array[] = {true, "text", 3, null};
        Assert.assertFalse(jsonSerializer.serialize(array).equals("[tttrue, \"text\", 3, null]"));
    }

    @Test
    public void serializeObjectTest() {
        Adress adress = new Adress();
        adress.setStreet("Lenina");
        adress.setCity(Adress.City.Minsk);
        adress.setHouseNumber(12);
        Assert.assertTrue(jsonSerializer.serialize(adress).equals("{\"city\":\"Minsk\", \"street\":\"Lenina\", \"houseNumber\":12}"));
    }

    @Test
    public void serializeObjectFailTest() {
        Adress adress = new Adress();
        adress.setStreet("Lenina");
        adress.setCity(Adress.City.Gomel);
        adress.setHouseNumber(12);
        Assert.assertFalse(jsonSerializer.serialize(adress).equals("{\"city\":\"Minsk\", \"street\":\"Lenina\", \"houseNumber\":12}"));
    }

    @Test
    public void serializeNullObjectTest() {
        Adress adress = new Adress();
        Assert.assertTrue(jsonSerializer.serialize(adress).equals("{\"city\":null, \"street\":null, \"houseNumber\":null}"));
    }

    @Test
    public void serializeToFile() {
        String str = "Hello World!";
        File file = new File("src/test/resources/serializeTestFile.txt");
        filesIO.serialize(str, file);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuilder sb = new StringBuilder();
        int end;
        while (true) {
            try {
                if (!((end = fileReader.read()) != -1)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sb.append((char) end);
        }
        Assert.assertTrue(("\"" + str + "\"").equals(sb.toString()));
    }

    @Test
    public void serializeToNoSuchFile() {
        String str = "Hello World!";
        File file = new File("src/test/resources/seriallllllllizeTestFile.txt");
        System.out.println("created new File");
        filesIO.serialize(str, file);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuilder sb = new StringBuilder();
        int end;
        while (true) {
            try {
                if (!((end = fileReader.read()) != -1)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sb.append((char) end);
        }
        Assert.assertTrue(("\"" + str + "\"").equals(sb.toString()));
    }

    @Test(expected = IOFileException.class)
    public void serializeToForbiddenFile() {
        String str = "Hello World!";
        File file = new File("src/test/resources/chmod444.txt");
        filesIO.serialize(str, file);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuilder sb = new StringBuilder();
        int end;
        while (true) {
            try {
                if (!((end = fileReader.read()) != -1)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sb.append((char) end);
        }
        Assert.assertTrue(("\"" + str + "\"").equals(sb.toString()));
    }
}