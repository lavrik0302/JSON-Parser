import lombok.*;

@Data
public class Adress {
    private FlatInfo flatInfo = new FlatInfo().getFlatInfo();
    private String street = "Lenina";
    private Number houseNumber = 12;

    public Adress getAdress() {
        Adress adress = new Adress();
        return adress;
    }
}