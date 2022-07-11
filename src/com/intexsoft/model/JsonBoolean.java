package com.intexsoft.model;

import lombok.Data;

@Data
public class JsonBoolean extends JsonNode {
    private boolean jsonBoolean;


    @Override
    public String toString() {
        return Boolean.toString(jsonBoolean);
    }
}

