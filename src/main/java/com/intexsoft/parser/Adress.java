package com.intexsoft.parser;

import lombok.*;

@Data
public class Adress {
    @NonNull
    private Integer houseNumber;
    @NonNull
    private String street;
    @NonNull
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
