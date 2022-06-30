import parser.JsonParser;
public class Main {

    public static void main(String[] args) {
        JsonParser test = new JsonParser("");
        System.out.println(test.parse("false"));
    }
}