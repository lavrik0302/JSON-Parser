package com.intexsoft.model;

import lombok.Data;

@Data
public class JsonString extends JsonNode {
    private String jsonString;

    @Override
    public String toString() {
        return "\"" + jsonString + "\"";
    }
}
