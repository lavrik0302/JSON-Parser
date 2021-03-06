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
import java.util.ArrayList;
import java.util.List;

@Mojo(name = "startprogram")
public class Plugin extends AbstractMojo {
    @Parameter(property = "pathToDirectory")
    private String pathToDirectory;

    public void execute() throws MojoExecutionException, MojoFailureException {

        File directory = new File(pathToDirectory);
        List<File> files = setFiles(directory);
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
                    System.out.println("here");
                    File dirTar = new File("buildtime/target/");
                    dirTar.mkdir();
                    File dir = new File("buildtime/target/generated-sources/");
                    dir.mkdir();
                    System.out.println(dir);
                    File jsonObjectFile = new File("buildtime/target/generated-sources/" + tempFile.getName().substring(0, 1).toUpperCase() + tempFile.getName().substring(1, tempFile.getName().indexOf('.')) + ".java");
                    System.out.println(jsonObjectFile);
                    jsonObjectFile.createNewFile();
                    System.out.println("aftercreatenew file");
                    mapObject(jsonNode, jsonObjectFile);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void mapObject(JsonNode jsonNode, File jsonObjectFile) throws IOException {
        FileWriter fileWriter = new FileWriter(jsonObjectFile, false);
        String[] fieldNames = ((JsonObject) jsonNode).values.keySet().toArray(new String[0]);
        fileWriter.write("import lombok.*;\n\n@Data\npublic class " + jsonObjectFile.getName().substring(0, 1).toUpperCase() + jsonObjectFile.getName().substring(1, jsonObjectFile.getName().indexOf('.')) + " { ");
        for (String fieldName : fieldNames) {
            if (!((JsonObject) jsonNode).get(fieldName).getClass().isAssignableFrom(JsonObject.class)) {
                fileWriter.write("\n    private " + ((JsonObject) jsonNode).get(fieldName).getClass().getSimpleName().replace("Json", "") + " " + fieldName + " = " + ((JsonObject) jsonNode).get(fieldName) + ";\n");
            } else {
                System.out.println("sdas");
                File file = new File("buildtime/target/generated-sources/" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1) + ".java");
                file.createNewFile();
                mapObject(((JsonObject) jsonNode).get(fieldName), file);
                fileWriter.write("\n    private " + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1) + " " + fieldName + " = new " + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1) + "().get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1) + "();\n");
            }
        }
        String className = jsonObjectFile.getName().substring(0, 1).toUpperCase() + jsonObjectFile.getName().substring(1, jsonObjectFile.getName().indexOf('.'));
        fileWriter.write("\n    public " + className + " get" + className + "() {\n        " + className + " " + className.toLowerCase() + " = new " + className + "();\n        return " + className.toLowerCase() + ";\n    }\n");
        fileWriter.write("}");
        fileWriter.close();
    }

    public List<File> setFiles(File directory) {
        List<File> allFiles = new ArrayList<>();
        File[] files = directory.listFiles();
        for (File tempFile : files) {
            if (tempFile.isDirectory()) {
                allFiles.addAll(setFiles(tempFile));
            } else {
                allFiles.add(tempFile);
            }
        }
        return allFiles;
    }
}