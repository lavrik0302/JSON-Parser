public class JsBoolean extends JsNode {
    boolean jsBoolean;

    public void setJsBoolean(boolean jsBoolean) {
        this.jsBoolean = jsBoolean;
    }
    public boolean getJsBoolean() {
       return jsBoolean;
    }

    @Override
    public String toString() {
return ""+jsBoolean;
    }
}

