package com.intexsoft.utils;

import com.intexsoft.model.JsonNode;
import com.intexsoft.model.JsonNumber;
import com.intexsoft.utils.exceptions.IOFileException;
import com.intexsoft.utils.exceptions.InvalidJsonException;
import com.intexsoft.utils.exceptions.NoSuchFileException;
import org.junit.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class FilesIOTest {
    FilesIO filesIO = new FilesIO();

    @Before
    public void setUp() {
        File file = new File("src/test/resources/chmod222.txt");
        file.setReadable(false);
        File file1 = new File("src/test/resources/chmod444.txt");
        file1.setWritable(false);
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

    @Ignore
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

    @Test
    public void parseFromFileTest() {
        File file = new File("src/test/resources/text.txt");
        JsonNumber actual = new JsonNumber();
        actual.setJsonNumber(12);
        JsonNode excepted = filesIO.parse(file);
        Assert.assertTrue(excepted.equals(actual));
    }

    @Test
    public void parseFromFileFailTest() {
        File file = new File("src/test/resources/text.txt");
        JsonNumber actual = new JsonNumber();
        actual.setJsonNumber(12.1);
        JsonNode excepted = filesIO.parse(file);
        Assert.assertFalse(excepted.equals(actual));
    }

    @Test(expected = InvalidJsonException.class)
    public void parsingInvalidJsonFromFileTest() {
        File file = new File("src/test/resources/invalidJson.txt");
        JsonNode excepted = filesIO.parse(file);
    }

    @Test(expected = NoSuchFileException.class)
    public void parseFromNoSuchFileTest() {
        File file = new File("src/test/resources/texxxxxxt.txt");
        JsonNumber actual = new JsonNumber();
        actual.setJsonNumber(12);
        JsonNode excepted = filesIO.parse(file);
        Assert.assertTrue(excepted.equals(actual));
    }

    @Ignore
    @Test(expected = NoSuchFileException.class, timeout = 100)
    public void parseFromLockedFileTest() {
        File file = new File("src/test/resources/chmod222.txt");
        JsonNumber actual = new JsonNumber();
        actual.setJsonNumber(12);
        JsonNode excepted = filesIO.parse(file);
        Assert.assertTrue(excepted.equals(actual));
    }

    @After
    public void chmod() {
        File file = new File("src/test/resources/chmod222.txt");
        file.setReadable(true);
        File file1 = new File("src/test/resources/chmod444.txt");
        file1.setWritable(true);
    }
}
