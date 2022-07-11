package com.intexsoft.model;

import lombok.Data;

@Data
public class JsonNumber extends JsonNode {
    private Number jsonNumber;
        @Override
    public String toString() {

        return jsonNumber.toString();
    }
}


