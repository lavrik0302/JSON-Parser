package parser;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import model.JsonObject;

@Data
public class User extends JsonObject {
    @NonNull
    @Getter
    @Setter
    private Boolean ableToWork;
    @NonNull
    @Getter
    @Setter
    private String name;
    @NonNull
    @Getter
    @Setter
    private Adress adress;
    @Getter
    @Setter
    @NonNull
    private Integer age;

    public User() {

    }

}
