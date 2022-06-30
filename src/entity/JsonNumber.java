package entity;

public class JsonNumber extends JsonNode {
    Number jsonNumber;

    public Number getJsonNumber() {
        return jsonNumber;
    }

    public void setJsonNumber(Number jsonNumber) {
        this.jsonNumber = jsonNumber;
    }

    @Override
    public String toString() {

        return jsonNumber.toString();
    }
}


