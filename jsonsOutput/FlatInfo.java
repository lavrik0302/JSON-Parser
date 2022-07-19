import lombok.*;

@Data
public class FlatInfo { 
    private Number flatNumber = 98;

    private Number floor = 2;

    public FlatInfo getFlatInfo() {
        FlatInfo flatinfo = new FlatInfo();
        return flatinfo;
    }
}