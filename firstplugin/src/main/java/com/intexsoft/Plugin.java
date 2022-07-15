package com.intexsoft;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Mojo(name = "startprogram")
public class Plugin extends AbstractMojo {

    @Parameter(property = "rootToFile")
    private String rootToFile;


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
            //Mapper mapper=new Mapper();
         // JsonNode jsonNode=JsonDesirializer.parse(stringBuilder.toString());
         //   System.out.println(jsonNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}