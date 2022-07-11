package parser;

import lombok.*;

@Data
public class Adress {
    @Getter
    @Setter
    @NonNull
    private Integer houseNumber;
    @Getter
    @Setter
    @NonNull
    private String street;
    @NonNull
    @Getter
    @Setter
    private City city;

    public Adress() {

    }

    public enum City {
        Grodno,
        Minsk,
        Vitebsk,
        Gomel,
        Mogilev,
        Brest
    }
}
