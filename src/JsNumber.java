import java.math.BigDecimal;
import java.math.BigInteger;

public class JsNumber extends JsNode {
    Number jsNumber;

    public Number getJsNumber() {
        return jsNumber;
    }

    public void setJsNumber(Number jsNumber) {
        this.jsNumber = jsNumber;
    }

    @Override
    public String toString() {
        return "" + jsNumber
                ;
    }
}


