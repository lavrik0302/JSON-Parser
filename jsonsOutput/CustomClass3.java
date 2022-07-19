import lombok.*;

@Data
public class CustomClass3 {
    private String street = "Lenina";
    private Number houseNumber = 12;

    public CustomClass3 getCustomClass3() {
        CustomClass3 customClass3 = new CustomClass3();
        return customClass3;
    }
}