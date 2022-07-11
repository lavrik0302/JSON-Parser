package parser;

import lombok.Data;
import lombok.NonNull;

@Data
public class User {
    @NonNull
    private Boolean ableToWork;
    @NonNull
    private String name;
    private Adress adress;
    @NonNull
    private Integer age;

    public User() {

    }
}
