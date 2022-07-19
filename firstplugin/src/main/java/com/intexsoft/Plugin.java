package com.intexsoft;


import com.intexsoft.model.JsonNode;
import com.intexsoft.model.JsonObject;
import com.intexsoft.parser.JsonDeserializer;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Mojo(name = "startprogram")
public class Plugin extends AbstractMojo {
    @Parameter(property = "pathToDirectory")
    private String pathToDirectory;

    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("=========================");
        System.out.println(pathToDirectory);
        File directory = new File(pathToDirectory);
        System.out.println(directory.listFiles().length);
        File[] files = directory.listFiles();
        int fileNumberCounter = 1;
        for (File tempFile : files) {
            try {
                FileReader fileReader = new FileReader(tempFile);
                StringBuilder stringBuilder = new StringBuilder();
                int end = 0;
                while (true) {
                    try {
                        if (!((end = fileReader.read()) != -1)) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stringBuilder.append((char) end);
                }
                JsonNode jsonNode = JsonDeserializer.parse(stringBuilder.toString());
                if (jsonNode.getClass().isAssignableFrom(JsonObject.class)) {
                    System.out.println(((JsonObject) jsonNode).values);
                    File JsonObjectFile = new File("jsonsOutput/CustomClass" + fileNumberCounter + ".java");
                    JsonObjectFile.createNewFile();
                    FileWriter fileWriter = new FileWriter(JsonObjectFile, false);
                    String fieldNames[] = ((JsonObject) jsonNode).values.keySet().toArray(new String[0]);
                    fileWriter.write("import lombok.*;@Data\n public class CustomClass" + fileNumberCounter + "{ ");
                    for (String fieldName : fieldNames) {
                        if (!((JsonObject) jsonNode).get(fieldName).getClass().isAssignableFrom(JsonObject.class)) {
                            fileWriter.write("private " + ((JsonObject) jsonNode).get(fieldName).getClass().getSimpleName().replace("Json", "") + " " + fieldName + " =" + ((JsonObject) jsonNode).get(fieldName) + ";");
                        } else {
                            fileNumberCounter++;
                            File nestedJsonObjectFile = new File("jsonsOutput/CustomClass" + fileNumberCounter + ".java");
                            nestedJsonObjectFile.createNewFile();
                            FileWriter nestedJsonObjectFileWriter = new FileWriter(nestedJsonObjectFile);
                            JsonObject jsonObject = (JsonObject) ((JsonObject) jsonNode).get(fieldName);
                            System.out.println(jsonObject.values);
                            String fieldNames2[] = jsonObject.values.keySet().toArray(new String[0]);
                            nestedJsonObjectFileWriter.write("import lombok.*;@Data\n public class CustomClass" + fileNumberCounter + "{ ");
                            for (String fieldName2 : fieldNames2) {
                                System.out.println("here");
                                nestedJsonObjectFileWriter.write("private " + jsonObject.get(fieldName2).getClass().getSimpleName().replace("Json", "") + " " + fieldName2 + " =" + jsonObject.get(fieldName2) + ";");
                            }
                            nestedJsonObjectFileWriter.write(" public CustomClass" + fileNumberCounter + " getCustomClass" + fileNumberCounter + "(){\n" +
                                    "        CustomClass" + fileNumberCounter + " customClass" + fileNumberCounter + "=new CustomClass" + fileNumberCounter + "();\n" +
                                    "        return customClass" + fileNumberCounter + ";\n" +
                                    "    }}");
                            nestedJsonObjectFileWriter.close();
                            fileWriter.write("private CustomClass" + fileNumberCounter + " customClass" + fileNumberCounter + " =new CustomClass" + fileNumberCounter + "();");
                            fileWriter.write("private " + "CustomClass" + fileNumberCounter + " " + fieldName + "=getCustomClass" + fileNumberCounter + "();");
                        }

                    }
                    fileNumberCounter++;
                    fileWriter.write("}");
                    fileWriter.close();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}