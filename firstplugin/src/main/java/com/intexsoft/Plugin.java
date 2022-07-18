package com.intexsoft;


import com.google.inject.Inject;
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
    @Parameter(property = "rootToFile")
    private String rootToFile;
@Inject
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("=========================");
        File file = new File(rootToFile);
        try {
            FileReader fileReader = new FileReader(file);
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
            System.out.println(stringBuilder.toString());
            JsonNode jsonNode= JsonDeserializer.parse(stringBuilder.toString());
            System.out.println(jsonNode);
            if (jsonNode.getClass().isAssignableFrom(JsonObject.class)){
            System.out.println(((JsonObject)jsonNode).values);
                File file2 =new File("CustomClass.java");
                file2.createNewFile();
                FileWriter fileWriter=new FileWriter(file2,false);
                String fieldNames[]= ((JsonObject) jsonNode).values.keySet().toArray(new String[0]);
                fileWriter.write("import lombok.*;@Data\n public class customClass{ ");
                for (String fieldName:fieldNames){

                 fileWriter.write("private "+((JsonObject) jsonNode).get(fieldName).getClass().getSimpleName().replace("Json","")+" "+ fieldName+" ="+ ((JsonObject) jsonNode).get(fieldName)+";");
                }
                fileWriter.write("}");
                fileWriter.close();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}