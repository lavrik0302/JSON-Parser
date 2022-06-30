import java.util.*;
import java.util.function.Consumer;

public final class JsArray extends JsNode implements Iterable<JsNode> {
    static final JsArray      EMPTY = new JsArray(Collections.emptyList());
    final List<JsNode> values;

    JsArray(List<JsNode> values) {
        this.values = values;
    }

    JsArray() {
        this.values = new ArrayList<>();
    }

    public JsArray getArray(int pos) {
        return (JsArray) values.get(pos);
    }

    public JsObject getObject(int pos) {
        return (JsObject) values.get(pos);
    }

    public JsBoolean getBoolean(int pos) {
        return (JsBoolean) values.get(pos);
    }

    public JsString getString(int pos) {
        return (JsString) values.get(pos);
    }

    public JsNumber getNumber(int pos) {
        return (JsNumber) values.get(pos);
    }


    public Object get(int index) {
        return values.get(index);
    }

    public int size() {
        return values.size();
    }


    @Override public String toString() {
        if (values.isEmpty()) return "[]";

        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (Object e : values) {
            if (e instanceof String) {
                sj.add("\"" + e + "\"");
            } else {
                sj.add(e.toString());
            }
        }
        return sj.toString();
    }

    @Override public Iterator<JsNode> iterator() {
        return values.iterator();
    }
}