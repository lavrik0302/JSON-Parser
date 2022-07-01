import parser.JsonDeserializer;
public class Main {

    public static void main(String[] args) {
        JsonDeserializer test = new JsonDeserializer("");
        System.out.println(test.parse("false"));

            }
}