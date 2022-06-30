public class JsString extends JsNode {
    String jsString;

    public String getJsString() {
        return jsString;
    }

    public void setJsString(String jsString) {
        this.jsString = jsString;
    }

    @Override
    public String toString() {
        return getJsString();
    }
}
