import lombok.*;

@Data
public class CustomClass2 {
    private String city = "Grodno";
    private Boolean moreThan100k = true;
    private CustomClass3 customClass3 = new CustomClass3();
    private CustomClass3 adress = getCustomClass3();
}