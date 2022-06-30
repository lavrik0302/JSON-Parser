import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.math.BigDecimal;
import java.math.BigInteger;


public final class JsParser {
    private final String src;
    private int cursor;

    public JsParser(String cs) {
        this.src = cs;
    }

    private JsNode parseValue() {
        JsNode value;
        skipWhitespaces();

        switch (src.charAt(cursor)) {
            case '[':
                value = parseArray();
                break;
            case '{':
                value = parseObject();
                break;
            case '"':{
                value=parseString();
                break;}
            case 'n':
                cursor += 3;
                value = null;
                break;
            case 't':
                cursor += 3;
                JsBoolean jsBooleanTrue=new JsBoolean();
                jsBooleanTrue.setJsBoolean(TRUE);
                  value = jsBooleanTrue;
                break;
            case 'f':
                cursor += 4;
                JsBoolean jsBooleanFalse=new JsBoolean();
                jsBooleanFalse.setJsBoolean(FALSE);
                value = jsBooleanFalse;
                break;
            default:
                value = parseNumber();
                break;
        }

        return value;
    }

    private void skipWhitespaces() {
        while (isWhitespace(src.charAt(cursor))) {
            cursor++;
        }
    }

    private JsString parseString() {
        cursor++; // skip double quotes
        int endIdx = src.indexOf('"', cursor);
        String value = src.substring(cursor, endIdx);
        cursor = endIdx;
         JsString jsString=new JsString();
         jsString.setJsString(value);
        return jsString;
    }

    private JsNumber parseNumber() throws NumberFormatException {
        boolean isInt = true;
        final int beginIdx = cursor;
        JsNumber jsNumber=new JsNumber();

        for (; cursor < src.length(); cursor++) {
            char c = src.charAt(cursor);
            if (isInt && c == 'e' || c == 'E' || c == '.') isInt = false;
            if (isWhitespace(c) || c == ',' || c == '}' || c == ']') break;
        }

        int len = cursor - beginIdx;

        if (isInt && len < 10) {
            jsNumber.setJsNumber(Integer.parseInt(src, beginIdx, cursor--, 10));
        } else if (isInt && len < 19) {
             jsNumber.setJsNumber(Long.parseLong(src, beginIdx, cursor--, 10));
        } else if (isInt) {
            jsNumber.setJsNumber(new BigInteger(src.substring(beginIdx, cursor--)));
        } else if (len < 15) {
            jsNumber.setJsNumber(Double.parseDouble(src.substring(beginIdx, cursor--)));
        } else {
            jsNumber.setJsNumber( new BigDecimal(src.substring(beginIdx, cursor--)));
        }
        return jsNumber;
    }

    private JsObject parseObject() {
        JsObject obj = null;
        String key = null;

        // skip an opening bracket
        for (cursor++; ; cursor++) {
            char c = src.charAt(cursor);
            if (c == '"') {
                key = parseString().getJsString();
            } else if (c == '}') {
                return (obj == null) ? JsObject.EMPTY : obj;
            } else if (c == ':') {
                if (obj == null) obj = new JsObject();
                cursor++;
                obj.values.put(key, parseValue());
            }
        }
    }


    private JsArray parseArray() {
        JsArray array = null;
        // skip an opening bracket
        for (cursor++; ; cursor++) {
            char c = src.charAt(cursor);
            if (c == ',' || isWhitespace(c)) continue;

            if (c == ']') {
                return (array == null) ? JsArray.EMPTY : array;
            } else {
                if (array == null) array = new JsArray();
                array.values.add(parseValue());
            }
        }
    }

    private static boolean isWhitespace(char c) {
        return c == ' ' || c == '\r' || c == '\n' || c == '\t';
    }

    public static JsNode parse(String jsonContent) throws IllegalArgumentException {
        JsParser parser = new JsParser(jsonContent);
        try {
            return parser.parseValue();
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Wrong JSON at position: " + parser.cursor);
        }
    }

    public static JsObject parseObject(String jsonContent) throws IllegalArgumentException {
        return (JsObject) parse(jsonContent);
    }
}