package main.java.com.intexsoft.model;

import lombok.Data;

@Data
public class JsonNull extends JsonNode {
    private Object jsonNull;


    @Override
    public String toString() {
        return "null";
    }
}
