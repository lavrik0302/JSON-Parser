package main.java.com.intexsoft.parser;

import lombok.NonNull;
import main.java.com.intexsoft.model.*;
import main.java.com.intexsoft.utils.exceptions.InvalidJsonException;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.math.BigDecimal;
import java.math.BigInteger;
public final class JsonDeserializer {
    @NonNull
    private final String src;
    @NonNull
    private int cursor;

    public JsonDeserializer(String cs) {
        this.src = cs;
    }

    private JsonNode parseValue() {
        JsonNode value;
        skipWhitespaces();

        switch (src.charAt(cursor)) {
            case '[':
                value = parseArray();
                break;
            case '{':
                value = parseObject();
                break;
            case '"': {
                value = parseString();
                break;
            }
            case 'n':
                cursor += 3;
                value = null;
                break;
            case 't':
                cursor += 3;
                JsonBoolean jsonBooleanTrue = new JsonBoolean();
                jsonBooleanTrue.setJsonBoolean(TRUE);
                value = jsonBooleanTrue;
                break;
            case 'f':
                cursor += 4;
                JsonBoolean jsonBooleanFalse = new JsonBoolean();
                jsonBooleanFalse.setJsonBoolean(FALSE);
                value = jsonBooleanFalse;
                break;
            default:try {
                value = parseNumber();
                break;
            }catch (NumberFormatException e){
                value=parseString();
                break;
            }
        }
        return value;
    }

    private void skipWhitespaces() {
        while (isWhitespace(src.charAt(cursor))) {
            cursor++;
        }
    }

    private JsonString parseString() {
        cursor++; // skip double quotes
        int endIdx = src.indexOf('"', cursor);
        String value = src.substring(cursor, endIdx);
        cursor = endIdx;
        JsonString jsonString = new JsonString();
        jsonString.setJsonString(value);
        return jsonString;
    }

    private JsonNumber parseNumber() throws NumberFormatException {
        boolean isInt = true;
        final int beginIdx = cursor;
        JsonNumber jsonNumber = new JsonNumber();

        for (; cursor < src.length(); cursor++) {
            char c = src.charAt(cursor);
            if (isInt && c == 'e' || c == 'E' || c == '.') isInt = false;
            if (isWhitespace(c) || c == ',' || c == '}' || c == ']') break;
        }

        int len = cursor - beginIdx;

        if (isInt && len < 10) {
            jsonNumber.setJsonNumber(Integer.parseInt(src, beginIdx, cursor--, 10));
        } else if (isInt && len < 19) {
            jsonNumber.setJsonNumber(Long.parseLong(src, beginIdx, cursor--, 10));
        } else if (isInt) {
            jsonNumber.setJsonNumber(new BigInteger(src.substring(beginIdx, cursor--)));
        } else if (len < 15) {
            jsonNumber.setJsonNumber(Double.parseDouble(src.substring(beginIdx, cursor--)));
        } else {
            jsonNumber.setJsonNumber(new BigDecimal(src.substring(beginIdx, cursor--)));
        }
        return jsonNumber;
    }

    private JsonObject parseObject() {
        JsonObject obj = null;
        String key = null;

        // skip an opening bracket
        for (cursor++; ; cursor++) {
            char c = src.charAt(cursor);
            if (c == '"') {
                key = parseString().getJsonString();
            } else if (c == '}') {
                return (obj == null) ? JsonObject.EMPTY : obj;
            } else if (c == ':') {
                if (obj == null) obj = new JsonObject();
                cursor++;
                obj.values.put(key, parseValue());
            }
        }
    }


    private JsonArray parseArray() {
        JsonArray array = null;
        // skip an opening bracket
        for (cursor++; ; cursor++) {
            char c = src.charAt(cursor);
            if (c == ',' || isWhitespace(c)) continue;

            if (c == ']') {
                return (array == null) ? JsonArray.EMPTY : array;
            } else {
                if (array == null) array = new JsonArray();
                array.values.add(parseValue());
            }
        }
    }

    private static boolean isWhitespace(char c) {
        return c == ' ' || c == '\r' || c == '\n' || c == '\t';
    }

    public static JsonNode parse(String jsonContent) throws IllegalArgumentException {
        JsonDeserializer parser = new JsonDeserializer(jsonContent);
        try {
            return parser.parseValue();
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidJsonException("Wrong JSON at position: ", parser.cursor);
        }
    }

    public static JsonObject parseObject(String jsonContent) throws IllegalArgumentException {
        return (JsonObject) parse(jsonContent);
    }
}